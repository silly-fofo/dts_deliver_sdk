package com.aliyun.dts.deliver.base;

import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.util.AutoCloseableIterator;
import com.aliyun.dts.deliver.protocol.generated.ConfiguredDtsCatalog;
import com.aliyun.dts.deliver.protocol.generated.DtsCatalog;
import com.aliyun.dts.deliver.protocol.generated.DtsMessage;
import com.fasterxml.jackson.databind.JsonNode;

public interface Source extends Integration {

    /**
     * Discover the current schema in the source.
     *
     * @param config - integration-specific configuration object as json. e.g. { "username": "Dts",
     *        "password": "super secure" }
     * @return Description of the schema.
     * @throws Exception - any exception.
     */
    DtsCatalog discover(JsonNode config) throws Exception;

    /**
     * Return a iterator of messages pulled from the source.
     *
     * @param config - integration-specific configuration object as json. e.g. { "username": "Dts",
     *        "password": "super secure" }
     * @param catalog - schema of the incoming messages.
     * @param state - state of the incoming messages.
     * @return {@link AutoCloseableIterator} that produces message. The iterator will be consumed until
     *         no records remain or until an exception is thrown. {@link AutoCloseableIterator#close()}
     *         will always be called once regardless of success or failure.
     * @throws Exception - any exception.
     */
    AutoCloseableIterator<DtsMessage> read(Settings config, ConfiguredDtsCatalog catalog, JsonNode state) throws Exception;

    /**
     * Determine if this exception happened in AnySource can be recovered. If so, the framework will retry the failed
     * routine.
     * @param throwable the exception to check
     * @return true if current exception can be retried
     */
    default boolean isRecoverable(Throwable throwable) {
        return false;
    }
}
