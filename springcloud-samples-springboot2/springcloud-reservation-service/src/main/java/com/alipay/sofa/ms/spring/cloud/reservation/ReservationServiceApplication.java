package com.alipay.sofa.ms.spring.cloud.reservation;

import com.alipay.sofa.ms.spring.cloud.reservation.entity.Reservation;
import com.google.common.base.Splitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author yiji@apache.org
 */
@EnableDiscoveryClient
@SpringBootApplication
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
