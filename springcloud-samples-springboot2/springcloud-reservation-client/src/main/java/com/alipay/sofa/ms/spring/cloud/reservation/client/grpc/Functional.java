/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.client.grpc;

/**
 *
 * @author xingqi
 * @version $Id: Functional.java, v 0.1 2020年10月25日 7:26 PM xingqi Exp $
 */
public interface Functional<Arg, HelloReply> {
    HelloReply run(Arg arg);
}