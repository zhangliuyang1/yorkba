package com.sjzl.york.core.model;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/11 18:14
 */
public class YorkbaException extends RuntimeException {
    public YorkbaException() {
        super();
    }

    public YorkbaException(String message) {
        super(message);
    }

    public YorkbaException(String message, Throwable cause) {
        super(message, cause);
    }

    public YorkbaException(Throwable cause) {
        super(cause);
    }

    protected YorkbaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
