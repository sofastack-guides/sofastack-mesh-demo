/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.service;

/**
 * @author yiji
 * @version : BenchmarkService.java, v 0.1 2020年04月01日 4:27 下午 yiji Exp $
 */
public interface BenchmarkService {

    // 单独分离每个方法，防止jvm方法调用优化
    String send_512_byte(String content);

    String send_1k(String content);

    String send_2k(String content);

    String send_4k(String content);

    String send_8k(String content);
    
    //String sendBytes(String content);

}