package com.aliyun.dts.deliver.commons.config;

public interface GlobalSettings {
    Settings.Setting<Integer> MAX_RETRY_SECONDS = Settings.integerSetting(
            "maxRetryTime", "max retry seconds for source/sink functions", 12 * 3600);
    Settings.Setting<Integer> RETRY_BLIND_SECONDS = Settings.integerSetting(
            "retry.blind.seconds", "blind retry seconds, this value must smaller than maxRetryTime", 10 * 60);
    Settings.Setting<Integer> RETRY_SLEEP_SECONDS = Settings.integerSetting(
            "retry.sleep.seconds", "sleep for some seconds before next retry for source/sink functions", 10);

    public static final Settings.Setting<Integer> SINK_TASK_RECORD_MAX_BATCH_SIZE = GlobalSettings.SINK_TASK_RECORD_MAX_BATCH_SIZE;


    Settings.Setting<String> ALIYUN_AK = Settings.stringSetting(
            "aliyun.ak", "the aliyun ak to get real user password and dbList");
    Settings.Setting<String> ALIYUN_SECRET = Settings.stringSetting(
            "aliyun.secret", "the aliyun secret to get real user password and dbList");

    Settings.Setting<String> DTS_INSTANCE_ID = Settings.stringSetting(
            "dts.instance.id", "dts instance id");

    Settings.Setting<String> DTS_DELIVER_TOPIC = Settings.stringSetting(
            "dts.deliver.topic", "dts deliver topic");

    Settings.Setting<String> DTS_BOOTSTRAP_SERVERS_CONFIG = Settings.stringSetting(
            "dts.bootstrap.servers", "dts deliver url, usually dproxy");

    Settings.Setting<Integer> DTS_DELIVER_TOPIC_PARTITION_NUM = Settings.integerSetting(
            "dts.deliver.topic.partition.num", "dts deliver topic partition num", 0);

    Settings.Setting<String> DB_MAPPER_JSON_EXPRESSIONS =
            Settings.stringSetting("dts.datamove.mapper.expressions",
                    "The expressions used to mapper db, table and columen", "");
}
