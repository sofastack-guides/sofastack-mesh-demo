package com.alipay.sofa.ms.client.domain;

/**
 * @author zxy
 * @date 2020-09-28 14:55
 **/
public enum CircuitBreakerResponseType {
    /**
     * 响应类型
     * 1-没有触发熔断
     * 2-触发了熔断,熔断后发送正常请求,熔断关闭
     * 3-触发了熔断,熔断后发送异常请求,熔断未关闭
     * 4-触发了熔断,熔断后发送超时请求,熔断未关闭
     */
    NO_BREAKER("c201","没有触发熔断"),
    BREAKER_NORMAL_CLOSE("c202","触发了熔断,熔断后发送正常请求,熔断关闭"),
    BREAKER_EXCEPTION_OPEN("c203","触发了熔断,熔断后发送异常请求,熔断未关闭"),
    BREAKER_TIMEOUT_OPEN("c204","触发了熔断,熔断后发送超时请求,熔断未关闭"),

    BREAKER_NORMAL_OPEN("c502","触发了熔断,熔断后发送正常请求,熔断未关闭"),
    BREAKER_EXCEPTION_CLOSE("c503","触发了熔断,熔断后发送异常请求,熔断关闭"),
    BREAKER_TIMEOUT_CLOSE("c504","触发了熔断,熔断后发送超时请求,熔断关闭")
    ;
    private String code;
    private String message;
    CircuitBreakerResponseType(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
