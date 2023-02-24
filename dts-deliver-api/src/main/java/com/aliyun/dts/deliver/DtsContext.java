package com.aliyun.dts.deliver;

import com.aliyun.dts.deliver.commons.functional.ThrowableFunction;
import com.aliyun.dts.deliver.commons.functional.ThrowableFunctionVoid;

import java.util.function.BiFunction;

public interface DtsContext {
    /**
     * Call function with retry mechanism, the return value of function is void.
     */
    void retry(ThrowableFunctionVoid throwableFunctionVoid, BiFunction<Throwable, Integer, Boolean> recoverableChecker) throws Exception;

    /**
     * Call function with retry mechanism, the return value of function is T.
     */
    <T> T retry(ThrowableFunction<T> throwableFunction, BiFunction<Throwable, Integer, Boolean> recoverableChecker) throws Exception;
}
