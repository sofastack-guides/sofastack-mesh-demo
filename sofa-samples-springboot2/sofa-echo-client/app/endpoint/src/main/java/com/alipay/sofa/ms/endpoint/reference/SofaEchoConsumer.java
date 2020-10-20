/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.endpoint.reference;

import com.alipay.sofa.ms.service.SofaEchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.TimeUnit;

/**
 * @author yiji
 * @version : EchoConsumer.java, v 0.1 2020年04月21日 2:44 下午 yiji Exp $
 */
public class SofaEchoConsumer implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SofaEchoConsumer.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SofaEchoService echoService = (SofaEchoService) applicationContext.getBean("echoService"); // get remote service proxy
//        new Thread(() -> {
//            for (; ; ) {
//                try {
//                    TimeUnit.SECONDS.sleep(1L);
//                    String status1 = echoService.echo("Hello world!");
//                    logger.info(">>>>>>>> echo result: " + status1);
//                } catch (Exception e) {
//                    logger.error(">>>>>>>> echo result: " + e.getMessage());
//                }
//            }
//        }).start();
    }
}