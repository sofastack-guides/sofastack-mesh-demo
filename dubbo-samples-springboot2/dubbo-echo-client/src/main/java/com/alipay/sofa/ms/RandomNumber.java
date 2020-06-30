/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

import java.util.Random;

/**
 * @author yiji
 * @version : RandomNumber.java, v 0.1 2020年06月07日 10:44 上午 yiji Exp $
 */
public class RandomNumber {

    public static void main(String[] args) {

        Random r = new Random();

        int parsed = r.nextInt(65536);

        double value = parsed / (100000.0);

        System.out.println(value);

        System.out.println(value * 1000000);

    }

}