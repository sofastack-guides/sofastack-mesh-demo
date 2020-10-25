package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import com.alipay.sofa.ms.spring.cloud.reservation.client.dto.Reservation;
import com.alipay.sofa.ms.spring.cloud.reservation.client.service.ReservationService.CustomerClientConfiguration;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@FeignClient(value = "reservation-service", configuration = CustomerClientConfiguration.class)
public interface ReservationService {

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    Resources<Reservation> queryReservations();

    @RequestMapping("/echo")
    String echoAll();

    @RequestMapping("/echo/name/{name}")
    String echoPath(@PathVariable("name") String name);

    @RequestMapping(value = "/echo/name")
    String echoParam(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/echo/name", method = RequestMethod.POST)
    String echoPost(@RequestBody TestBean testBean);

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
