/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return JSON.toJSONString( "hello world!");
    }

    @RequestMapping("/model")
    @ResponseBody
    public Model model(@RequestBody RequestModel model){
        System.out.println(model.toString());
        return new Model().setValue("hello world!");
    }

    @RequestMapping("/echo")
    public String echo(String message){
        System.out.println(message);
        return "[spring cloud] hello " + message + "!";
    }

    @RequestMapping("/{message}/echo")
    public String echo2(@PathVariable String message){
        System.out.println(message);
        return "[spring cloud] hello " + message + "!";
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