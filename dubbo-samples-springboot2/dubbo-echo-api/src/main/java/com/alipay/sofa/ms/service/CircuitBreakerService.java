package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.exception.RequestException;

/**
 * @author zxy
 * @date 2020-09-27 16:38
 **/
public interface CircuitBreakerService {

    /**
     * 获取service信息
     * @param sleepTime 睡眠时间 单位毫秒
     * @param exception 是否异常
     * @throws RequestException 请求异常
     * @return service info
     */
    String getServiceInfo(Long sleepTime,Boolean exception) throws RequestException;
}
