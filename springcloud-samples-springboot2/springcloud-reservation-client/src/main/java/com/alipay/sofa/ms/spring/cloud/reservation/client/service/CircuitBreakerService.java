package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

/**
 * @author zxy
 * @date 2020-09-27 16:38
 **/

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "reservation-service-zxy",path = "circuitBreaker")
public interface CircuitBreakerService {

    /**
     * 获取service信息
     * @param executionTime 执行时间 单位毫秒
     * @param type 类型(1-正常,2-异常,3-超时)
     * @return service info
     */
    @RequestMapping(value = "/getServiceInfo", method = RequestMethod.GET)
    String getServiceInfo(@RequestParam("executionTime")Long executionTime, @RequestParam("type")String type);
}
