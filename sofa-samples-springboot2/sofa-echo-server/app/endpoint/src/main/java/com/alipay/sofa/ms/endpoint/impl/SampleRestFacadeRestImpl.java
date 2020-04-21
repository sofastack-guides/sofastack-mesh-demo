package com.alipay.sofa.ms.endpoint.impl;


import com.alipay.sofa.ms.endpoint.exception.CommonException;
import com.alipay.sofa.ms.endpoint.facade.SampleRestFacade;
import com.alipay.sofa.ms.endpoint.model.DemoUserModel;
import com.alipay.sofa.ms.endpoint.response.RestSampleFacadeResp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PathParam;

/**
 * rest service interface impl
 * <p>
 * sofa rest resource to deal with rest request
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
public class SampleRestFacadeRestImpl implements SampleRestFacade {

    private static final Logger logger = LoggerFactory.getLogger("MDC-EXAMPLE");

    public RestSampleFacadeResp<DemoUserModel> userInfo(@PathParam("userName") String userName) throws CommonException {

        DemoUserModel demoUserModel = new DemoUserModel();
        demoUserModel.setRealName("Real " + userName);
        demoUserModel.setUserName(userName);

        logger.info("rest mdc example");

        RestSampleFacadeResp<DemoUserModel> result = new RestSampleFacadeResp<DemoUserModel>();
        result.setData(demoUserModel);
        result.setSuccess(true);
        return result;
    }

    public RestSampleFacadeResp<Integer> addUserInfo(DemoUserModel user) {
        int id = 1;
        RestSampleFacadeResp<Integer> result = new RestSampleFacadeResp<Integer>();
        result.setData(id);
        result.setSuccess(true);
        return result;
    }

    public RestSampleFacadeResp<Integer> deleteUser(String userName) {
        int deletedCount = 1;
        RestSampleFacadeResp<Integer> result = new RestSampleFacadeResp<Integer>();
        result.setData(deletedCount);
        result.setSuccess(true);
        return result;
    }

    public RestSampleFacadeResp<Integer> updateUser(@PathParam("userName") String userName, DemoUserModel demoUserModel) {
        int updatedCount = 1;
        RestSampleFacadeResp<Integer> result = new RestSampleFacadeResp<Integer>();
        result.setData(updatedCount);
        result.setSuccess(true);
        return result;
    }
}
