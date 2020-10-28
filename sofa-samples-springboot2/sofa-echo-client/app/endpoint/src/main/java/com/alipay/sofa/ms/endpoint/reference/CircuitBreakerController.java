package com.alipay.sofa.ms.endpoint.reference;

import com.alipay.sofa.ms.dto.RequestType;
import com.alipay.sofa.ms.endpoint.dto.CircuitBreakerModel;
import com.alipay.sofa.ms.endpoint.dto.CircuitBreakerResponse;
import com.alipay.sofa.ms.endpoint.dto.CircuitBreakerResponseType;
import com.alipay.sofa.ms.endpoint.dto.ResponseType;
import com.alipay.sofa.ms.service.CircuitBreakerRestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zxy
 * @date 2020-09-27 16:39
 **/
@RestController
@RequestMapping("circuitBreaker")
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    private final List<String> circuitBreakerMessageList = Arrays.asList("mosn limit exception", "509", "mosn limit excepiton");

    @Autowired
    private CircuitBreakerRestFacade circuitBreakerService;

    @GetMapping(path = "getServiceInfo")
    public CircuitBreakerResponse getServiceInfo(CircuitBreakerModel model) throws Exception {
        prepare();
        boolean breakFlag = false;
        if (model.getErrorPercent() != null) {
            breakFlag = triggerErrorPercentBreaker(model);
        } else if (model.getAverageRt() != null) {
            breakFlag = triggerAverageRtBreaker(model);
        }
        CircuitBreakerResponseType res = CircuitBreakerResponseType.NO_BREAKER;
        if (breakFlag) {
            logger.info("触发了熔断状态，开始唤醒熔断");
            res = testAwaken(model);
        } else {
            logger.info("没有触发熔断");
        }
        return buildResponse(res);
    }

    private void prepare() throws InterruptedException {
        logger.info("先发送3条正常请求，确保服务端处于正常响应状态");
        for (int i = 0; i < 3; i++) {
            ResponseType responseType = sendRequest(0L, RequestType.NORMAL);
            if (responseType != ResponseType.NORMAL) {
                throw new RuntimeException("服务端未正常响应请求!");
            }
        }
        int waitTime = 5000;
        logger.info("等待:{}ms后开始熔断测试", waitTime);
        Thread.sleep(waitTime);
    }

    private CircuitBreakerResponse buildResponse(CircuitBreakerResponseType res) {
        return new CircuitBreakerResponse().setCode(res.getCode()).setMessage(res.getMessage());
    }

    private boolean triggerAverageRtBreaker(CircuitBreakerModel model) throws InterruptedException, ExecutionException {
        logger.info("开始平均RT模式测试");
        Long requestCount = model.getRequestCount();
        AtomicBoolean breakFlag = new AtomicBoolean(false);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 40,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue());
        Future future = null;
        logger.info("准备发送:{}条执行时间:{}ms的请求", requestCount, model.getAverageRt());
        for (int i = 0; i < requestCount + 1; i++) {
            if (breakFlag.get()) {
                pool.shutdown();
                return true;
            }
            int finalI = i;
            future = pool.submit(() -> {
                if (finalI == requestCount) {
                    try {
                        breakFlag.set(getBreakStatus(model.getAverageRt(), RequestType.NORMAL, model.getTotalMetricWindow()));
                        return;
                    } catch (InterruptedException interruptedException) {
                        logger.info("响应中断", interruptedException);
                    }
                }
                ResponseType resType = sendRequest(model.getAverageRt(), RequestType.NORMAL);
                if (isBreaker(resType)) {
                    breakFlag.set(true);
                }
            });
        }
        //阻塞
        future.get();
        return breakFlag.get();
    }

    private boolean isBreaker(ResponseType resType) {
        return resType == ResponseType.CIRCUIT_BREAKER;
    }

    private boolean checkCircuitBreakerException(Exception e) {
        String message = e.getMessage();
        return message != null
                && circuitBreakerMessageList.stream().anyMatch(message::contains);
    }

    private boolean checkNormalException(Exception e) {
        return e.getMessage() != null && e.getMessage().contains("异常调用");
    }

    private boolean checkTimeoutException(Exception e) {
        return e.getMessage() != null && (e.getMessage().contains("504") || e.getMessage().contains("timeout"));
    }


    private boolean triggerErrorPercentBreaker(CircuitBreakerModel model) throws InterruptedException {
        logger.info("开始错误率模式请求");
        Long requestCount = model.getRequestCount();
        Double errorPercent = model.getErrorPercent();
        long error = new Double(Math.ceil(requestCount * errorPercent / 100)).longValue();
        error = Math.min(requestCount, error);
        logger.info("准备发送:{}条请求,其中:{}条错误请求", requestCount, error);
        //发送请求
        for (int i = 0; i < requestCount; i++) {
            ResponseType resType = null;
            if (i < requestCount - error) {
                resType = sendRequest(0L, RequestType.NORMAL);
            } else {
                resType = sendRequest(0L, RequestType.EXCEPTION);
            }
            if (isBreaker(resType)) {
                return true;
            }
        }
        //检查熔断状态
        return getBreakStatus(0L, RequestType.EXCEPTION, model.getTotalMetricWindow());
    }

    private boolean getBreakStatus(Long executionTime, RequestType requestType, Integer totalMetricWindow) throws InterruptedException {
        logger.info("等待熔断时间窗口:{}秒后，额外发送一条请求检查熔断状态", totalMetricWindow);
        Thread.sleep(totalMetricWindow * 1000);
        ResponseType resType = sendRequest(executionTime, requestType);
        return isBreaker(resType);
    }

    private CircuitBreakerResponseType testAwaken(CircuitBreakerModel model) throws InterruptedException {
        //等待熔断恢复期
        logger.info("等待:{}ms熔断恢复期", model.getSleepWindow());
        Thread.sleep(model.getSleepWindow());
        //根据类型发送请求
        CircuitBreakerResponseType res = null;
        int count = 3;
        logger.info("发送:{}条指定类型请求测试熔断状态", count);
        for (int i = 0; i < count; i++) {
            res = sendRequestAndGetType(model);
        }
        return res;
    }

    private CircuitBreakerResponseType sendRequestAndGetType(CircuitBreakerModel model) {
        String type = model.getAwakenRequestType();
        ResponseType resType = sendRequest(0L, RequestType.findByType(type));
        return CircuitBreakerResponseType.match(type, isBreaker(resType));
    }

    /**
     * 发送请求
     *
     * @param executionTime 执行时间 ms
     * @param requestType   请求类型
     * @return 返回类型
     */
    private ResponseType sendRequest(Long executionTime, RequestType requestType) {
        ResponseType res = ResponseType.NORMAL;
        Integer timeout = null;
        if (requestType == RequestType.EXCEPTION) {
            //sofa暂时不支持抛出异常来进行熔断统计
            requestType = RequestType.TIMEOUT;
        }
        if (requestType == RequestType.TIMEOUT) {
            timeout = 200;
            executionTime = 250L;
        }
        try {
            circuitBreakerService.getServiceInfo(executionTime, requestType.getType(), timeout);
            logger.info("正常调用");
        } catch (Exception e) {
            if (checkNormalException(e)) {
                res = ResponseType.EXCEPTION;
                logger.info("异常调用");
            } else if (checkTimeoutException(e)) {
                res = ResponseType.TIMEOUT;
                logger.info("超时调用");
            } else if (checkCircuitBreakerException(e)) {
                res = ResponseType.CIRCUIT_BREAKER;
                logger.info("发生了熔断");
            } else {
                throw e;
            }
        }
        return res;
    }
}
