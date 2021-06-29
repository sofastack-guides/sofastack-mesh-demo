package com.alipay.sofa.ms.endpoint.reference.controller;

import com.alipay.sofa.ms.service.SofaRouterService;
import com.alipay.sofa.ms.service.SofaRouterService1;
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
@RequestMapping("router")
public class RouterController {
    private static final Logger logger = LoggerFactory.getLogger(RouterController.class);

    @Autowired
    private SofaRouterService routerService;

    @Autowired
    private SofaRouterService1 routerService1;

    @GetMapping("/request")
    public String request(@RequestParam(value = "name", required = false, defaultValue = "router") String name,
                          @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                          @RequestParam(value = "sleep", required = false, defaultValue = "0") Integer sleep,
                          @RequestParam(value = "exception", required = false) Boolean exception,
                          @RequestParam(value = "service", required = false, defaultValue = "service") String service,
                          @RequestParam(value = "method", required = false, defaultValue = "echo") String method
    ) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String response = "";
            try {
                if ("service".equals(service)) {
                    if ("echo".equals(method)) {
                        response = routerService.echo(name, exception, sleep);
                    } else {
                        response = routerService.echo1(name, exception, sleep);
                    }
                } else {
                    if ("echo".equals(method)) {
                        response = routerService1.echo(name, exception, sleep);
                    } else {
                        response = routerService1.echo1(name, exception, sleep);
                    }
                }
                logger.info(response);
            } catch (Exception e) {
                response = e.getMessage();
            }
            result.append("第 ").append(i + 1).append("次调用").append("service:").append(service).append(",sleep ").append(sleep).append(" 毫秒， 结果：").append(response).append("<br>");
        }
        return result.toString();
    }
}
