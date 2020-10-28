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
     * @param type 请求类型
     * @see com.alipay.sofa.ms.dto.RequestType
     * @param timeout 请求超时时间
     * @return service info
     */
    String getServiceInfo(Long sleepTime,String type,Integer timeout);

}
