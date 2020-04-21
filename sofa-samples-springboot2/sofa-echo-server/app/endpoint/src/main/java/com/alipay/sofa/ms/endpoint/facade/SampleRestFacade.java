package com.alipay.sofa.ms.endpoint.facade;


import com.alipay.sofa.ms.endpoint.constants.URLConstants;
import com.alipay.sofa.ms.endpoint.exception.CommonException;
import com.alipay.sofa.ms.endpoint.constants.RestConstants;
import com.alipay.sofa.ms.endpoint.model.DemoUserModel;
import com.alipay.sofa.ms.endpoint.response.RestSampleFacadeResp;

import javax.ws.rs.*;

/**
 * rest service interface
 * <p>
 * the get, post, delete, put method each deal with the function of query info, add info, delete info and update info
 * <p>
 * <p>
 * <p/>
 * Created by yangguanchao on 16/11/18.
 */
@Path(URLConstants.REST_API_PEFFIX + "/users")
@Consumes(RestConstants.DEFAULT_CONTENT_TYPE)
@Produces(RestConstants.DEFAULT_CONTENT_TYPE)
public interface SampleRestFacade {

    /**
     * query
     *
     * http://localhost:8341/webapi/users/xiaoming
     *
     * @param userName
     * @return
     */
    @GET
    @Path("/{userName}")
    public RestSampleFacadeResp<DemoUserModel> userInfo(@PathParam("userName") String userName) throws CommonException;

    /***

     *
     * @param userName user's account name
     * @param realName user's real name
     * @return
     */

    /**
     * add
     *
     * you can use postman to mock the post request
     * <p/>
     * http://localhost:8341/webapi/users
     * <p/>
     * post method add a user whose information is in the http request
     * @param user
     * @return
     */
    @POST
    @Path("/")
    public RestSampleFacadeResp<Integer> addUserInfo(DemoUserModel user);

    /**
     * delete
     *
     * @param userName
     * @return
     */
    @DELETE
    @Path("/{userName}")
    public RestSampleFacadeResp<Integer> deleteUser(@PathParam("userName") String userName);

    /***
     * update
     *
     * @param userName
     * @param demoUserModel
     * @return
     */
    @PUT
    @Path("/{userName}")
    public RestSampleFacadeResp<Integer> updateUser(@PathParam("userName") String userName, DemoUserModel demoUserModel);
}
