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
    NO_BREAKER(201,"没有触发熔断"),
    BREAKER_NORMAL_CLOSE(202,"触发了熔断,熔断后发送正常请求,熔断关闭"),
    BREAKER_EXCEPTION_OPEN(203,"触发了熔断,熔断后发送异常请求,熔断未关闭"),
    BREAKER_TIMEOUT_OPEN(204,"触发了熔断,熔断后发送超时请求,熔断未关闭"),

    BREAKER_NORMAL_OPEN(502,"触发了熔断,熔断后发送正常请求,熔断未关闭"),
    BREAKER_EXCEPTION_CLOSE(503,"触发了熔断,熔断后发送异常请求,熔断关闭"),
    BREAKER_TIMEOUT_CLOSE(504,"触发了熔断,熔断后发送超时请求,熔断关闭")
    ;
    private Integer code;
    private String message;
    CircuitBreakerResponseType(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
