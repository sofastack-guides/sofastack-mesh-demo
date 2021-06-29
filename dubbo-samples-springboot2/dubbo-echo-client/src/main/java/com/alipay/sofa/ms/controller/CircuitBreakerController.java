/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.DubboCircuitBreakerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxy
 * @date 2020-12-11
 */
@RestController
@RequestMapping(path = "circuitBreaker")
public class CircuitBreakerController {
    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @Autowired
    private DubboCircuitBreakerService dubboCircuitBreakerService;

    @GetMapping(path = "echo")
    public String echo(@RequestParam(value = "name", required = false, defaultValue = "dubbo") String name,
                       @RequestParam(value = "methodName", required = false, defaultValue = "echo") String methodName,
                       @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                       @RequestParam(value = "sleep", required = false, defaultValue = "0") Integer sleep,
                       @RequestParam(value = "exception", required = false) Boolean exception
    ) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String response = "";
            try {
                if ("echo1".equals(methodName)) {
                    response = dubboCircuitBreakerService.echo1(name, exception, sleep);
                } else {
                    response = dubboCircuitBreakerService.echo(name, exception, sleep);
                }
                logger.info(response);
            } catch (Exception e) {
                response = e.getMessage();
                Throwable cause = e.getCause();
                if (cause != null && StringUtils.isNotBlank(cause.getMessage())) {
                    response += cause.getMessage();
                }
            }
            result.append("第 ").append(i + 1).append("次调用，sleep ").append(sleep).append(" 毫秒， 结果：").append(response).append("<br>");
        }

        return result.toString();
    }

}