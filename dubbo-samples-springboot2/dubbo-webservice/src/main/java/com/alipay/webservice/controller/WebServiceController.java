/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.webservice.controller;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meshtest/ws")
public class WebServiceController {

    private static final Logger logger = LoggerFactory.getLogger(WebServiceController.class);

    private static final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

    @RequestMapping("/test")
    public String getRtMsg() {
        try {
            Client client = dcf.createClient("http://127.0.0.1:8999/services/ws/api?wsdl");
            Object[] objects = new Object[0];
            try {
                objects = client.invoke("sayHello", "meshTest");
                logger.info("返回数据:" + objects[0]);
                return objects[0].toString();
            } catch (java.lang.Exception e) {
                logger.error("error: ", e);
                return e.getMessage();
            }
        } catch (Exception e) {
            logger.error("/meshtest/ws/test error", e);
            return e.getMessage();
        }
    }
}