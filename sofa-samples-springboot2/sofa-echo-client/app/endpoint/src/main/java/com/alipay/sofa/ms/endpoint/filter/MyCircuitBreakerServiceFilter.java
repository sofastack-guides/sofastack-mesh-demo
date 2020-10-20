package com.alipay.sofa.ms.endpoint.filter;

import com.alipay.sofa.ms.dto.RequestType;
import com.alipay.sofa.rpc.api.context.ResultCodeEnum;
import com.alipay.sofa.rpc.common.RemotingConstants;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;

import java.util.Objects;

/**
 * @author zxy
 */
@Extension("circuitBreakerServiceFilter")
@AutoActive(consumerSide = true)
public class MyCircuitBreakerServiceFilter extends Filter {
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        if (!"getServiceInfo".equals(request.getMethod().getName())) {
            return invoker.invoke(request);
        }
        if (Objects.equals(RequestType.TIMEOUT.getType(), request.getMethodArgs()[1]) && request.getMethodArgs()[2] != null) {
            request.setTimeout((Integer) request.getMethodArgs()[2]);
        }
        return invoker.invoke(request);
    }
}