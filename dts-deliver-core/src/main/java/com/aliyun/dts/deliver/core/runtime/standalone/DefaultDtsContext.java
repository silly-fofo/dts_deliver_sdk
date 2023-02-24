package com.aliyun.dts.deliver.core.runtime.standalone;

import com.aliyun.dts.deliver.DtsContext;
import com.aliyun.dts.deliver.commons.functional.ThrowableFunction;
import com.aliyun.dts.deliver.commons.functional.ThrowableFunctionVoid;

import java.util.function.BiFunction;

public class DefaultDtsContext implements DtsContext {
    @Override
    public void retry(ThrowableFunctionVoid throwableFunctionVoid, BiFunction<Throwable, Integer, Boolean> recoverableChecker) throws Exception {

    }

    @Override
    public <T> T retry(ThrowableFunction<T> throwableFunction, BiFunction<Throwable, Integer, Boolean> recoverableChecker) throws Exception {
        return null;
    }
}
