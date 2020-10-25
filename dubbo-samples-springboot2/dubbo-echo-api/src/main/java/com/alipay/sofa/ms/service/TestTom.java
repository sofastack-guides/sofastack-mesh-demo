/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

import java.io.Serializable;

/**
 *
 * @author xingqi
 * @version $Id: TestTom.java, v 0.1 2020年10月25日 3:42 PM xingqi Exp $
 */
public class TestTom implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public TestTom setName(String name) {
        this.name = name;
        return this;
    }
}