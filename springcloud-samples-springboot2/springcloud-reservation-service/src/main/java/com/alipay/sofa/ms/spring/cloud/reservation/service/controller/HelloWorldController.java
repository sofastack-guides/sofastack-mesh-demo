/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}