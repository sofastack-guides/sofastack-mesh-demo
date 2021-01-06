package com.alipay.sofa.ms;


import com.alipay.sofa.ms.endpoint.filter.RpcExceptionFilter;
import com.alipay.sofa.ms.endpoint.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    private HelloServiceImpl helloService;

    @GetMapping("/status")
    public HelloServiceImpl callCnt() {
        return helloService;
    }

    @GetMapping("/status/set")
    public String setStatus(int sleep, boolean throwException) {
        helloService.sleep = sleep;
        helloService.throwException = throwException;

        return "success";
    }

    @GetMapping("/rpcException/set")
    public String setRpcExceptionStatus(@RequestParam(required = false, defaultValue = "false") boolean throwException) {
        RpcExceptionFilter.exception = throwException;
        return "success";
    }
}
