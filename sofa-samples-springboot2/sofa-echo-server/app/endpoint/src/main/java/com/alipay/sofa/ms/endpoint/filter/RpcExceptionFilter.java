package com.alipay.sofa.ms.endpoint.filter;

import com.alipay.sofa.rpc.auth.Authenticator;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

@Extension(
        value = "rpcException",
        order = -1001
)
@AutoActive(
        providerSide = true
)
public class RpcExceptionFilter  extends Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Authenticator.class);

    public static boolean exception = false;

    public RpcExceptionFilter() {
    }

    public boolean needToLoad(FilterInvoker invoker) {
       return true;
    }

    public SofaResponse invoke(FilterInvoker filterInvoker, SofaRequest sofaRequest) throws SofaRpcException {
       if (exception) {
           throw new SofaRpcException("throw custom server rpc exception...");
       }
        return filterInvoker.invoke(sofaRequest);
    }
}
