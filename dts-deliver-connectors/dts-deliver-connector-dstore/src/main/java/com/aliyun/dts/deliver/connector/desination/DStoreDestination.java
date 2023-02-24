package com.aliyun.dts.deliver.connector.desination;

import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.DtsMessageConsumer;
import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.openapi.DtsOpenApi;
import com.aliyun.dts.deliver.connector.BaseConnector;
import com.aliyun.dts.deliver.protocol.generated.ConfiguredDtsCatalog;
import com.aliyun.dts.deliver.protocol.generated.DtsConnectionStatus;
import com.aliyun.dts.deliver.protocol.generated.DtsMessage;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DStoreDestination extends BaseConnector implements Destination {

    @Override
    //todo(yanmen)
    public DtsConnectionStatus check(JsonNode config) throws Exception {
        return new DtsConnectionStatus().withStatus(DtsConnectionStatus.Status.SUCCEEDED);
    }

    @Override
    public void close() {
    }

    @Override
    public DtsMessageConsumer getConsumer(Settings settings, BiConsumer<String, Long> checkpointHandler) throws Exception {

        DtsOpenApi dtsOpenApi = new DtsOpenApi(
                GlobalSettings.ALIYUN_AK.getValue(settings),
                GlobalSettings.ALIYUN_SECRET.getValue(settings),
                GlobalSettings.DTS_INSTANCE_ID.getValue(settings)
        );

        Pair<String, String> userPassword = dtsOpenApi.getUserPassword();

        return new DStoreRecordConsumer(DStoreDestinationConfig.getDStoreDestinationConfig(settings, userPassword), checkpointHandler);
    }

    @Override
    public Boolean isRecoverable(Throwable e) {
        return false;
    }
}
