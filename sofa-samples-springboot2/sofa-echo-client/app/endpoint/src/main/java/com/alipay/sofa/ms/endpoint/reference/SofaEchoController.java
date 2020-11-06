package com.alipay.sofa.ms.endpoint.reference;

import com.alipay.sofa.ms.service.SofaEchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxy
 * @date ：2020/11/5 4:28 下午
 */
@RestController
@RequestMapping("/echoController")
public class SofaEchoController {

    @Autowired
    private SofaEchoService echoService;

    @GetMapping("test")
    public String test(){
        String res;
        try {
            String status1 = echoService.echo("Hello world!");
            res=">>>>>>>> echo result: " + status1;
        } catch (Exception e) {
            res=">>>>>>>> echo result: " + e.getMessage();
        }
        return res;
    }
}
