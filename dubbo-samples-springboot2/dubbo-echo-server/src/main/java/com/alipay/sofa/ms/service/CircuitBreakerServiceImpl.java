package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.exception.RequestException;
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
    public String getServiceInfo(Long sleepTime,Boolean exception) throws RequestException{
        logger.info("被调用,sleep:{}ms,异常状态为:{}",sleepTime,exception);
        if(Objects.equals(exception,true)){
            throw new RequestException("异常调用");
        }
        long start = System.currentTimeMillis();
        String res = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            res=String.format("熔断请求服务,ip地址:%s", localHost.getHostAddress());
            Thread.sleep(sleepTime);
            res += String.format(",执行时间ms:%d",System.currentTimeMillis()-start);
            return res;
        } catch (InterruptedException interruptedException) {
            logger.warn("被唤醒");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return res;
    }
}
