package com.alipay.sofa.ms.spring.cloud.reservation.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * Try the following urls to access reservation service indirectly
 * http://localhost:9999/reservations/names or http://localhost:9999/reservations/names-feign to access the reservation service
 *
 * <br />
 *
 * Try the following urls to access reservation service directly, i.e. as zuul proxy
 * http://localhost:9999/reservation-service/reservations
 *
 * @author yiji@apache.org
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ReservationClientApplication {
  @Bean
  CommandLineRunner runner(DiscoveryClient dc) {
    return args -> {
      dc.getInstances("reservation-service")
              .forEach(si -> System.out.println(String.format(
                      "Found %s %s:%s", si.getServiceId(), si.getHost(), si.getPort())));
    };
  }


  public static void main(String[] args) {
    SpringApplication.run(ReservationClientApplication.class, args);
  }
}
