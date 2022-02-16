/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yiji@apache.org
 * @version : WebServiceBootstrap.java
 */
@SpringBootApplication
public class WebServiceBootstrap {

    public static void main(String[] args) {

        // start netty server
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketServerBootstrap.main(args);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        SpringApplication.run(WebServiceBootstrap.class, args);
    }

}