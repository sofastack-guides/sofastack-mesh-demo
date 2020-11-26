/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alibaba.dubbo.rpc.cluster.router.user;

import com.alibaba.dubbo.rpc.cluster.router.user.IUserRouter;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 * @author xingqi
 * @version $Id: IUserRouterImpl.java, v 0.1 2020年11月06日 8:26 PM xingqi Exp $
 */
public class IUserRouterImpl implements IUserRouter {

    @Override
    public boolean route(org.apache.dubbo.common.URL url, Object[] objects) throws RpcException {
        System.out.println("====>" + url.toFullString());
        System.out.println(JSON.toJSON(objects));
        return true;
    }
}