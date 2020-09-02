package com.alipay.sofa.ms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class RpcBenchmarkImpl implements RpcBenchmark {

    private Logger logger = LoggerFactory.getLogger(RpcBenchmarkImpl.class);

    @Resource
    private RpcBenchmarkReply rpcBenchmarkReply;

    @Override
    public Response request(Request request) {

        request.updateRouteRecord();

        logger.info("received request " + request);
        return rpcBenchmarkReply.request(request);
    }

    @Override
    public Response clearAndReset() {
        logger.info("received clearAndReset invoke");
        return rpcBenchmarkReply.clearAndReset();
    }
}