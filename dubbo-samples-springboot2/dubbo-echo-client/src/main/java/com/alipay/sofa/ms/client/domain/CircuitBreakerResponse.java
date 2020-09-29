package com.alipay.sofa.ms.client.domain;

/**
 * @author zxy
 * @date 2020-09-29 13:56
 **/

public class CircuitBreakerResponse {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public CircuitBreakerResponse setCode(Integer code) {
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
