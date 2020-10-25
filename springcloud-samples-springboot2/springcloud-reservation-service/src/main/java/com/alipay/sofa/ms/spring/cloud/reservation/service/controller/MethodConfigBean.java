/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

/**
 *
 * @author xingqi
 * @version $Id: TestBean.java, v 0.1 2020年10月25日 3:08 PM xingqi Exp $
 */
public class MethodConfigBean {
    private String methodName;

    private boolean isException;

    private int timeout;

    public String getMethodName() {
        return methodName;
    }

    public MethodConfigBean setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public boolean isException() {
        return isException;
    }

    public MethodConfigBean setException(boolean exception) {
        isException = exception;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public MethodConfigBean setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
}