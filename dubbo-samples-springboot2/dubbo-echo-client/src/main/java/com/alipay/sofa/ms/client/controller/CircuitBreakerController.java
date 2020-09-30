package com.alipay.sofa.ms.client.controller;

import com.alipay.sofa.ms.client.domain.CircuitBreakerModel;
import com.alipay.sofa.ms.client.domain.CircuitBreakerResponse;
import com.alipay.sofa.ms.client.domain.CircuitBreakerResponseType;
import com.alipay.sofa.ms.exception.RequestException;
import com.alipay.sofa.ms.service.CircuitBreakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zxy
 * @date 2020-09-27 16:39
 **/
@RestController
@RequestMapping("circuitBreaker")
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @Resource(name = "circuitBreakerService")
    private CircuitBreakerService service;

    @GetMapping(path = "getServiceInfo")
    public CircuitBreakerResponse getServiceInfo(CircuitBreakerModel model) throws Exception {
        boolean breakFlag = false;
        if (model.getErrorPercent() != null) {
            breakFlag = triggerErrorPercentBreaker(model);
        } else if (model.getAverageRt() != null) {
            breakFlag = triggerAverageRtBreaker(model);
        }
        CircuitBreakerResponseType res = CircuitBreakerResponseType.NO_BREAKER;
        if (breakFlag) {
            res = testAwaken(model);
        }
        return buildResponse(res);
    }

    private CircuitBreakerResponse buildResponse(CircuitBreakerResponseType res) {
        return new CircuitBreakerResponse().setCode(res.getCode()).setMessage(res.getMessage());
    }

    private boolean triggerAverageRtBreaker(CircuitBreakerModel model) throws InterruptedException, ExecutionException {
        Long requestCount = model.getRequestCount();
        AtomicBoolean breakFlag = new AtomicBoolean(false);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 40,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue());
        Future future = null;
        for (int i = 0; i < requestCount; i++) {
            if (breakFlag.get()) {
                pool.shutdown();
                return true;
            }
            future = pool.submit(() -> {
                try {
                    service.getServiceInfo(model.getAverageRt(), false);
                } catch (RuntimeException e) {
                    logger.info("发生了熔断", e);
                    breakFlag.set(true);
                }
            });
        }
        future.get();
        //阻塞
        return breakFlag.get();
    }


    private boolean triggerErrorPercentBreaker(CircuitBreakerModel model) {
        Long requestCount = model.getRequestCount();
        Double errorPercent = model.getErrorPercent();
        long error = new Double(Math.ceil(requestCount * errorPercent / 100)).longValue();
        error = Math.min(requestCount, error);
        //进行正常请求
        for (int i = 0; i < requestCount - error; i++) {
            service.getServiceInfo(0L, false);
        }
        boolean breakFlag = false;
        //多请求，触发熔断
        error++;
        for (int i = 0; i < error; i++) {
            try {
                service.getServiceInfo(0L, true);
            } catch (RequestException e) {
                logger.info("异常调用");
            } catch (RuntimeException e) {
                //假设这里发生了熔断
                logger.info("发生了熔断", e);
                breakFlag = true;
                break;
            }
        }
        return breakFlag;
    }

    private CircuitBreakerResponseType testAwaken(CircuitBreakerModel model) throws InterruptedException {
        //等待熔断恢复期
        Thread.sleep(model.getSleepWindow());
        //根据类型发送请求
        CircuitBreakerResponseType res = null;
        try {
            res = sendRequestByModel(model);
        } catch (RuntimeException e) {
            //熔断打开状态
            logger.info("发生了熔断", e);
            String type = model.getAwakenRequestType();
            switch (type) {
                case "1":
                    res = CircuitBreakerResponseType.BREAKER_NORMAL_OPEN;
                    break;
                case "2":
                    res = CircuitBreakerResponseType.BREAKER_EXCEPTION_OPEN;
                    break;
                case "3":
                    res = CircuitBreakerResponseType.BREAKER_TIMEOUT_OPEN;
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    private CircuitBreakerResponseType sendRequestByModel(CircuitBreakerModel model) {
        String type = model.getAwakenRequestType();
        CircuitBreakerResponseType res = null;
        switch (type) {
            case "1":
                service.getServiceInfo(0L, false);
                res = CircuitBreakerResponseType.BREAKER_NORMAL_CLOSE;
                break;
            case "2":
                try {
                    service.getServiceInfo(0L, true);
                } catch (RequestException e) {
                    res = CircuitBreakerResponseType.BREAKER_EXCEPTION_CLOSE;
                }
                break;
            case "3":
                service.getServiceInfo(model.getRpcTimeout() + 100L, false);
                res = CircuitBreakerResponseType.BREAKER_TIMEOUT_CLOSE;
                break;
            default:
                throw new IllegalArgumentException("unknown awakeRequestType:" + type);
        }
        return res;
    }
}
