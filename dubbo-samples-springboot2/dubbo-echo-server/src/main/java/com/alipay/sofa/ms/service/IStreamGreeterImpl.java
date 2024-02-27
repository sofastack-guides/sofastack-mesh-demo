package com.alipay.sofa.ms.service;

import org.apache.dubbo.common.stream.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class IStreamGreeterImpl implements IGreeter {
    @Override
    public StreamObserver<String> sayHello(StreamObserver<String> replyObserver) {
        return new StreamObserver<String>() {
            private final List<String> replyList = new ArrayList<>();

            @Override
            public void onNext(String helloRequest) {
                System.out.println("onNext receive request name:" + helloRequest);
                replyList.add("receive name:" + helloRequest);
                replyObserver.onNext("receive name:" + helloRequest);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("onError");
                replyObserver.onError(cause);
            }

            @Override
            public void onCompleted() {
                System.out.println("onComplete receive request size:" + replyList.size());
                replyObserver.onCompleted();
            }
        };
    }

    public void sayHelloServerStream(String request, StreamObserver<String> response) {
        for (int i = 0; i < 10; i++) {
            response.onNext("hello," + request);
        }
        response.onCompleted();
    }
}
