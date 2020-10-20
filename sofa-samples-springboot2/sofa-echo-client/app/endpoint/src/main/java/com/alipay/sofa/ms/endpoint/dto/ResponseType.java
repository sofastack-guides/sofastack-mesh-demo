package com.alipay.sofa.ms.endpoint.dto;

/**
 * @author zxy
 * @date 2020-10-13 10:17
 **/
public enum ResponseType {
    /**
     * 响应类型
     */
    NORMAL("1"), EXCEPTION("2"), TIMEOUT("3"), CIRCUIT_BREAKER("4");
    private String type;

    ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
