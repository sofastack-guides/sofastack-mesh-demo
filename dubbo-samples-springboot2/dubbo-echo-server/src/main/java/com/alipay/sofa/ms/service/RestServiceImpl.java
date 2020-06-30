/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author yiji
 * @version : RestServiceImpl.java, v 0.1 2020年06月30日 5:51 下午 yiji Exp $
 */
@Path("/users")
public class RestServiceImpl implements RestService {

    @POST
    @Path("/echo")
    public String echo(String id) {
        return id;
    }
}