/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.webservice.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWebService {

    String sayHello(@WebParam(name = "name") String name);

}