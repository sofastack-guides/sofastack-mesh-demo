package com.alipay.sofa.ms.client.controller;

import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import com.alipay.sofa.ms.service.RpcBenchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class BenchmarkController {

    private static AtomicBoolean running = new AtomicBoolean();

    private static RequestHolder request = new RequestHolder();

    private Logger logger = LoggerFactory.getLogger(BenchmarkController.class);

    @Resource
    private RpcBenchmark benchmark;

    private static Thread thread;

    @RequestMapping(value = "/request")
    public String request(@RequestParam(name = "sellerNick", required = false) String sellerNick,
                          @RequestParam(name = "skuId", required = false) String skuId,
                          @RequestParam(name = "tradeId", required = false) String tradeId,
                          @RequestParam(name = "count", required = false) Integer count) {

        StringBuffer buffer = new StringBuffer();

        if (running.get()) {
            buffer.append("There are pressure tasks running already.");
            buffer.append("\n");
            buffer.append(request);
            return buffer.toString();
        }

        if (count == null || count <= 0) {
            count = 1000;
        }

        request.pressureId = UUID.randomUUID().toString();

        if (running.compareAndSet(false, true)) {
            Integer loop = count;
            thread = new Thread(() -> {
                Random random = new Random();
                try {
                    for (int i = 0; i < loop; i++) {
                        try {
                            request.sent++;
                            Thread.sleep(random.nextInt(50)); // random sleep [0-49] ms
                            Request req = new Request(request.pressureId, sellerNick, skuId, tradeId);
                            logger.info("request" + req + ", id: " + i);
                            Response response = benchmark.request(req);
                            logger.info("request" + req + "received response: " + response + ", id: " + i);
                            if (response.getThrowable() != null) {
                                request.throwable = response.getThrowable();
                            }
                        } catch (Exception e) {
                            request.failed++;
                            request.throwable = e;
                        }
                    }
                } finally {
                    running.compareAndSet(true, false);
                }
            });
            thread.start();
        }

        buffer.append("Start request success.").append("\n");
        return buffer.append(request).toString();
    }

    @RequestMapping(value = "/query")
    public String query() {
        StringBuffer buffer = new StringBuffer();
        return buffer.append(request).toString();
    }

    @RequestMapping(value = "/stop")
    public String stop() {
        if (running.get() && thread != null) {
            thread.interrupt();
            thread = null;
            return "stopped";
        }

        return "not start yet.";
    }

    @RequestMapping(value = "/clear")
    public String clear() {
        try {
            Response response = benchmark.clearAndReset();
            logger.info("received response: " + response);
            request.reset();
            running.compareAndSet(true, false);
            String value = "status: " + response.isSuccess();
            if (response.getThrowable() != null) {
                return value + "\n exception: " + toString(response.getThrowable());
            }
            return value;
        } catch (Exception e) {
            logger.error("failed to invoke clearAndReset", e);
            return "status: false" + "\n exception: " + toString(e);
        }
    }

    static class RequestHolder {

        volatile String sellerNick;
        volatile String skuId;
        volatile String tradeId;
        volatile int    count;
        volatile int    sent;
        volatile int    failed;

        volatile String pressureId;

        volatile Throwable throwable;

        public void reset() {
            this.sellerNick = null;
            this.skuId = null;
            this.tradeId = null;
            this.count = 0;
            this.sent = 0;
            this.failed = 0;
            this.pressureId = null;
            this.throwable = null;
        }

        @Override
        public String toString() {

            StringBuffer buffer = new StringBuffer();
            buffer.append("pressureId=").append(pressureId);
            if (sellerNick != null) {
                buffer.append(", sellerNick=").append(sellerNick);
            }
            if (skuId != null) {
                buffer.append(", skuId=").append(skuId);
            }
            if (tradeId != null) {
                buffer.append(", tradeId=").append(tradeId);
            }

            buffer.append(", count=").append(count)
                    .append(", sent=").append(sent)
                    .append(", fail=").append(failed);

            if (throwable != null) {
                buffer.append(", exception:").append(throwable.getMessage());
                buffer.append("\n");

                buffer.append("stack:\n").append(BenchmarkController.toString(throwable));
            }

            return buffer.toString();
        }
    }

    static String toString(Throwable e) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(output);
        try {
            e.printStackTrace(writer);
            writer.flush();
        } finally {
            writer.close();
        }
        return output.toString();
    }

}