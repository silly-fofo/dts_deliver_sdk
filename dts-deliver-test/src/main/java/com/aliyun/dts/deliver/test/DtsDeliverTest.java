package com.aliyun.dts.deliver.test;

import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.JobConfig;
import com.aliyun.dts.deliver.connector.desination.DStoreDestination;
import com.aliyun.dts.deliver.core.bootstrap.DtsDeliver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtsDeliverTest {

    public static void main(String[] args) throws Throwable  {

        String configPath = "";
        Map<String, String> settingValueMap = new HashMap<>();

        settingValueMap.put(GlobalSettings.DTS_BOOTSTRAP_SERVERS_CONFIG.getKey(), "dts_proxy_url");
        settingValueMap.put(GlobalSettings.ALIYUN_AK.getKey(), "ak");
        settingValueMap.put(GlobalSettings.ALIYUN_SECRET.getKey(), "secret");
        settingValueMap.put(GlobalSettings.DTS_INSTANCE_ID.getKey(), "dts_instance_id");
        settingValueMap.put(GlobalSettings.DTS_DELIVER_TOPIC.getKey(), "topic");
        settingValueMap.put(GlobalSettings.DTS_DELIVER_TOPIC_PARTITION_NUM.getKey(), "3");

        JobConfig jobConfig = new JobConfig(configPath, settingValueMap);

        //source


        List<Source> sourceList = new ArrayList<>();
        Source source1 = new FakeSource("source 1", jobConfig.getSettings());
        Source source2 = new FakeSource("source 2", jobConfig.getSettings());
        Source source3 = new FakeSource("source 3", jobConfig.getSettings());
        sourceList.add(source1);
        sourceList.add(source2);
        sourceList.add(source3);

        Destination destination = new DStoreDestination();

        DtsDeliver dtsDeliver = new DtsDeliver(jobConfig, sourceList, destination);

        dtsDeliver.startup();
    }
}
