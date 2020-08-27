package com.alipay.sofa.ms.service;

import java.io.Serializable;

public class Response implements Serializable {

    boolean success;

    Throwable throwable;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return "success=" + success;
    }
}