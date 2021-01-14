package com.alipay.sofa.ms.spring.cloud.reservation.service;

import com.alipay.sofa.ms.spring.cloud.reservation.service.entity.Reservation;
import com.google.common.base.Splitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yiji@apache.org
 */
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource("spring/sp-cgo-server.xml")
public class ReservationServiceApplication {
  @Bean
  CommandLineRunner runner(ReservationRepository rr) {
    return args -> {
      rr.deleteAll();

      Splitter.on(",").omitEmptyStrings().trimResults().split("Tom, Jerry")
              .forEach(x -> rr.save(new Reservation(x)));
      rr.findAll().forEach(System.out::println);
    };
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(ReservationServiceApplication.class)
            .run(args);
  }
}
