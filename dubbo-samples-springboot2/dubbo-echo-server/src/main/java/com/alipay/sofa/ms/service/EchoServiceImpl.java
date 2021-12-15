/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.service.model.SubReq;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yiji@apache.org
 * @version : EchoServiceImpl.java, v 0.1 2020年02月24日 2:47 下午 yiji Exp $
 */
public class EchoServiceImpl implements EchoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceImpl.class);

    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        LOGGER.info("[" + now + "] Hello " + message
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "hello " + message + "!";
    }

    @Override
    public Integer add(Integer a, Integer b) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        LOGGER.info("[" + now + "] add (" + a + "+" + b + ") =" + (a+b)
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return a + b;
    }

    @Override
    public Integer sub(SubReq subReq) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        LOGGER.info("[" + now + "] add (" + subReq.a + "-" + subReq.b + ") =" + (subReq.a+subReq.b)
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return subReq.a+subReq.b;
    }
}