/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.controller;

import com.alipay.sofa.ms.spring.cloud.reservation.service.BenchmarkService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiji@apache.org
 * @version : HelloWorldController.java, v 0.1 2020年02月26日 8:32 下午 yiji Exp $
 */
@RestController
@RequestMapping("/reservations")
public class BenchmarkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkController.class);
    @Autowired
    private BenchmarkService benchmarkService;

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello world!";
    }


    @GetMapping("/send")
    public String send() {
        try {
            return benchmarkService.send();
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_512_byte")
    public String send_512_byte() {
        try {
            return benchmarkService.send_512_byte(randomString(512));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_512_byte, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_1k")
    public String send_1k() {
        try {
            return benchmarkService.send_1k(randomString(1024));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_1k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_2k")
    public String send_2k() {
        try {
            return benchmarkService.send_2k(randomString(1024 * 2));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_2k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_4k")
    public String send_4k() {
        try {
            return benchmarkService.send_4k(randomString(1024 * 4));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_4k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    @GetMapping("/send_8k")
    public String send_8k() {
        try {
            return benchmarkService.send_8k(randomString(1024 * 8));
        } catch (Exception e) {
            LOGGER.error("Failed to invoke send_8k, cause: " + e);
            return "ERROR:" + e.getMessage();
        }
    }

    static char[] chars = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z',
    };

    static String randomString(int len) {
        return RandomStringUtils.random(len, 0, chars.length, true, true, chars);
    }
}