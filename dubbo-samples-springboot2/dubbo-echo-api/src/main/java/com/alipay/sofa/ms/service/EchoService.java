/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.service.model.SubReq;

/**
 * @author yiji@apache.org
 * @version : EchoService.java, v 0.1 2020年02月24日 2:47 下午 yiji Exp $
 */
public interface EchoService {
    String echo(String message);

    Integer add(Integer a, Integer b);

    Integer sub(SubReq subReq);
}