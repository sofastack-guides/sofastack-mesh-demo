package com.alipay.sofa.ms;


import com.alibaba.dubbo.common.Constants;
import com.alipay.sofa.ms.service.IGreeter;
import com.alipay.sofa.ms.service.InvokeService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdaptiveController {


    @DubboReference(retries = 0, check = false)
    private InvokeService invokeService;

    @DubboReference(retries = 0, check = true)
    private IGreeter iGreeter;

    private static final Logger LOGGER = LoggerFactory.getLogger(com.alipay.sofa.ms.AdaptiveController.class);


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
    /**
     * curl <a href="http://localhost:8777/invoke">http://localhost:8777/invokeStream</a>
     */
    @GetMapping(value = "/invokeStream")
    public String invokeStream(@RequestParam(name = "shardingId", required = false) String shardingId,
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
            //String result = this.invokeService.echo("ldc invoke zoneType '" + zoneType + "' originEnv '" + originEnv + "' product '" + product + "'");

            final String[] val = {""};
//            StreamObserver<String> streamObserver = iGreeter.sayHello(new StreamObserver<String>() {
//
//
//
//                @Override
//                public void onNext(String reply) {
//                    System.out.println("onNext");
//                    System.out.println(reply);
//                    val[0] += reply + "\n";
//                }
//
//                @Override
//                public void onError(Throwable throwable) {
//                    System.out.println("onError:" + throwable.getMessage());
//                }
//
//                @Override
//                public void onCompleted() {
//                    System.out.println("onCompleted");
//                }
//            });


            iGreeter.sayHelloServerStream("yiji", new StreamObserver<String>() {


                @Override
                public void onNext(String reply) {
                    System.out.println("onNext");
                    System.out.println(reply);
                    val[0] += reply + "\n";
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("onError:" + throwable.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("onCompleted");
                }
            });

//            streamObserver.onNext("tony");
//            streamObserver.onNext("nick");
//
//            streamObserver.onCompleted();

            LOGGER.info(">>>>>>>> ldc result: " + val[0]);

            return val[0];
        } catch (Exception e) {
            LOGGER.info(">>>>>>>> ldc fatal: " + e.getMessage());
            return "Failed to invoke request, cause: " + e.getMessage();
        }
    }

    @ResponseBody
    // http://localhost:8777/hello
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello world!";
    }

}
