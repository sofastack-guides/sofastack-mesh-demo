package com.alipay.sofa.ms.endpoint.reference.controller;

import com.alipay.sofa.ms.service.SofaFaultToleranceService;
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
@RequestMapping("faultTolerance")
public class FaultToleranceController {
    private static final Logger logger = LoggerFactory.getLogger(FaultToleranceController.class);

    @Autowired
    private SofaFaultToleranceService faultToleranceService;

    @GetMapping("/request")
    public String request(@RequestParam(value = "name", required = false, defaultValue = "faultTolerance") String name,
                          @RequestParam(value = "count", required = false, defaultValue = "1") Integer count,
                          @RequestParam(value = "sleep", required = false, defaultValue = "0") Integer sleep,
                          @RequestParam(value = "exception", required = false) Boolean exception,
                          @RequestParam(value = "exceptionIp", required = false) String exceptionIp,
                          @RequestParam(value = "method", required = false, defaultValue = "echo") String method
    ) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String response = "";
            try {
                if ("echo".equals(method)) {
                    response = faultToleranceService.echo(name, exception, sleep, exceptionIp);
                } else {
                    response = faultToleranceService.echo1(name, exception, sleep, exceptionIp);
                }
                logger.info(response);
            } catch (Exception e) {
                response = e.getMessage();
            }
            result.append("第 ").append(i + 1).append("次调用").append(",sleep ").append(sleep).append(" 毫秒， 结果：").append(response).append("<br>");
        }
        return result.toString();
    }
}
