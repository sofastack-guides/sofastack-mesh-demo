/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alibaba.dubbo.rpc.cluster.router.user;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 * @author xingqi
 * @version $Id: IUserRouter.java, v 0.1 2020年11月06日 8:22 PM xingqi Exp $
 */
public interface IUserRouter {

    boolean route(URL url, Object[] objects) throws RpcException;
}