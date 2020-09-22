/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * http://localhost:7799/reservations/hello 转发到springcloud-reservation-service应用8080端口
 * http://localhost:8080/reservations/hello
 *
 * @author yiji@apache.org
 * @version : ReservationGatewayApplication.java, v 0.1 2020年02月26日 8:10 下午 yiji Exp $
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ReservationGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationGatewayApplication.class, args);
    }

}