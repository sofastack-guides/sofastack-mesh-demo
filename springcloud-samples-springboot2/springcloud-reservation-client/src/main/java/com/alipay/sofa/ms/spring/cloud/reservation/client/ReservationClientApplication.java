package com.alipay.sofa.ms.spring.cloud.reservation.client;

import com.alipay.sofa.ms.spring.cloud.reservation.service.CgoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Try the following urls to access reservation service indirectly
 * http://localhost:9999/reservations/names or http://localhost:9999/reservations/names-feign to access the reservation service
 *
 * <br />
 *
 * Try the following urls to access reservation service directly, i.e. as zuul proxy
 * http://localhost:9999/sp-cgo-server/reservations
 *
 * @author yiji@apache.org
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ImportResource("spring/sp-cgo-client.xml")
public class ReservationClientApplication implements ApplicationContextAware {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReservationClientApplication.class);

  @Bean
  CommandLineRunner runner(DiscoveryClient dc) {
    return args -> {
      dc.getInstances("sp-cgo-server")
              .forEach(si -> System.out.println(String.format(
                      "Found %s %s:%s", si.getServiceId(), si.getHost(), si.getPort())));
    };
  }

  /**
   * The load balanced rest template, it will be customized with load balancer interceptors
   * @see LoadBalancerAutoConfiguration
   */
  @LoadBalanced
  @Bean
  RestTemplate loadBalanced() {
    return new RestTemplate();
  }

  /**
   * The normal rest template
   */
  @Primary
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(ReservationClientApplication.class, args);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    CgoService echoService = (CgoService) applicationContext.getBean("echoService"); // get remote service proxy
    new Thread(() -> {
      for (; ; ) {
        try {
          TimeUnit.SECONDS.sleep(1L);
          String status1 = echoService.echo("cgo Hello world!");
          LOGGER.info(">>>>>>>> echo result: " + status1);
        } catch (Exception e) {
          LOGGER.error(">>>>>>>> echo result: " + e.getMessage());
        }
      }
    }).start();
  }
}
