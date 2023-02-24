package com.aliyun.dts.deliver.base;

import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.protocol.generated.ConfiguredDtsCatalog;
import com.aliyun.dts.deliver.protocol.generated.DtsMessage;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.aliyun.dts.deliver.commons.json.Jsons;

public interface Destination extends Integration {

    /**
     * Return a consumer that writes messages to the destination.
     *
     * @param settings - integration-specific configuration object as json. e.g. { "username": "airbyte",
     *        "password": "super secure" }
     * @return Consumer that accepts message.
     *         will be called n times where n is the number of messages.
     *         {@link DtsMessageConsumer#close()} will always be called once regardless of success
     *         or failure.
     * @throws Exception - any exception.
     */
    DtsMessageConsumer getConsumer(Settings settings, BiConsumer<String, Long> checkpointHandler)
            throws Exception;

    static void defaultOutputRecordCollector(final DtsMessage message) {
        System.out.println(Jsons.serialize(message));
    }

    Boolean isRecoverable(Throwable e);
}
