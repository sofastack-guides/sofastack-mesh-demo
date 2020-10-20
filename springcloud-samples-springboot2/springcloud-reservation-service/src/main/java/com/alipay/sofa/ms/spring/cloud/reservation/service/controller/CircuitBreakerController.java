package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author zxy
 * @date 2020-09-27 16:47
 **/
@RestController
@RequestMapping("circuitBreaker")
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping(path = "getServiceInfo")
    public String getServiceInfo(Long executionTime, String type, HttpServletResponse response){
        logger.info("被调用,执行时间:{}ms,类型为:{}",executionTime,type);
        if(Objects.equals(type,"2")){
            response.setStatus(502);
            return null;
        }else if(Objects.equals(type,"3")){
            response.setStatus(504);
            return null;
        }
        long start = System.currentTimeMillis();
        String res = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            res=String.format("熔断请求服务,ip地址:%s", localHost.getHostAddress());
            Thread.sleep(executionTime);
            res += String.format(",执行时间ms:%d",System.currentTimeMillis()-start);
            return res;
        } catch (InterruptedException interruptedException) {
            logger.warn("被唤醒");
        } catch (UnknownHostException e) {
            logger.error("UnknownHostException",e);
        }
        return res;
    }
}
