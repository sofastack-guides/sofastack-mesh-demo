package com.alipay.sofa.ms.service;

import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TriServiceImpl extends EchoServiceImpl implements TriEchoService {

    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        LOGGER.info("[" + now + "] Hello " + message
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "[tri] echo " + message + "from " + RpcContext.getContext().getLocalAddressString();
    }

}
