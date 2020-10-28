package com.alipay.sofa.ms.client.dto;

/**
 * @author zxy
 * @date 2020-09-29 13:56
 **/

public class CircuitBreakerResponse {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public CircuitBreakerResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CircuitBreakerResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
