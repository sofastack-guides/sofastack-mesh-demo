/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.reply.controller;

import com.alipay.sofa.ms.reply.service.RpcBenchmarkReplyImpl;
import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiji@apache.org
 * @version : BenchmarkReplyController.java, v 0.1 2020年02月26日 8:32 下午 yiji Exp $
 */
@RestController
@RequestMapping("/benchmark")
public class BenchmarkReplyController {

    @Autowired
    private RpcBenchmarkReplyImpl reply;

    @RequestMapping(value = "/request",method = RequestMethod.POST)
    public Response request(@RequestBody Request request) {
        return reply.request(request);
    }

    @RequestMapping(value = "/clearAndReset",method = RequestMethod.POST)
    public Response clearAndReset() {
        return reply.clearAndReset();
    }
}