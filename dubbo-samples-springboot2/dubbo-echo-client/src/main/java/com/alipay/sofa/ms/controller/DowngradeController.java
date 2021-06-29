package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.DubboDowngradeService;
import com.alipay.sofa.ms.service.DubboDowngradeService1;
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
@RequestMapping("downgrade")
public class DowngradeController {
    private static final Logger logger = LoggerFactory.getLogger(DowngradeController.class);

    @Autowired
    private DubboDowngradeService downgradeService;

    @Autowired
    private DubboDowngradeService1 downgradeService1;

    @GetMapping("/request")
    public String request(@RequestParam(value = "name", required = false, defaultValue = "downgrade") String name,
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
                        response = downgradeService.echo(name, exception, sleep);
                    } else {
                        response = downgradeService.echo1(name, exception, sleep);
                    }
                } else {
                    if ("echo".equals(method)) {
                        response = downgradeService1.echo(name, exception, sleep);
                    } else {
                        response = downgradeService1.echo1(name, exception, sleep);
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
            result.append("第 ").append(i + 1).append("次调用").append("service:").append(service).append(",sleep ").append(sleep).append(" 毫秒， 结果：").append(response).append("<br>");
        }
        return result.toString();

    }
}
