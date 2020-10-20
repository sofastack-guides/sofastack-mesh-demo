package com.alipay.sofa.ms.endpoint.dto;

/**
 * @author zxy
 * @date 2020-09-28 14:43
 **/
public class CircuitBreakerModel {

    /**
     * 熔断窗口(s)
     */
    private Integer totalMetricWindow;

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

    public Integer getTotalMetricWindow() {
        return totalMetricWindow;
    }

    public void setTotalMetricWindow(Integer totalMetricWindow) {
        this.totalMetricWindow = totalMetricWindow;
    }
}
