/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.BenchmarkService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yiji
 * @version : BenchmarkController.java, v 0.1 2020年04月01日 10:09 下午 yiji Exp $
 */
@RestController
public class BenchmarkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkController.class);

    @Resource
    private BenchmarkService benchmarkService;

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

    @GetMapping("/warm_up_random")
    public String warm_up_random(@RequestParam(name = "loop", required = false) Integer loops,
                                 @RequestParam(name = "bytes", required = false) Integer bytes) {
        int len = bytes == null || bytes <= 0 ? 512 : bytes;
        int loop = loops == null || loops <= 0 ? 10000 : loops;
        String lastLoop = "null";
        for (int i = 0; i <= loop; i++) {
            lastLoop = randomString(len);
        }

        return lastLoop;
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