package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxy
 * @date 2020-10-22 15:57
 **/
@FeignClient(value = "reservation-service-zxy", path = "faultTolerance")
public interface FaultToleranceService {

    @RequestMapping(value = "/delayResponse", method = RequestMethod.GET)
    String delayResponse();
}
