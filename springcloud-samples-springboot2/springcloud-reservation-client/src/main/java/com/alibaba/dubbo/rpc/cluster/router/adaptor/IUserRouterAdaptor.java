/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alibaba.dubbo.rpc.cluster.router.adaptor;

import com.alipay.sofa.ms.service.TestTom;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 * @author xingqi
 * @version $Id: IUserRouter.java, v 0.1 2020年11月06日 8:22 PM xingqi Exp $
 */
public interface IUserRouterAdaptor {

    /**
     * customer router for TomService : String tom(TestTom testTom)
     * @param url
     * @param testTom
     * @return
     * @throws RpcException
     */
    boolean route(String url, TestTom testTom) throws RpcException;
}