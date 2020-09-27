package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zxy
 * @date 2020-09-09 17:34
 **/
@FeignClient(value = "springcloud-reservation-service",path = "/benchmark", configuration = ReservationService.CustomerClientConfiguration.class,fallback =BenchmarkServiceFallback.class )
public interface BenchmarkService {
    @RequestMapping(value = "request", method = RequestMethod.POST)
    Response request(Request req);

    @RequestMapping(value = "clearAndReset", method = RequestMethod.POST)
    Response clearAndReset();

}
