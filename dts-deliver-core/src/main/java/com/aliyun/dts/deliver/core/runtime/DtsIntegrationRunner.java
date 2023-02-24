package com.aliyun.dts.deliver.core.runtime;

import com.aliyun.dts.deliver.DtsContext;
import com.aliyun.dts.deliver.DtsMessageInterceptor;
import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.etl.ETLInstance;
import com.aliyun.dts.deliver.core.runtime.pipeline.DtsMessagePipeline;
import com.aliyun.dts.deliver.core.runtime.tasks.SinkTask;
import com.aliyun.dts.deliver.core.runtime.tasks.SourceTask;
import com.aliyun.dts.deliver.core.runtime.tasks.TaskManager;
import com.aliyun.dts.deliver.core.runtime.tasks.TaskSubmitter;
import com.aliyun.dts.deliver.store.AbstractRecordStoreWithMetrics;
import com.aliyun.dts.deliver.store.memory.MemoryRecordStore;
import org.apache.kafka.common.metrics.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DtsIntegrationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DtsIntegrationRunner.class);

    private final Settings settings;

    private final ETLInstance etlInstance;

    private final TaskSubmitter taskSubmitter;

    private AbstractRecordStoreWithMetrics recordStore;

    private AtomicReference<Throwable> errorRef;

    private final Metrics metrics;

    private Destination destination;
    private List<Source> sourceList;
    private DtsContext context;


    private int topicPartitionNum;

    public DtsIntegrationRunner(Settings settings, Metrics metrics, Destination destination, List<Source> sourceList, DtsContext context) {
        this.settings = settings;
        this.metrics = metrics;
        this.etlInstance = new ETLInstance(settings);
        this.destination = destination;
        this.sourceList = sourceList;
        this.context = context;

        this.topicPartitionNum = GlobalSettings.DTS_DELIVER_TOPIC_PARTITION_NUM.getValue(settings);

        taskSubmitter = new TaskSubmitter(etlInstance, settings, metrics, context);

        this.recordStore = taskSubmitter.getRecordStore().orElseGet(() -> new MemoryRecordStore(metrics, context));

    }

    private DtsMessagePipeline buildSourceMessagePipeline(Source source) {
        DtsMessagePipeline rs = null;

        List<DtsMessageInterceptor> interceptors = source.recordInterceptors(metrics);

        if (!interceptors.isEmpty()) {
            rs = new DtsMessagePipeline(interceptors);
        }

        return rs;
    }

    private DtsMessagePipeline buildDestinationMessagePipeline(Destination destination) {
        DtsMessagePipeline rs = null;

        List<DtsMessageInterceptor> interceptors = destination.recordInterceptors(metrics);

        if (!interceptors.isEmpty()) {
            rs = new DtsMessagePipeline(interceptors);
        }

        return rs;
    }

    public void start() {

        LOGGER.info("start source and dest task");
        for(Source source : sourceList) {
            DtsMessagePipeline sourceMessagePipeline = buildSourceMessagePipeline(source);
            taskSubmitter.submitSourceTask(recordStore, source, sourceMessagePipeline);
        }

        for (int i = 0; i < topicPartitionNum; i++) {
            DtsMessagePipeline destinationMessagePipeline = buildDestinationMessagePipeline(destination);

            taskSubmitter.submitSinkTask(recordStore, destination, destinationMessagePipeline, i);
        }
    }
}
