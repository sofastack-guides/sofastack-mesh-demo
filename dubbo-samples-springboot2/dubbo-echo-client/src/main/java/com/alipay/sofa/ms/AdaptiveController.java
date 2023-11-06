package com.alipay.sofa.ms;


import com.alibaba.dubbo.common.Constants;
import com.alipay.sofa.ms.service.InvokeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.*;

/**
 * 本接口用于演示RPC服务调用
 */
//curl http://localhost:8777/ldc/invoke
@RestController
//@RequestMapping("/ldc")
public class AdaptiveController {


    @DubboReference
    private InvokeService invokeService;


    @ResponseBody
    /**
     * curl <a href="http://localhost:8777/ldc/invoke">http://localhost:8777/ldc/invoke</a>
     */
    @GetMapping(value = "/invoke")
    public String request(@RequestParam(name = "shardingId", required = false) String shardingId,
                          @RequestParam(name = "product", required = false) String product,
                          @RequestParam(name = "zoneType") String zoneType,
                          @RequestParam(name = "originEnv") String originEnv,
                          @RequestParam(name = "tag") String tag) {
        try {

            if (product == null) product = "";
            if (shardingId == null) shardingId = "00";

            RpcContext.getContext().setAttachment(Constants.TAG_KEY, tag);

            RpcContext.getContext().setAttachment("product", product);
            RpcContext.getContext().setAttachment("zoneType", zoneType.toUpperCase());
            RpcContext.getContext().setAttachment("originEnv", originEnv);

            if ("R".equals(zoneType)) {
                RpcContext.getContext().setAttachment("shardingId", shardingId);
            }

            return invokeService.echo("ldc invoke zoneType '" + zoneType + "' originEnv '" + originEnv + "' product '" + product + "'");
        } catch (Exception e) {
            return "Failed to invoke request, cause: " + e.getMessage();
        }
    }

    @ResponseBody
    // http://localhost:8777/ldc/hello
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello world!";
    }

}
