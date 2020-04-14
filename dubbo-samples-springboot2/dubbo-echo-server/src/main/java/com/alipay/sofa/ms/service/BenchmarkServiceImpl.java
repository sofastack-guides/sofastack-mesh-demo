/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yiji
 * @version : BenchmarkService.java, v 0.1 2020年04月01日 4:47 下午 yiji Exp $
 */
public class BenchmarkServiceImpl implements BenchmarkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkServiceImpl.class);

    @Override
    public String send_512_byte(String content) {
        //String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        //LOGGER.info("[" + now + "] send_512_byte, request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return content;
    }

    @Override
    public String send_1k(String content) {
        //String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        //LOGGER.info("[" + now + "] send_1k, request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return content;
    }

    @Override
    public String send_2k(String content) {
        //String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        //LOGGER.info("[" + now + "] send_2k, request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return content;
    }

    @Override
    public String send_4k(String content) {
        //String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        //LOGGER.info("[" + now + "] send_4k, request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return content;
    }

    @Override
    public String send_8k(String content) {
        //String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        //LOGGER.info("[" + now + "] send_8k, request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return content;
    }

}