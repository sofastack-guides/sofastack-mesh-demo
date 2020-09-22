package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import com.alipay.sofa.ms.spring.cloud.reservation.service.service.BenchmarkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxy
 * @date 2020-09-09 17:20
 **/
@RestController
@RequestMapping("/benchmark")
public class BenchmarkController {

    Logger logger = Logger.getLogger(BenchmarkController.class);

    @Autowired
    private BenchmarkService service;

    @RequestMapping(value = "/request",method = RequestMethod.POST)
    public Response request(@RequestBody Request request) {

        request.updateRouteRecord();

        logger.info("received request " + request);
        return service.request(request);
    }

    @RequestMapping(value = "/clearAndReset",method = RequestMethod.POST)
    public Response clearAndReset() {
        logger.info("received clearAndReset invoke");
        return service.clearAndReset();
    }


}
