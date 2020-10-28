package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.dto.RequestType;
import com.alipay.sofa.ms.exception.RequestException;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author zxy
 * @date 2020-09-27 16:47
 **/
@Service
public class CircuitBreakerServiceImpl implements CircuitBreakerService {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerServiceImpl.class);


    @Override
    public String getServiceInfo(Long executionTime, String type,Integer timeout) {
        logger.info("被调用,执行时间:{}ms,类型为:{}", executionTime, type);
        if (Objects.equals(type, RequestType.EXCEPTION.getType())) {
            throw new IllegalArgumentException("异常调用");
        } else if (Objects.equals(type, RequestType.TIMEOUT.getType())) {
            try {
                Thread.sleep(executionTime);
            } catch (InterruptedException interruptedException) {
                logger.warn("被唤醒");
            }
            return "timeout:504";
        }
        long start = System.currentTimeMillis();
        String res = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            res = String.format("熔断请求服务,ip地址:%s", localHost.getHostAddress());
            Thread.sleep(executionTime);
            res += String.format(",执行时间ms:%d", System.currentTimeMillis() - start);
            return res;
        } catch (InterruptedException interruptedException) {
            logger.warn("被唤醒");
        } catch (UnknownHostException e) {
            logger.error("UnknownHostException", e);
        }
        return res;
    }
}
