package com.alipay.sofa.ms.controller;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.router.UserRouter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;

import java.util.ArrayList;
import java.util.List;

@com.alibaba.dubbo.router.CustomRouter
public class CustomRouter implements Router, UserRouter {

    private URL url;

    public CustomRouter(URL url) {
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return this.url;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        if (invokers.size() >= 1) {
            List<Invoker<T>> i = new ArrayList<>();
            Invoker<T> invoker = invokers.get(invokers.size() - 1);
            i.add(invoker);
            System.out.println("invoke url: " + invoker.getUrl());
            return i;
        }
        return invokers;
    }

//    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int compareTo(Router o) {
        return 0;
    }


}