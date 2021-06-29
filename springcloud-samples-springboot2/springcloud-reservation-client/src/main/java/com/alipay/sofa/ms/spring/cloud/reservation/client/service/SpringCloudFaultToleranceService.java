package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zxy
 * @date 2020-12-11
 */
@FeignClient(value = "springcloud-server-zxy", path = "faultTolerance", configuration =
        ReservationService.CustomerClientConfiguration.class)
public interface SpringCloudFaultToleranceService {

    /**
     * echo
     *
     * @param name      请求名称
     * @param exception 是否抛出异常
     * @param sleep     sleep时间
     * @param exceptionIp 异常ip
     * @return 信息
     */
    @RequestMapping("/echo")
    String echo(@RequestParam(value = "name") String name, @RequestParam(value = "exception") Boolean exception,
                @RequestParam(value = "sleep") Integer sleep,@RequestParam(value = "exceptionIp") String exceptionIp);

    /**
     * echo1
     *
     * @param name      请求名称
     * @param exception 是否抛出异常
     * @param sleep     sleep时间
     * @param exceptionIp 异常ip
     * @return 信息
     */
    @RequestMapping("/echo1")
    String echo1(@RequestParam(value = "name") String name, @RequestParam(value = "exception") Boolean exception,
                @RequestParam(value = "sleep") Integer sleep,@RequestParam(value = "exceptionIp") String exceptionIp);
}
