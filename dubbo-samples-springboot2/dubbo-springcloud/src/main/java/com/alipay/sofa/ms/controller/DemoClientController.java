package com.alipay.sofa.ms.controller;

import com.alipay.sofa.ms.service.EchoService;
import com.alipay.sofa.ms.service.model.SubReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String helloWorld(@RequestParam String message) {
        try {
            return echoService.echo(message);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/dubbo/add")
    public Integer add(@RequestParam int a, @RequestParam int b ) {
            return echoService.add(a, b);
    }

    @RequestMapping("/dubbo/sub")
    public Integer sub(@RequestBody SubReq subReq) {
        return echoService.sub(subReq);
    }

}



