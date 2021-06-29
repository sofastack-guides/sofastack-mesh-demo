package com.alipay.sofa.ms.spring.cloud.reservation.service.govern;

/**
 * @author zxy
 * @date 2020-12-11
 */
public interface SpringCloudDowngradeService {

    /**
     * echo
     * @param name      请求名称
     * @param exception 是否抛出异常
     * @param sleep     sleep时间
     * @return 信息
     */
    String echo(String name, Boolean exception, Integer sleep);

    /**
     * echo1
     * @param name      请求名称
     * @param exception 是否抛出异常
     * @param sleep     sleep时间
     * @return 信息
     */
    String echo1(String name, Boolean exception, Integer sleep);
}
