/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yiji@apache.org
 * @version : EchoProvider.java, v 0.1 2020年02月24日 2:49 下午 yiji Exp $
 */
@SpringBootApplication
@ImportResource("spring/echo-provider.xml")
public class EchoProvider {

    public static void main(String[] args) {
        SpringApplication.run(EchoProvider.class, args);
    }

}