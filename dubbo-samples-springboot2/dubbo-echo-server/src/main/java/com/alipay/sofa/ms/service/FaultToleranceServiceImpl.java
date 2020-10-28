package com.alipay.sofa.ms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * @author zxy
 * @date 2020-10-22 15:53
 **/
@Service
public class FaultToleranceServiceImpl implements FaultToleranceService{

    private Logger logger = LoggerFactory.getLogger(FaultToleranceServiceImpl.class);

    @Override
    public String delayResponse() throws Exception {
        long sleepTime = 1200L;
        Thread.sleep(sleepTime);
        InetAddress addr = InetAddress.getLocalHost();
        String hostAddress = addr.getHostAddress();
        String res = "ip:" + hostAddress + ",delay time:" + sleepTime;
        logger.info(res);
        return res;
    }
}
