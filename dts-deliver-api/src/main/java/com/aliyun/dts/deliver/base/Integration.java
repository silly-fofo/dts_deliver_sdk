package com.aliyun.dts.deliver.base;

import com.aliyun.dts.deliver.DtsMessageInterceptor;
import com.aliyun.dts.deliver.protocol.generated.ConnectorSpecification;
import com.aliyun.dts.deliver.protocol.generated.DtsConnectionStatus;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collections;
import java.util.List;

public interface Integration {

    /**
     * Fetch the specification for the integration.
     *
     * @return specification.
     * @throws Exception - any exception.
     */
    ConnectorSpecification spec() throws Exception;

    /**
     * Check whether, given the current configuration, the integration can connect to the integration.
     *
     * @param config - integration-specific configuration object as json. e.g. { "username": "airbyte",
     *        "password": "super secure" }
     * @return Whether or not the connection was successful. Optional message if it was not.
     * @throws Exception - any exception.
     */
    DtsConnectionStatus check(JsonNode config) throws Exception;


    /**
     * Close the source, which means to release resources related, and cleanup the context
     *
     */
    void close();


    /**
     * Get the record interceptors used to intercept records before putting in record-store, or intercept records
     * after polling from record-store.
     * If current processor works as Source mode, the interceptors should be inserted before record store; otherwise,
     * that should be inserted after record store.
     * @return record filters
     */
    default List<DtsMessageInterceptor> recordInterceptors() {
        return Collections.emptyList();
    }
}

