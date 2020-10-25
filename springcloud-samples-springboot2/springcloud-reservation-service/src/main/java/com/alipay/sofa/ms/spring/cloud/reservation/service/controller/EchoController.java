/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiji@apache.org
 * @version : HelloWorldController.java, v 0.1 2020年02月26日 8:32 下午 yiji Exp $
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @Autowired
    private ConfigController configController;

    @RequestMapping("/")
    public String echoAll() {
        configController.execute("echoAll");
        return "hello world!";
    }

    @RequestMapping("/name/{name}")
    public String echoPath(@PathVariable("name") String name) {
        configController.execute("echoPath");
        return "hello world! " + name;
    }

    @RequestMapping(value = "/name")
    public String echoParam(@RequestParam(value = "name") String name) {
        configController.execute("echoParam");
        return "hello world! " + name;
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public String echoPost(@RequestBody TestBean testBean) {
        configController.execute("echoPost");
        return "hello world! " + (testBean != null ? testBean.getName() : "");
    }
}