package com.alipay.sofa.ms.controller;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

public class CustomRouterFactory implements RouterFactory {

    @Override
    public Router getRouter(URL url) {
        return new CustomRouter(url);
    }

}