/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.endpoint.impl;

import com.alipay.sofa.ms.service.SofaEchoService;

/**
 * @author yiji
 * @version : SofaEchoServiceImpl.java, v 0.1 2020年04月21日 1:50 下午 yiji Exp $
 */
public class SofaEchoServiceImpl implements SofaEchoService {
    @Override
    public String echo(String message) {
        // we don't care about message content.
        return "success";
    }
}