package com.aliyun.dts.deliver.connector.desination;

import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.json.Jsons;
import com.aliyun.dts.deliver.commons.openapi.DtsOpenApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import com.taobao.drc.togo.client.producer.TogoProducer;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

public class DStoreDestinationConfig {
    protected static final Logger LOGGER = LoggerFactory.getLogger(DStoreDestinationConfig.class);

    protected String topic;
    private TogoProducer producer;

    private Properties properties;

    public DStoreDestinationConfig(final TogoProducer producer, final Properties properties) {
        this.producer = producer;
        this.properties = properties;
    }



    public static DStoreDestinationConfig getDStoreDestinationConfig(Settings settings, Pair<String, String> userPassword) {

        Properties properties = getProducerProperties(settings, userPassword);

        LOGGER.info("all dstore properties are: ");
        for (String key : properties.stringPropertyNames()) {
            LOGGER.info("\t{}: {}", key, properties.get(key));
        }

        return new DStoreDestinationConfig(new TogoProducer(properties), properties);
    }

    public static Properties getProducerProperties(Settings settings, Pair<String, String> userPassword) {
        Properties props = new Properties();

        initCommonClientProps(props);

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, GlobalSettings.DTS_BOOTSTRAP_SERVERS_CONFIG.getValue(settings));
        props.put(ProducerConfig.CLIENT_ID_CONFIG, getClientId("producer", GlobalSettings.DTS_INSTANCE_ID.getValue(settings)));
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.RETRIES_CONFIG, 0); //重试0次，避免数据重复
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, settings.get("batch_size"));
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, settings.get("buffer_memory")); //
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, Long.MAX_VALUE);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, settings.get("request_timeout_ms"));
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, settings.get("compression_type"));
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1024 * 1024 * 1024); // 1GB
        props.put(ProducerConfig.SEND_BUFFER_CONFIG,  settings.get("send_buffer_bytes")); // 32MB
        props.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, settings.get("receive_buffer_bytes")); // 32MB
        props.put(ProducerConfig.LINGER_MS_CONFIG, settings.get("linger_ms"));

        // enable leader switch interrupt.
        props.put("leaderSwitchInterrupt", "true");

        //protocol props
        props.put(SaslConfigs.SASL_JAAS_CONFIG, buildJaasConfig(userPassword.getLeft(), userPassword.getRight()));

        props.put("timeout.ms", settings.get("request_timeout_ms"));
        props.put("session.timeout.ms", settings.get("request_timeout_ms"));

        //load all kafka properties
        settings.getSettings().forEach((key, v) -> {
            if (key.startsWith("dstore.")) {
                String toPutKey = key.substring(7);
                props.setProperty(toPutKey, (String) v);
            }
        });

        return props;
    }

    public static Properties getProducerProperties(JsonNode config, Pair<String, String> userPassword) {
        Properties props = new Properties();

        initCommonClientProps(props);

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.get("bootstrap_servers").asText());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, getClientId("producer", config.get("dts_sid").asText()));
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.RETRIES_CONFIG, 0); //重试0次，避免数据重复
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, config.get("batch_size").asInt());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.get("buffer_memory").asInt()); //
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, Long.MAX_VALUE);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, config.get("request_timeout_ms").asInt());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, config.get("compression_type").asText());
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1024 * 1024 * 1024); // 1GB
        props.put(ProducerConfig.SEND_BUFFER_CONFIG,  config.get("send_buffer_bytes").asInt()); // 32MB
        props.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, config.get("receive_buffer_bytes").asInt()); // 32MB
        props.put(ProducerConfig.LINGER_MS_CONFIG, config.get("linger_ms").asLong());

        // enable leader switch interrupt.
        props.put("leaderSwitchInterrupt", "true");

        //protocol props
        props.putAll(propertiesByProtocol(config));

        props.put("timeout.ms", config.get("request_timeout_ms").asInt());
        props.put("session.timeout.ms", config.get("request_timeout_ms").asInt());

        return props;
    }

    private static void initCommonClientProps(Properties props) {
        props.put(CommonClientConfigs.RETRY_BACKOFF_MS_CONFIG, 100L);
        props.put(CommonClientConfigs.METADATA_MAX_AGE_CONFIG, 5 * 60 * 1000);
        props.put(CommonClientConfigs.SEND_BUFFER_CONFIG, 128 * 1024);
        props.put(CommonClientConfigs.RECONNECT_BACKOFF_MS_CONFIG, 100L);
        props.put(CommonClientConfigs.RECONNECT_BACKOFF_MAX_MS_CONFIG, 1000L);
        props.put(CommonClientConfigs.RECEIVE_BUFFER_CONFIG, 32 * 1024);
        props.put(CommonClientConfigs.REQUEST_TIMEOUT_MS_CONFIG, 40 * 1000);
    }

    private static String getClientId(String role, String jobId) {
        String localIp = "";
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return String.format("%s-%s-%s", role, jobId, localIp);
    }


    private static Map<?,?> propertiesByProtocol(JsonNode config) {
        final JsonNode protocolConfig = config.get("protocol");
        LOGGER.info("Kafka protocol config: {}", protocolConfig.toString());
        final KafkaProtocol protocol = KafkaProtocol.valueOf(protocolConfig.get("security_protocol").asText().toUpperCase());
        final ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object>builder()
                .put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, protocol.toString());

        switch (protocol) {
            case PLAINTEXT :
                break;
            case SASL_SSL:
            case SASL_PLAINTEXT :
                builder.put(SaslConfigs.SASL_JAAS_CONFIG, buildJaasConfig(config.get("dts_user").asText(), config.get("dts_password").asText()));
                builder.put(SaslConfigs.SASL_MECHANISM, protocolConfig.get("sasl_mechanism").asText());
                break;
            default :
                throw new RuntimeException("Unexpected Kafka protocol: " + Jsons.serialize(protocol));
        }

        return builder.build();
    }

    private static Object buildJaasConfig(String user, String password) {
        String jaasTemplate = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";";

        return String.format(jaasTemplate, user,  password);
    }

    public TogoProducer getProducer() {
        return producer;
    }

    public String getTopic() {
        return topic;
    }

    public Properties getProperties() {
        return properties;
    }

}
