package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import com.alipay.sofa.ms.spring.cloud.reservation.service.govern.SpringCloudDowngradeService;
import com.alipay.sofa.ms.spring.cloud.reservation.service.govern.SpringCloudDowngradeService1;
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
@RequestMapping("downgrade")
public class DowngradeController {
    @Autowired
    private SpringCloudDowngradeService service;

    @Autowired
    private SpringCloudDowngradeService1 service1;

    @GetMapping("service/echo")
    public String echo1(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "sleep", required = false) Integer sleep,
                       @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service.echo(name, exception, sleep);
    }

    @GetMapping("service/echo1")
    public String echo2(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "sleep", required = false) Integer sleep,
                        @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service.echo1(name, exception, sleep);
    }

    @GetMapping("service1/echo")
    public String echo3(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "sleep", required = false) Integer sleep,
                       @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service1.echo(name, exception, sleep);
    }

    @GetMapping("service1/echo1")
    public String echo4(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "sleep", required = false) Integer sleep,
                        @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        return service1.echo1(name, exception, sleep);
    }

}
