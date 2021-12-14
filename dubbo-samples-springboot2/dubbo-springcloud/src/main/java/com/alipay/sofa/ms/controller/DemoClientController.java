package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tancong date 2021/12/12 6:32 下午
 * @description: TODO
 */
@RestController
@RequestMapping("/meshtest")
public class DemoClientController {

    @Autowired
    private EchoService echoService;

    @RequestMapping("/dubbo/hello")
    public String helloWorld() {
        try {
            return echoService.echo("tancong");
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}



