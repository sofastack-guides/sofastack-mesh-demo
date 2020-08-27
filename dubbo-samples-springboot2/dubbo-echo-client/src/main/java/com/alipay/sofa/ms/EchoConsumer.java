/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yiji@apache.org
 * @version : EchoConsumer.java, v 0.1 2020年02月24日 4:23 下午 yiji Exp $
 */
@SpringBootApplication
@ImportResource("spring/echo-consumer.xml")
public class EchoConsumer implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoConsumer.class);

    public static void main(String[] args) {
        SpringApplication.run(EchoConsumer.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //EchoService echoService = (EchoService) applicationContext.getBean("echoService"); // get remote service proxy
        //new Thread(() -> {
        //    for (; ; ) {
        //        try {
        //            TimeUnit.SECONDS.sleep(1L);
        //            String status1 = echoService.echo("Hello world!");
        //            LOGGER.info(">>>>>>>> echo result: " + status1);
        //        } catch (Exception e) {
        //            LOGGER.error(">>>>>>>> echo result: " + e.getMessage());
        //        }
        //    }
        //}).start();
    }
}