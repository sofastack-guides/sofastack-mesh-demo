package com.alipay.sofa.ms.spring.cloud.reservation.service.service;

import com.alipay.sofa.ms.service.Request;
import com.alipay.sofa.ms.service.Response;
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
@FeignClient(value = "springcloud-reservation-reply",path = "/benchmark", configuration = BenchmarkService.CustomerClientConfiguration.class,fallback = BenchmarkServiceFallback.class )
public interface BenchmarkService {
    @RequestMapping(value = "request", method = RequestMethod.POST)
    Response request(Request req);

    @RequestMapping(value = "clearAndReset", method = RequestMethod.POST)
    Response clearAndReset();

    @Configuration
    @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
    public static class CustomerClientConfiguration {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public Decoder feignDecoder() {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                    .registerModule(new Jackson2HalModule());

            MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
            jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
            jacksonConverter.setObjectMapper(objectMapper);

            ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
            return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
        }
    }
}
