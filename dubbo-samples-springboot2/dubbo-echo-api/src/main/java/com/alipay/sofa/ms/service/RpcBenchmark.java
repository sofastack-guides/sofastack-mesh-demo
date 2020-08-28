package com.alipay.sofa.ms.service;

import com.alipay.sofa.pressure.annotation.PressureTest;

@PressureTest
public interface RpcBenchmark {

    Response request(Request request);

    Response clearAndReset();

}