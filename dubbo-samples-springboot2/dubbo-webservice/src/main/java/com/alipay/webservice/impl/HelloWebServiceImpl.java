/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.webservice.impl;


import com.alipay.webservice.service.HelloWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebService(endpointInterface = "com.alipay.webservice.service.HelloWebService",
        targetNamespace = "http://service.webservice.alipay.com/")
public class HelloWebServiceImpl implements HelloWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWebServiceImpl.class);

    @Override
    public String sayHello(@WebParam(name = "name") String name) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String msg = "[" + now + "] Hello " + name
                + ", request from consumer, this is web service " + ip;
        LOGGER.info(msg);

        return msg;
    }
}