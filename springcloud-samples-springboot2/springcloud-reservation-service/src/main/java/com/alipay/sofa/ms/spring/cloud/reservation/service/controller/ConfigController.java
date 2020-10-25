/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.service.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yiji@apache.org
 * @version : HelloWorldController.java, v 0.1 2020年02月26日 8:32 下午 yiji Exp $
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private volatile Map<String, ConfigBean> configBeanMap = new HashMap<>();

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void changeConfig(@RequestBody List<MethodConfigBean> configBeans) {
        if (configBeans == null || configBeans.isEmpty()) {
            return;
        }
        Map<String, ConfigBean> cb = new HashMap<>();
        for (MethodConfigBean methodConfigBean : configBeans) {
            cb.put(methodConfigBean.getMethodName(),
                    new ConfigBean().setTimeout(methodConfigBean.getTimeout()).setException(methodConfigBean.isException()));
        }
        configBeanMap = cb;
    }

    public Map<String, ConfigBean> getConfigBeanMap() {
        return configBeanMap;
    }

    public void execute(String methodName) {
        Map<String, ConfigBean> map = getConfigBeanMap();
        if (map == null) {
            return;
        }
        ConfigBean configBean = map.get(methodName);
        if (configBean == null) {
            return;
        }
        if (configBean.getTimeout() > 0) {
            try {
                Thread.sleep(configBean.getTimeout());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (configBean.isException()) {
            throw new RuntimeException("config exception");
        }
    }
}