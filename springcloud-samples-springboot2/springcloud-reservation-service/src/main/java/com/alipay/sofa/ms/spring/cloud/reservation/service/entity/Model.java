package com.alipay.sofa.ms.spring.cloud.reservation.service.entity;

import java.io.Serializable;
import java.util.Map;

public class Model implements Serializable {
    public Map<String, String> attachments;
    public Object value;
    public String exception;

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public Model setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Model setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getException() {
        return exception;
    }

    public Model setException(String exception) {
        this.exception = exception;
        return this;
    }

    @Override
    public String toString() {
        return "Model{" +
                "attachments=" + attachments +
                ", value=" + value +
                ", exception='" + exception + '\'' +
                '}';
    }
}