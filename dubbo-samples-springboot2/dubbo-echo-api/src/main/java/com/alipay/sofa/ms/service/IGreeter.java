package com.alipay.sofa.ms.service;

import org.apache.dubbo.common.stream.StreamObserver;

public interface IGreeter {

    StreamObserver<String> sayHello(StreamObserver<String> replyObserver);

    void sayHelloServerStream(String request, StreamObserver<String> response);

}
