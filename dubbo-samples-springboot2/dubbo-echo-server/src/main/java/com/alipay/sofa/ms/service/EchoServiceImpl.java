/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yiji@apache.org
 * @version : EchoServiceImpl.java, v 0.1 2020年02月24日 2:47 下午 yiji Exp $
 */
public class EchoServiceImpl implements ShuPianEchoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceImpl.class);

    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        LOGGER.info("[" + now + "] Hello " + message
                + ", request from consumer");
        return message;
    }

}