/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.client.grpc;

import com.alipay.sofa.ms.pb.GreeterGrpc;
import io.grpc.Channel;
import org.springframework.stereotype.Component;

/**
 *
 * @author xingqi
 * @version $Id: JavaGrpcClient.java, v 0.1 2020年10月25日 7:25 PM xingqi Exp $
 */
@Component
public class JavaGrpcClient {

    public <Result> Result run(Channel channel, Functional<GreeterGrpc.GreeterBlockingStub, Result> functional) {
        GreeterGrpc.GreeterBlockingStub testServiceBlockingStub =
                GreeterGrpc.newBlockingStub(channel);
        return functional.run(testServiceBlockingStub);
    }
}