package com.alipay.sofa.ms.endpoint.impl;


import com.alipay.sofa.ms.endpoint.exception.CommonException;
import com.alipay.sofa.ms.endpoint.facade.SampleRestFacade;
import com.alipay.sofa.ms.endpoint.model.DemoUserModel;
import com.alipay.sofa.ms.endpoint.response.RestSampleFacadeResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * rest service interface impl
 * <p>
 * sofa rest resource to deal with rest request
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
public class SampleRestFacadeRestImpl implements SampleRestFacade {

    private static final Logger logger = LoggerFactory.getLogger("MDC-EXAMPLE");

    public RestSampleFacadeResp<DemoUserModel> userInfo(@QueryParam("user") String userName, @QueryParam("sleep") String sleep) throws CommonException {

        DemoUserModel demoUserModel = new DemoUserModel();
        demoUserModel.setRealName("Real " + userName);
        demoUserModel.setUserName(userName);

        logger.info("rest mdc example");

        RestSampleFacadeResp<DemoUserModel> result = new RestSampleFacadeResp<DemoUserModel>();
        result.setData(demoUserModel);
        result.setSuccess(true);
        return result;
    }
}
