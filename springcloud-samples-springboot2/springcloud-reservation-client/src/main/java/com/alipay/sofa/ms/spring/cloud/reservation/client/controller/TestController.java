/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms.spring.cloud.reservation.client.controller;

import com.alipay.sofa.ms.pb.HelloReply;
import com.alipay.sofa.ms.pb.HelloRequest;
import com.alipay.sofa.ms.service.EchoService;
import com.alipay.sofa.ms.service.TestTom;
import com.alipay.sofa.ms.service.TomService;
import com.alipay.sofa.ms.spring.cloud.reservation.client.grpc.JavaGrpcClient;
import com.alipay.sofa.ms.spring.cloud.reservation.client.service.ReservationService;
import com.alipay.sofa.ms.spring.cloud.reservation.client.service.TestBean;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *
 * @author xingqi
 * @version $Id: TestController.java, v 0.1 2020年10月25日 4:01 PM xingqi Exp $
 */
@RestController
@RequestMapping("/meshtest")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    @LoadBalanced
    private RestTemplate rt;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EchoService echoService;

    @Resource(name = "tomGroupService")
    private TomService tomGroupService;

    @Resource(name = "tomVersionService")
    private TomService tomVersionService;

    @Resource(name = "tomGroupVersionService")
    private TomService tomGroupVersionService;

    @RequestMapping("/rt")
    public String getRtMsg() {
        try {
            ParameterizedTypeReference<String> parameterizedTypeReference =
                    new ParameterizedTypeReference<String>() {
                    };

            ResponseEntity<String> exchange = rt.exchange(
                    "http://reservation-service/echo/name/aaa",
                    HttpMethod.GET, null, parameterizedTypeReference);

            return exchange.getBody();
        } catch (Exception e) {
            logger.error("reservation-service/echo/name/aaa error", e);
            return e.getMessage();
        }
    }

    @RequestMapping("/rt/http/{ip}/{port}")
    public String getRtHttpMsg(@PathVariable String ip, @PathVariable String port) {
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(5000);
            requestFactory.setReadTimeout(3000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            return restTemplate.getForObject("http://" + ip + ":" + port + "/echo/name/aaa", String.class);
        } catch (Throwable e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/feign")
    public String getFeignMsg() {
        return reservationService.echoPost(new TestBean().setName("testAaa"));
    }

    @RequestMapping("/rt/dubbo")
    public String getRtMsgDubbo() {

        ParameterizedTypeReference<String> parameterizedTypeReference =
                new ParameterizedTypeReference<String>() {
                };

        ResponseEntity<String> exchange = rt.exchange(
                "http://reservation-service/echo/name/aaa",
                HttpMethod.GET, null, parameterizedTypeReference);

        String msg = exchange.getBody();

        return echoService.echo(msg);
    }

    @RequestMapping("/feign/dubbo")
    public String getReservationNamesViaFeign() {
        String msg = reservationService.echoPost(new TestBean().setName("testAaa"));
        msg = tomGroupService.tom(new TestTom().setName(msg));
        tomVersionService.tom(new TestTom().setName(msg));
        return tomGroupVersionService.tom(new TestTom().setName(msg));
    }

    @RequestMapping("/dubbo/tomGroupService")
    public String getReservationNamesViaFeign222() {
        return tomGroupService.tom(new TestTom().setName("tomGroupService"));
    }

    @Autowired
    private JavaGrpcClient javaGrpcClient;

    @RequestMapping("/grpc/{ip}")
    public String grpc(@PathVariable String ip) {

        Channel channel = ManagedChannelBuilder
                .forAddress(ip, 50051)
                .usePlaintext(true)
                .build();

        HelloRequest request = HelloRequest.newBuilder().setName("grpc").build();
        HelloReply result = javaGrpcClient.run(channel, o -> o.sayHello(request));
        return result.getMessage();
    }

    @RequestMapping("/grpc/{ip}/{port}")
    public String grpcPort(@PathVariable String ip, @PathVariable String port) {

        Channel channel = ManagedChannelBuilder
                .forAddress(ip, Integer.parseInt(port))
                .usePlaintext(true)
                .build();

        HelloRequest request = HelloRequest.newBuilder().setName("grpc").build();
        HelloReply result = javaGrpcClient.run(channel, o -> o.sayHello(request));
        return result.getMessage();
    }

    @RequestMapping("/dubbo/echo/{msg}")
    public String getDubboEcho(@PathVariable String msg) {
        try {
            return echoService.echo(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}