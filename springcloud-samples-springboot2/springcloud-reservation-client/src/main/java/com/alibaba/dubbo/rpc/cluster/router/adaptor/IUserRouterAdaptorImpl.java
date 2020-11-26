/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alibaba.dubbo.rpc.cluster.router.adaptor;

import com.alibaba.dubbo.rpc.cluster.router.user.IUserRouter;
import com.alipay.sofa.ms.service.TestTom;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author xingqi
 * @version $Id: IUserRouter.java, v 0.1 2020年11月06日 8:22 PM xingqi Exp $
 */
public class IUserRouterAdaptorImpl implements IUserRouterAdaptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(IUserRouterAdaptorImpl.class);

    private IUserRouter iUserRouter;

    public IUserRouterAdaptorImpl(IUserRouter iUserRouter) {
        this.iUserRouter = iUserRouter;
    }

    @Override
    public boolean route(String url, TestTom testTom) throws RpcException {
        if (iUserRouter == null) {
            return true;
        }
        try {
            return iUserRouter.route(URL.valueOf(url), new Object[] {testTom});
        } catch (Exception e) {
            LOGGER.error("iUserRouter error. ", e);
        }
        return true;
    }
}