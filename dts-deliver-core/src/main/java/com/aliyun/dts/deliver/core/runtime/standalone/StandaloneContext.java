package com.aliyun.dts.deliver.core.runtime.standalone;

import com.aliyun.dts.deliver.DtsContext;
import com.aliyun.dts.deliver.commons.config.GlobalSettings;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.functional.ThrowableFunction;
import com.aliyun.dts.deliver.commons.functional.ThrowableFunctionVoid;
import com.aliyun.dts.deliver.commons.util.RetryUtil;
import com.aliyun.dts.deliver.commons.util.RetryUtil.RetryInfo;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class StandaloneContext {
    private DefaultStandaloneContext defaultContext;

    private SourceStandaloneContext sourceContext;
    private SinkStandaloneContext sinkContext;

    private Settings settings;

    public StandaloneContext(Settings settings) {
        this.settings = settings;
    }

    public synchronized DtsContext getDefaultContext() {
        if (null == this.defaultContext) {
            this.defaultContext = new DefaultStandaloneContext();
        }
        return this.defaultContext;
    }

    public synchronized DtsContext getSourceContext() {
        if (null == this.sourceContext) {
            this.sourceContext = new SourceStandaloneContext();
        }
        return this.sourceContext;
    }

    public synchronized DtsContext getSinkContext() {
        if (null == this.sinkContext) {
            this.sinkContext = new SinkStandaloneContext();
        }
        return this.sinkContext;
    }

    abstract class BaseStandaloneContext implements DtsContext {
        private volatile RetryInfo retryInfoRef;
        protected RetryUtil retryUtil;

        protected final void initializeRetryUtil(String globalJobType, String objectNameToRetry) {
            int intervalSeconds = GlobalSettings.RETRY_SLEEP_SECONDS.getValue(settings);
            int maxRetrySeconds = GlobalSettings.MAX_RETRY_SECONDS.getValue(settings);
            int blindSeconds = GlobalSettings.RETRY_BLIND_SECONDS.getValue(settings);

            this.retryUtil = new RetryUtil(globalJobType, objectNameToRetry, intervalSeconds, TimeUnit.SECONDS,
                    blindSeconds / intervalSeconds, maxRetrySeconds / intervalSeconds, maxRetrySeconds, (e) -> true);
        }

        @Override
        public void retry(ThrowableFunctionVoid throwableFunctionVoid, BiFunction<Throwable, Integer, Boolean> checker) throws Exception {
            retryUtil.callFunctionWithRetry(throwableFunctionVoid, checker, this::setRetryInfo);
        }

        @Override
        public <T> T retry(ThrowableFunction<T> throwableFunction, BiFunction<Throwable, Integer, Boolean> checker) throws Exception {
            return retryUtil.callFunctionWithRetry(throwableFunction, checker, this::setRetryInfo);
        }

        private void setRetryInfo(RetryInfo retryInfo, Long expiredTime) {
            this.retryInfoRef = retryInfo;
            this.retryInfoRef.setExpiredTimestamp(expiredTime);
        }

    }

    class DefaultStandaloneContext extends BaseStandaloneContext {
    }

    class SourceStandaloneContext extends BaseStandaloneContext {

    }

    class SinkStandaloneContext extends BaseStandaloneContext {

    }

}

