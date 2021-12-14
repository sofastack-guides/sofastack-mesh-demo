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
 * @version : WebServiceBootstrap.java
 */
@SpringBootApplication
@ImportResource("spring/echo-consumer.xml")
public class SpringCloudBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudBootstrap.class, args);
    }

}