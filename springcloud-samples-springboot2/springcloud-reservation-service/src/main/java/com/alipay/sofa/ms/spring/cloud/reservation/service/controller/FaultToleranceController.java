package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import com.alipay.sofa.ms.spring.cloud.reservation.service.govern.SpringCloudFaultToleranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxy
 * @date ：2020/12/14 2:59 下午
 */
@RestController
@RequestMapping("faultTolerance")
public class FaultToleranceController {

    @Autowired
    private SpringCloudFaultToleranceService service;



    @GetMapping("echo")
    public String echo1(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "sleep", required = false) Integer sleep,
                        @RequestParam(value = "exceptionIp", required = false) String exceptionIp,
                        @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service.echo(name, exception, sleep,exceptionIp);
    }

    @GetMapping("echo1")
    public String echo2(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "sleep", required = false) Integer sleep,
                        @RequestParam(value = "exceptionIp", required = false) String exceptionIp,
                        @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service.echo1(name, exception, sleep,exceptionIp);
    }

}
