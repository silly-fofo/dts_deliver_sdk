package com.aliyun.dts.deliver.core.runtime.tasks;

import com.aliyun.dts.deliver.base.Destination;
import com.aliyun.dts.deliver.base.DtsMessageConsumer;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.functional.Future;
import com.aliyun.dts.deliver.commons.functional.SwallowException;
import com.aliyun.dts.deliver.core.runtime.pipeline.DtsMessagePipeline;
import com.aliyun.dts.deliver.protocol.generated.DtsMessage;
import com.aliyun.dts.deliver.store.AbstractRecordStoreWithMetrics;

import java.util.List;

public class IncrementSinkTask extends SinkTask {
    /**
     * Construct a SinkTask object to handle sink task.
     *
     * @param settings
     * @param destination
     * @param recordStore
     * @param destPipeline
     */
    public IncrementSinkTask(Settings settings, Destination destination, AbstractRecordStoreWithMetrics recordStore, DtsMessagePipeline destPipeline, int groupKey) {
        super(settings, destination, recordStore, destPipeline, groupKey);
    }

    @Override
    protected void sinkRecords(Destination destination, AbstractRecordStoreWithMetrics recordStore, DtsMessagePipeline destPipeline, int batchSize, int groupKey) throws Exception {

        List<DtsMessage> dtsMessages =  recordStore.consume(batchSize, groupKey);

        if (null == dtsMessages) {
            if (recordStore.isEOF()) {
                // record store is EOF, and we can not get new data, just stop
                stop();
            }
            return;
        }

        inState("Write");

        List<DtsMessage> newRecords = DtsMessagePipeline.cookDtsMessages(dtsMessages, destPipeline);

        try {
            context.retry(
                    () -> {
                        try {
                            DtsMessageConsumer getConsumer = destination.getConsumer(settings, (c1, c2) -> recordStore.setCheckpoint(c1, c2));

                            //todo(yanmen) async
                            getConsumer.accept(newRecords);
                        } catch (Exception foo) {
                            /**
                             * error happened, try to close record range to release stale resources;
                             * otherwise, try to reuse previous resources
                             */
                            LOGGER.warn("Sink record failed, close record range, maybe try again", foo);
                            destination.close();
                            throw foo;
                        }
                    }, (e, t) -> destination.isRecoverable(e));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            SwallowException.callAndSwallowException(() -> destination.close());
            throw e;
        }
    }
}
