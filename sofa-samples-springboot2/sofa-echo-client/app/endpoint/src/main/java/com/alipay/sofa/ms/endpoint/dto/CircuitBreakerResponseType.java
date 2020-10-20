package com.alipay.sofa.ms.endpoint.dto;

import java.util.Arrays;
import java.util.Objects;

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
    NO_BREAKER("c201", "没有触发熔断", null, null),
    BREAKER_NORMAL_CLOSE("c202", "触发了熔断,熔断后发送正常请求,熔断关闭", "1", false),
    BREAKER_EXCEPTION_OPEN("c203", "触发了熔断,熔断后发送异常请求,熔断未关闭", "2", true),
    BREAKER_TIMEOUT_OPEN("c204", "触发了熔断,熔断后发送超时请求,熔断未关闭", "3", true),

    BREAKER_NORMAL_OPEN("c502", "触发了熔断,熔断后发送正常请求,熔断未关闭", "1", true),
    BREAKER_EXCEPTION_CLOSE("c503", "触发了熔断,熔断后发送异常请求,熔断关闭", "2", false),
    BREAKER_TIMEOUT_CLOSE("c504", "触发了熔断,熔断后发送超时请求,熔断关闭", "3", false);
    /**
     * 返回code
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 熔断状态
     */
    private Boolean circuitBreakerOpened;


    CircuitBreakerResponseType(String code, String message, String requestType, Boolean circuitBreakerOpened) {
        this.code = code;
        this.message = message;
        this.requestType = requestType;
        this.circuitBreakerOpened = circuitBreakerOpened;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestType() {
        return requestType;
    }

    public Boolean getCircuitBreakerOpened() {
        return circuitBreakerOpened;
    }

    public static CircuitBreakerResponseType match(String requestType, Boolean circuitBreakerOpened) {
        return Arrays.stream(CircuitBreakerResponseType.values())
                .filter(x -> Objects.equals(requestType, x.getRequestType()) & Objects.equals(x.getCircuitBreakerOpened(), circuitBreakerOpened))
                .findFirst().orElse(null);
    }
}
