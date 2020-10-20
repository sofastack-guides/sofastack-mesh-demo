package com.alipay.sofa.ms.spring.cloud.reservation.client.dto;

import java.util.Arrays;

/**
 * @author zxy
 * @date 2020-10-13 10:11
 **/
public enum RequestType {
    /**
     * 请求分类
     */
    NORMAL("1"), EXCEPTION("2"), TIMEOUT("3");

    public String getType() {
        return type;
    }

    private String type;

    RequestType(String type) {
        this.type = type;
    }

    public static RequestType findByType(String type) {
        return Arrays.stream(RequestType.values()).filter(x -> x.getType().equals(type)).findFirst().orElse(null);
    }
}
