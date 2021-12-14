/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import com.alipay.sofa.ms.spring.cloud.reservation.service.entity.Model;
import com.alipay.sofa.ms.spring.cloud.reservation.service.entity.RequestModel;
import com.alipay.sofa.ms.spring.cloud.reservation.service.entity.SubReq;
import org.springframework.web.bind.annotation.*;

/**
 * @author yiji@apache.org
 * @version : HelloWorldController.java, v 0.1 2020年02月26日 8:32 下午 yiji Exp $
 */
@RestController
@RequestMapping("/reservations")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @RequestMapping("/echo")
    @ResponseBody
    public Model echo(@RequestBody RequestModel model){
        System.out.println(model.toString());
        return new Model().setValue("hello world!");
    }

    @RequestMapping("/add")
    public String add(@RequestParam int a, @RequestParam int b ) {
        return String.valueOf(a + b);
    }

    @RequestMapping("/sub")
    public String sub(@RequestBody SubReq subReq) {
        return String.valueOf(subReq.a - subReq.b);
    }
}