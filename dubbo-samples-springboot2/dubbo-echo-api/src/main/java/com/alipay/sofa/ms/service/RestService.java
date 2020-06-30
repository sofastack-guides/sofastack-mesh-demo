/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author yiji
 * @version : RestService.java, v 0.1 2020年06月30日 5:48 下午 yiji Exp $
 */
@Path("/users")
public interface RestService {

    @POST
    @Path("/echo")
    @Consumes
    String echo(String id);

}