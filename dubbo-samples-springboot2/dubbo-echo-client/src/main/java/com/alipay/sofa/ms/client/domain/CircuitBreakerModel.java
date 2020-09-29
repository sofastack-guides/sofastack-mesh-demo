package com.alipay.sofa.ms.client.domain;

/**
 * @author zxy
 * @date 2020-09-28 14:43
 **/
public class CircuitBreakerModel {
    /**
     * 请求次数
     */
    private Long requestCount;
    /**
     * 错误比例
     */
    private Double errorPercent;

    /**
     * 平均RT时间
     */
    private Long averageRt;

    /**
     * 熔断恢复时间段
     */
    private Integer sleepWindow;

    /**
     * rpc超时时间
     */
    private Long rpcTimeout;

    /**
     * 唤醒请求的类型
     * (1-正常2-异常3-超时)
     */
    private String awakenRequestType;

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    public Double getErrorPercent() {
        return errorPercent;
    }

    public void setErrorPercent(Double errorPercent) {
        this.errorPercent = errorPercent;
    }

    public Integer getSleepWindow() {
        return sleepWindow;
    }

    public void setSleepWindow(Integer sleepWindow) {
        this.sleepWindow = sleepWindow;
    }

    public Long getRpcTimeout() {
        return rpcTimeout;
    }

    public void setRpcTimeout(Long rpcTimeout) {
        this.rpcTimeout = rpcTimeout;
    }

    public String getAwakenRequestType() {
        return awakenRequestType;
    }

    public void setAwakenRequestType(String awakenRequestType) {
        this.awakenRequestType = awakenRequestType;
    }

    public Long getAverageRt() {
        return averageRt;
    }

    public void setAverageRt(Long averageRt) {
        this.averageRt = averageRt;
    }
}
