package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
import com.alipay.sofa.ms.spring.cloud.reservation.client.dto.Reservation;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * @author zxy
 * @date 2020-09-09 17:34
 **/
@FeignClient(value = "springcloud-reservation-service",path = "/benchmark", configuration = ReservationService.CustomerClientConfiguration.class,fallback =BenchmarkServiceFallback.class )
public interface BenchmarkService {
    @RequestMapping(value = "request", method = RequestMethod.POST)
    Response request(Request req);

    @RequestMapping(value = "clearAndReset", method = RequestMethod.POST)
    Response clearAndReset();

}
