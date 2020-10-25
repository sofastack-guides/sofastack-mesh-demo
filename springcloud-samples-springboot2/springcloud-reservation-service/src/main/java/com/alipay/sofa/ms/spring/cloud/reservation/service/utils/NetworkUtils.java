/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author xingqi
 * @version $Id: NetworkUtils.java, v 0.1 2020年10月25日 3:48 PM xingqi Exp $
 */
public class NetworkUtils {

    public static String getLocalIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }
}