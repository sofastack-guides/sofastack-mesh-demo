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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yiji
 * @version : EchoConsumer.java, v 0.1 2020年04月21日 2:44 下午 yiji Exp $
 */
public class SofaEchoConsumer implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SofaEchoConsumer.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SofaEchoService echoService = (SofaEchoService) applicationContext.getBean("echoService"); // get remote service proxy
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                int count = 1;
                for (; ; ) {
                    try {
                        //if (count++ <=1) {
                            long start = System.currentTimeMillis();
                            String status1 = echoService.echo("Hello world!");
                            long elapsed = System.currentTimeMillis() -start;
                            Thread thread = Thread.currentThread();
                            System.out.println(
                                    ">>>>>>>> [" + thread.getId() + "," + (count++) + "," + elapsed + "ms]" + fmt.format(new Date()) + " echo result: " + status1);
                            logger.info(">>>>>>>> echo result: " + status1);
                        //}
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (Exception e) {
                        try {
                            TimeUnit.SECONDS.sleep(1L);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        System.err.println(">>>>>>>> failed: " + e.getMessage());
                        logger.error(">>>>>>>> echo result: " + e.getMessage());
                    }
                }
            }).start();
        }
    }
}