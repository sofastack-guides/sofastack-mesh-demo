package com.alipay.sofa.ms.endpoint.impl;

import com.alipay.sofa.ms.service.SofaRouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author ：zxy
 * @date ：2021/1/27 4:02 下午
 */
public class SofaRouterServiceImpl implements SofaRouterService {
    private static final Logger logger = LoggerFactory.getLogger(SofaRouterServiceImpl.class);

    @Override
    public String echo(String name, Boolean exception, Integer sleep) {
        try {
            if (sleep != null) {
                Thread.sleep(sleep);
            }
        } catch (InterruptedException e) {
            logger.error("sleep", e);
        }
        if (exception != null && exception) {
            throw new RuntimeException("some exception happend. name = " + name);
        }
        return "Hi " + name + "! 我是[" + getIpAddress() + "] 的 echo";
    }

    @Override
    public String echo1(String name, Boolean exception, Integer sleep) {
        try {
            if (sleep != null) {
                Thread.sleep(sleep);
            }
        } catch (InterruptedException e) {
            logger.error("sleep", e);
        }
        if (exception != null && exception) {
            throw new RuntimeException("some exception happend. name = " + name);
        }
        return "Hi " + name + "! 我是[" + getIpAddress() + "] 的 echo1";
    }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("IP地址获取失败", e);
        }
        return "";
    }

}
