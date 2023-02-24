package com.aliyun.dts.deliver.commons.exceptions;

public interface ErrorCode {

    int SUCCESS = 0;

    /**
     * 不合理的参数，如下标超过范围，字符串为空
     */
    int COMMON_LIB_INVALID_PARAMETERS = 11004;

    /**
     * IO错误，如读写文件异常
     */
    int COMMON_LIB_IO_EXCEPTION = 11010;

    /**
     * 不合理的状态，如重复注册信号,30008
     */
    int FRAMEWORK_ILLEGAL_STATE = 31017; // illegal state

    /**
     * 从source读取数据异常,30010
     */
    int FRAMEWORK_READ_SOURCE_DATA = 31009;

    /**
     * the times retried exceeds the max retry times
     */
    int COMMON_LIB_EXCEED_MAX_RETRY_TIMES = 100047;

    /**
     * DB名映射失败
     */
    int COMMON_LIB_CREATE_DB_NAMES_MAPPER_ERROR = 10026;

    /**
     * 不支持的操作，如不支持某些数据类型,10020
     */
    int COMMON_LIB_UNSUPPORTED_OPERATION = 11005;
}
