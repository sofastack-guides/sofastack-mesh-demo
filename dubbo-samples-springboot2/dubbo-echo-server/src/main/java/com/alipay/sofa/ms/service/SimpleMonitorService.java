package com.alipay.sofa.ms.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.monitor.MonitorService;

import java.util.ArrayList;
import java.util.List;

public class SimpleMonitorService implements MonitorService {
    
    @Override
    public void collect(URL statistics) {

    }

    @Override
    public List<URL> lookup(URL query) {
        return new ArrayList<>();
    }
}