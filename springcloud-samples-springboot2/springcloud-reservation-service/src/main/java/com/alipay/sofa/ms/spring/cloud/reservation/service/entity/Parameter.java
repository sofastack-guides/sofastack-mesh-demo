package com.alipay.sofa.ms.spring.cloud.reservation.service.entity;

import java.io.Serializable;

public class Parameter implements Serializable {
    public String type;
    public Object value;
    public String getType() {
        return type;
    }
    public Parameter setType(String type) {
        this.type = type;
        return this;
    }
    public Object getValue() {
        return value;
    }
    public Parameter setValue(Object value) {
        this.value = value;
        return this;
    }
}