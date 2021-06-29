package com.alipay.sofa.ms.spring.cloud.reservation.service.govern;

/**
 * @author zxy
 * @date 2020-12-11
 */
public interface SpringCloudFaultToleranceService {

    /**
     * echo
     *
     * @param name        请求名称
     * @param sleep       sleep时间
     * @param exception   异常
     * @param exceptionIp 异常ip
     * @return 信息
     */
    String echo(String name, Boolean exception, Integer sleep, String exceptionIp);


    /**
     * echo
     *
     * @param name        请求名称
     * @param sleep       sleep时间
     * @param exception   异常
     * @param exceptionIp 异常ip
     * @return 信息
     */
    String echo1(String name, Boolean exception, Integer sleep, String exceptionIp);
}
