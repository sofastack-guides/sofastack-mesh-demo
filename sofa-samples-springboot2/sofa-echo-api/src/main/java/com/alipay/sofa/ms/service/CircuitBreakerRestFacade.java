package com.alipay.sofa.ms.service;

import com.alipay.sofa.ms.constants.RestConstants;
import com.alipay.sofa.ms.constants.URLConstants;
import com.taobao.remoting.RemotingException;

import javax.ws.rs.*;

/**
 * @author zxy
 * @date 2020-09-27 16:47
 **/
@Path(URLConstants.REST_API_PEFFIX +"/circuitBreaker")
@Consumes(RestConstants.DEFAULT_CONTENT_TYPE)
@Produces(RestConstants.DEFAULT_CONTENT_TYPE)
public interface CircuitBreakerRestFacade {


    @GET
    @Path("/getServiceInfo")
    String getServiceInfo(@QueryParam("executionTime") Long executionTime, @QueryParam("type") String type,@QueryParam("timeout")Integer timeout) ;
}
