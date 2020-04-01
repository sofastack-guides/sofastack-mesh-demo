/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.BenchmarkService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yiji
 * @version : BenchmarkController.java, v 0.1 2020年04月01日 10:09 下午 yiji Exp $
 */
@RestController
public class BenchmarkController {

    @Resource
    private BenchmarkService benchmarkService;

    @GetMapping("/send_512_byte")
    public String send_512_byte() {
        return benchmarkService.send_512_byte(RandomStringUtils.randomAlphanumeric(512));
    }

    @GetMapping("/send_1k")
    public String send_1k() {
        return benchmarkService.send_1k(RandomStringUtils.randomAlphanumeric(1024));
    }

    @GetMapping("/send_2k")
    public String send_2k() {
        return benchmarkService.send_512_byte(RandomStringUtils.randomAlphanumeric(1024 * 2));
    }

    @GetMapping("/send_4k")
    public String send_4k() {
        return benchmarkService.send_512_byte(RandomStringUtils.randomAlphanumeric(1024 * 4));
    }

    @GetMapping("/send_8k")
    public String send_8k() {
        return benchmarkService.send_512_byte(RandomStringUtils.randomAlphanumeric(1024 * 8));
    }

}