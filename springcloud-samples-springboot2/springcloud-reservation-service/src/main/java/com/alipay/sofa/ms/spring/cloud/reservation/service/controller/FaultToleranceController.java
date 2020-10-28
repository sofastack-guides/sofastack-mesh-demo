package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Optional;

/**
 * @author zxy
 * @date 2020-10-22 15:53
 **/
@RestController
@RequestMapping("faultTolerance")
public class FaultToleranceController {

    private Logger logger = LoggerFactory.getLogger(FaultToleranceController.class);

    @Autowired
    private HttpServletResponse response;

    @GetMapping(path = "delayResponse")
    public String delayResponse() throws Exception {
        String responseStatus = System.getenv("FAULT_TOLERANCE_RESPONSE_STATUS");
        String s = Optional.ofNullable(responseStatus).orElse("200");
        Integer status = NumberUtils.createInteger(s);
        response.setStatus(status);
        InetAddress addr = InetAddress.getLocalHost();
        String hostAddress = addr.getHostAddress();
        String res = "ip:" + hostAddress + ",retrun status:" + status;
        logger.info(res);
        return res;
    }
}
