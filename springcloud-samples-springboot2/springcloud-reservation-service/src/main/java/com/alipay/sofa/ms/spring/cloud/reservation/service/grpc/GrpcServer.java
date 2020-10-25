/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.grpc;

import com.alipay.sofa.ms.pb.GreeterGrpc;
import com.alipay.sofa.ms.pb.HelloReply;
import com.alipay.sofa.ms.pb.HelloRequest;
import com.alipay.sofa.ms.spring.cloud.reservation.service.utils.NetworkUtils;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author xingqi
 * @version $Id: GrpcServer.java, v 0.1 2020年10月25日 6:38 PM xingqi Exp $
 */
@Component
public class GrpcServer extends GreeterGrpc.GreeterImplBase implements InitializingBean {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String ip = NetworkUtils.getLocalIp();
        String msg = "[" + now + "] Hello " + (request == null ? "" : request.getName())
                + ", request from consumer, this is dubbo service EchoService " + ip;

        HelloReply result = HelloReply.newBuilder().setMessage(msg).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerBuilder.forPort(50051)
                .addService(new GrpcServer())
                .build()
                .start();
    }

}