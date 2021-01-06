package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.filter.RpcExceptionFilter;
import com.alipay.sofa.ms.service.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class Controller {

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @Autowired
    private HelloServiceImpl helloService;

    @GetMapping("/status")
    public HelloServiceImpl callCnt() {
        return helloService;
    }

    @GetMapping("/status/set")
    public String setStatus(@RequestParam(required = false, defaultValue = "0")int sleep,
                            @RequestParam(required = false, defaultValue = "false")boolean throwException) {
        helloService.sleep = sleep;
        helloService.throwException = throwException;
        RpcExceptionFilter.exception = throwException;
        return "success";
    }


}
