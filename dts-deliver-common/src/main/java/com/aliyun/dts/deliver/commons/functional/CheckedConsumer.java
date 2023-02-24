package com.aliyun.dts.deliver.commons.functional;

@FunctionalInterface
public interface CheckedConsumer<T, E extends Throwable> {

    void accept(T t) throws E;

}