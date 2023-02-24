package com.aliyun.dts.deliver.core;

import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.io.IOs;
import com.aliyun.dts.deliver.commons.json.Jsons;
import com.aliyun.dts.deliver.commons.util.AutoCloseableIterators;
import com.aliyun.dts.deliver.commons.util.MoreIterators;
import com.aliyun.dts.deliver.commons.validation.json.JsonSchemaValidator;
import com.aliyun.dts.deliver.protocol.generated.*;
import com.aliyun.dts.deliver.protocol.generated.DtsConnectionStatus.Status;
import com.aliyun.dts.deliver.protocol.generated.DtsMessage.Type;
import com.aliyun.dts.deliver.protocol.transform.CatalogHelpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ThreadUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntegrationRunnerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationRunnerTest.class);

    private static final String CONFIG_FILE_NAME = "config.json";
    private static final String CONFIGURED_CATALOG_FILE_NAME = "configured_catalog.json";
    private static final String STATE_FILE_NAME = "state.json";

    private static final String[] ARGS = new String[] {"args"};

    private static final String CONFIG_STRING = "{ \"username\": \"airbyte\" }";
    private static final JsonNode CONFIG = Jsons.deserialize(CONFIG_STRING);
    private static final String STREAM_NAME = "users";
    private static final Long EMITTED_AT = Instant.now().toEpochMilli();
    private static final Path TEST_ROOT = Path.of("/tmp/airbyte_tests");

    private static final DtsCatalog CATALOG = new DtsCatalog().withStreams(Lists.newArrayList(new DtsStream().withName(STREAM_NAME)));
    private static final ConfiguredDtsCatalog CONFIGURED_CATALOG = CatalogHelpers.toDefaultConfiguredCatalog(CATALOG);
    private static final JsonNode STATE = Jsons.jsonNode(ImmutableMap.of("checkpoint", "05/08/1945"));

    private IntegrationCliParser cliParser;
    private Consumer<DtsMessage> stdoutConsumer;
    private Destination destination;
    private Source source;
    private Path configPath;
    private Path configuredCatalogPath;
    private Path statePath;

    @BeforeEach
    void setup() throws IOException {
        cliParser = mock(IntegrationCliParser.class);
        stdoutConsumer = Mockito.mock(Consumer.class);
        destination = mock(Destination.class);
        source = mock(Source.class);
        final Path configDir = Files.createTempDirectory(Files.createDirectories(TEST_ROOT), "test");

        configPath = IOs.writeFile(configDir, CONFIG_FILE_NAME, CONFIG_STRING);
        configuredCatalogPath = IOs.writeFile(configDir, CONFIGURED_CATALOG_FILE_NAME, Jsons.serialize(CONFIGURED_CATALOG));
        statePath = IOs.writeFile(configDir, STATE_FILE_NAME, Jsons.serialize(STATE));

        final String testName = Thread.currentThread().getName();
        ThreadUtils.getAllThreads()
                .stream()
                .filter(runningThread -> !runningThread.isDaemon())
                .forEach(runningThread -> runningThread.setName(testName));
    }

    @Test
    void testSpecSource() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.spec();
        final ConnectorSpecification output = new ConnectorSpecification().withDocumentationUrl(new URI("https://help.aliyun.com/product/26590.html"));

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(source.spec()).thenReturn(output);

        new IntegrationRunner(cliParser, stdoutConsumer, null, source).run(ARGS);

        verify(source).spec();
        verify(stdoutConsumer).accept(new DtsMessage().withType(Type.SPEC).withSpec(output));
    }

    @Test
    void testSpecDestination() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.spec();
        final ConnectorSpecification output = new ConnectorSpecification().withDocumentationUrl(new URI("https://help.aliyun.com/product/26590.html"));

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(destination.spec()).thenReturn(output);

        new IntegrationRunner(cliParser, stdoutConsumer, destination, null).run(ARGS);

        verify(destination).spec();
        verify(stdoutConsumer).accept(new DtsMessage().withType(Type.SPEC).withSpec(output));
    }

    @Test
    void testCheckSource() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.check(configPath);
        final DtsConnectionStatus output = new DtsConnectionStatus().withStatus(DtsConnectionStatus.Status.FAILED).withMessage("it failed");

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(source.check(CONFIG)).thenReturn(output);

        final ConnectorSpecification expectedConnSpec = mock(ConnectorSpecification.class);
        when(source.spec()).thenReturn(expectedConnSpec);
        when(expectedConnSpec.getConnectionSpecification()).thenReturn(CONFIG);
        final JsonSchemaValidator jsonSchemaValidator = mock(JsonSchemaValidator.class);
        new IntegrationRunner(cliParser, stdoutConsumer, null, source, jsonSchemaValidator).run(ARGS);

        verify(source).check(CONFIG);
        verify(stdoutConsumer).accept(new DtsMessage().withType(Type.CONNECTION_STATUS).withConnectionStatus(output));
        verify(jsonSchemaValidator).validate(any(), any());
    }

    @Test
    void testCheckDestination() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.check(configPath);
        final DtsConnectionStatus output = new DtsConnectionStatus().withStatus(Status.FAILED).withMessage("it failed");

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(destination.check(CONFIG)).thenReturn(output);

        final ConnectorSpecification expectedConnSpec = mock(ConnectorSpecification.class);
        when(destination.spec()).thenReturn(expectedConnSpec);
        when(expectedConnSpec.getConnectionSpecification()).thenReturn(CONFIG);

        final JsonSchemaValidator jsonSchemaValidator = mock(JsonSchemaValidator.class);

        new IntegrationRunner(cliParser, stdoutConsumer, destination, null, jsonSchemaValidator).run(ARGS);

        verify(destination).check(CONFIG);
        verify(stdoutConsumer).accept(new DtsMessage().withType(Type.CONNECTION_STATUS).withConnectionStatus(output));
        verify(jsonSchemaValidator).validate(any(), any());
    }

    @Test
    void testDiscover() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.discover(configPath);
        final DtsCatalog output = new DtsCatalog()
                .withStreams(Lists.newArrayList(new DtsStream().withName("oceans")));

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(source.discover(CONFIG)).thenReturn(output);

        final ConnectorSpecification expectedConnSpec = mock(ConnectorSpecification.class);
        when(source.spec()).thenReturn(expectedConnSpec);
        when(expectedConnSpec.getConnectionSpecification()).thenReturn(CONFIG);

        final JsonSchemaValidator jsonSchemaValidator = mock(JsonSchemaValidator.class);
        new IntegrationRunner(cliParser, stdoutConsumer, null, source, jsonSchemaValidator).run(ARGS);

        verify(source).discover(CONFIG);
        verify(stdoutConsumer).accept(new DtsMessage().withType(Type.CATALOG).withCatalog(output));
        verify(jsonSchemaValidator).validate(any(), any());
    }

    @Test
    void testRead() throws Exception {
        final IntegrationConfig intConfig = IntegrationConfig.read(configPath, configuredCatalogPath,
                statePath);
        final DtsMessage message1 = new DtsMessage().withType(Type.RECORD)
                .withRecord(new DtsRecordMessage().withData(Jsons.jsonNode(ImmutableMap.of("names", "byron"))));
        final DtsMessage message2 = new DtsMessage().withType(Type.RECORD).withRecord(new DtsRecordMessage()
                .withData(Jsons.jsonNode(ImmutableMap.of("names", "reginald"))));

        when(cliParser.parse(ARGS)).thenReturn(intConfig);
        when(source.read(CONFIG, CONFIGURED_CATALOG, STATE))
                .thenReturn(AutoCloseableIterators.fromIterator(MoreIterators.of(message1, message2)));

        final ConnectorSpecification expectedConnSpec = mock(ConnectorSpecification.class);
        when(source.spec()).thenReturn(expectedConnSpec);
        when(expectedConnSpec.getConnectionSpecification()).thenReturn(CONFIG);

        final JsonSchemaValidator jsonSchemaValidator = mock(JsonSchemaValidator.class);
        new IntegrationRunner(cliParser, stdoutConsumer, null, source, jsonSchemaValidator).run(ARGS);

        verify(source).read(CONFIG, CONFIGURED_CATALOG, STATE);
        verify(stdoutConsumer).accept(message1);
        verify(stdoutConsumer).accept(message2);
        verify(jsonSchemaValidator).validate(any(), any());
    }
}
