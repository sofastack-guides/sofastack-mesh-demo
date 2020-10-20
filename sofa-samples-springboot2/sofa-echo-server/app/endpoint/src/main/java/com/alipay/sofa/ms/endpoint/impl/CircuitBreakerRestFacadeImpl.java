package com.alipay.sofa.ms.endpoint.impl;

import com.alipay.sofa.ms.dto.RequestType;
import com.alipay.sofa.ms.service.CircuitBreakerRestFacade;
import com.taobao.remoting.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author zxy
 * @date 2020-09-27 16:47
 **/
public class CircuitBreakerRestFacadeImpl implements CircuitBreakerRestFacade {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerRestFacadeImpl.class);

    @Override
    public String getServiceInfo(Long executionTime, String type,Integer timeout) throws RemotingException {
        logger.info("被调用,执行时间:{}ms,类型为:{}", executionTime, type);
        if (Objects.equals(type, RequestType.EXCEPTION.getType())) {
            throw new RemotingException("异常调用");
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
