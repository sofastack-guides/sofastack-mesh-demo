package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.DubboFaultInjectService;
import com.alipay.sofa.ms.service.DubboFaultInjectService1;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxy
 * @date ：2021/1/27 4:04 下午
 */
@RestController
@RequestMapping("faultInject")
public class FaultInjectController {
    private static final Logger logger = LoggerFactory.getLogger(FaultInjectController.class);

    @Autowired
    private DubboFaultInjectService faultInjectService;

    @Autowired
    private DubboFaultInjectService1 faultInjectService1;

    @GetMapping("/request")
    public String request(@RequestParam(value = "name", required = false, defaultValue = "faultInject") String name,
                          @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                          @RequestParam(value = "sleep", required = false, defaultValue = "0") Integer sleep,
                          @RequestParam(value = "exception", required = false) Boolean exception,
                          @RequestParam(value = "service", required = false, defaultValue = "service") String service,
                          @RequestParam(value = "method", required = false, defaultValue = "echo") String method
    ) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String response = "";
            long start=0;
            try {
                start = System.currentTimeMillis();
                if ("service".equals(service)) {
                    if ("echo".equals(method)) {
                        response = faultInjectService.echo(name, exception, sleep);
                    } else {
                        response = faultInjectService.echo1(name, exception, sleep);
                    }
                } else {
                    if ("echo".equals(method)) {
                        response = faultInjectService1.echo(name, exception, sleep);
                    } else {
                        response = faultInjectService1.echo1(name, exception, sleep);
                    }
                }
                logger.info(response);
            } catch (Exception e) {
                response = e.getMessage();
                Throwable cause = e.getCause();
                if (cause != null && StringUtils.isNotBlank(cause.getMessage())) {
                    response += cause.getMessage();
                }
            }
            long time = System.currentTimeMillis() - start;
            result.append("第 ").append(i + 1).append("次调用，sleep ").append(sleep).append(" 毫秒,调用耗时: ").append(time).append(" 毫秒,").append("结果：").append(response).append("<br>");
        }
        return result.toString();
    }
}
