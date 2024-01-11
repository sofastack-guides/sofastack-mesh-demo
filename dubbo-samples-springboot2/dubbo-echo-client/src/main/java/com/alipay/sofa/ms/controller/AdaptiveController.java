package com.alipay.sofa.ms.controller;


import com.alibaba.dubbo.rpc.RpcContext;
import com.alipay.sofa.ms.service.InvokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class AdaptiveController {


    @Resource
    private InvokeService invokeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdaptiveController.class);


    @ResponseBody
    /**
     * curl <a href="http://localhost:8777/invoke">http://localhost:8777/invoke</a>
     */
    @GetMapping(value = "/invoke")
    public String request(@RequestParam(name = "shardingId", required = false) String shardingId,
                          @RequestParam(name = "product", required = false) String product,
                          @RequestParam(name = "zoneType") String zoneType,
                          @RequestParam(name = "originEnv") String originEnv,
                          @RequestParam(name = "tag", required = false) String tag) {
        try {
            if (product == null)
                product = "";
            if (shardingId == null)
                shardingId = "00";
            //RpcContext.getContext().setAttachment("dubbo.tag", tag);
            RpcContext.getContext().setAttachment("product", product);
            RpcContext.getContext().setAttachment("zoneType", zoneType.toUpperCase());
            RpcContext.getContext().setAttachment("originEnv", originEnv);
            if ("R".equals(zoneType))
                RpcContext.getContext().setAttachment("shardingId", shardingId);
            String result = this.invokeService.echo("ldc invoke zoneType '" + zoneType + "' originEnv '" + originEnv + "' product '" + product + "'");
            LOGGER.info(">>>>>>>> ldc result: " + result);
            return result;
        } catch (Exception e) {
            LOGGER.info(">>>>>>>> ldc fatal: " + e.getMessage());
            return "Failed to invoke request, cause: " + e.getMessage();
        }
    }

    @ResponseBody
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello world!";
    }

}
