package com.alipay.sofa.ms.service;

public interface RpcBenchmark {

    Response request(Request request);

    Response clearAndReset();

}