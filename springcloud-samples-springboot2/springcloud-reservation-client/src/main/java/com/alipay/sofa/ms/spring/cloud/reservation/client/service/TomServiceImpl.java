/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import com.alipay.sofa.ms.service.TestTom;
import com.alipay.sofa.ms.service.TomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yiji@apache.org
 * @version : EchoServiceImpl.java, v 0.1 2020年02月24日 2:47 下午 yiji Exp $
 */
public class TomServiceImpl implements TomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TomServiceImpl.class);

    @Override
    public String tom(TestTom testTom) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String msg = "[" + now + "] Hello " + (testTom == null ? "" : testTom.getName())
                + ", request from consumer, this is dubbo service TomService clientServer";
        LOGGER.info(msg);
        return msg;
    }
}