package com.alipay.sofa.ms.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(
        group = Constants.PROVIDER,
        order = -1001
)
public class RpcExceptionFilter implements Filter {

    public static boolean exception = false;

    public RpcExceptionFilter() {
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (exception) {
            throw new RpcException("throw custom server rpc exception...");
        }

        return invoker.invoke(invocation);
    }
}
