package com.alipay.sofa.ms.client.controller;

import com.alipay.sofa.ms.service.FaultToleranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zxy
 * @date 2020-10-22 15:56
 **/
@RestController
@RequestMapping("faultTolerance")
public class FaultToleranceController {

    private final Logger logger = LoggerFactory.getLogger(FaultToleranceController.class);

    @Autowired
    FaultToleranceService service;
    boolean initStatus = false;
    long initCount = 1000;
    AtomicBoolean status = new AtomicBoolean(initStatus);
    AtomicLong count = new AtomicLong(initCount);

    @GetMapping(path = "delayResponse")
    public String delayResponse() throws InterruptedException {
        status.set(true);
        sendRequest();
        return "开始轮询实例，请查看日志";
    }

    @GetMapping(path = "clear")
    public String clear() {
        status.set(initStatus);
        count.set(initCount);
        return "clear over.";
    }

    public void sendRequest() {
        new Thread(() -> {
            while (status.get() && count.decrementAndGet() > 0) {
                try {
                    Thread.sleep(1000);
                    String message = service.delayResponse();
                    logger.info("result:" + message);
                } catch (InterruptedException e) {
                    logger.error("中断异常:", e);
                } catch (Exception e) {
                    logger.info("result:" + e.getMessage());
                }
            }
        }).start();
    }

}
