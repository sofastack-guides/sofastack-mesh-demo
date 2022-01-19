package com.alipay.sofa.ms.spring.cloud.reservation.client.controller;

import com.alipay.sofa.ms.spring.cloud.reservation.client.dto.Reservation;
import com.alipay.sofa.ms.spring.cloud.reservation.client.service.ReservationService;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yiji@apache.org
 */
@RestController
@RequestMapping("/reservations")
public class ReservationApiGatewayRestController implements ApplicationContextAware {

  private static final Logger logger = LoggerFactory.getLogger(ReservationApiGatewayRestController.class);
  @Autowired
  private RestTemplate rt;

  @Autowired
  private ReservationService reservationService;

  private Collection<String> getReservationNamesFallback() {
    return Collections.emptyList();
  }

  @RequestMapping("/names")
  public Collection<String> getReservationNames() {
    logger.debug("Get reservation names via rest template!");

    ParameterizedTypeReference<Resources<Reservation>> parameterizedTypeReference =
            new ParameterizedTypeReference<Resources<Reservation>>() {
            };

    ResponseEntity<Resources<Reservation>> exchange = rt.exchange(
            "http://reservation-service/reservations",
            HttpMethod.GET, null, parameterizedTypeReference);

    return exchange.getBody().getContent().stream().map(Reservation::getReservationName).collect(Collectors.toList());
  }

  @RequestMapping("/names-feign")
  public Collection<String> getReservationNamesViaFeign() {
    logger.debug("Get reservation names via feign!");

    Resources<Reservation> reservations = reservationService.queryReservations();

    return reservations.getContent().stream().map(Reservation::getReservationName).collect(Collectors.toList());
  }

  @RequestMapping("/echo/dubbo")
  public String echo(String message){
    HttpHeaders headers = new HttpHeaders();
    headers.add("service", "reservation-client");
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> result = rt.exchange(
            "http://127.0.0.1:10088/reservations/" + message + "/echo",
            HttpMethod.POST, entity, String.class);
    return result.getBody();
  }

  @RequestMapping("/echo/springcloud")
  public String echo2(String message){
    HttpHeaders headers = new HttpHeaders();
    headers.add("service", "reservation-service");
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> result = rt.exchange(
            "http://127.0.0.1:10088/reservations/" + message + "/echo",
            HttpMethod.POST, entity, String.class);
    return result.getBody();
  }

  @RequestMapping("/echo")
  public String echo3(String message, @RequestHeader String service){
    HttpHeaders headers = new HttpHeaders();
    headers.add("service", service);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<String> result = rt.exchange(
            "http://127.0.0.1:10088/reservations/" + message + "/echo",
            HttpMethod.POST, entity, String.class);
    return result.getBody();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    new Thread(() -> {
      for (; ; ) {
        try {
          TimeUnit.SECONDS.sleep(1L);
          logger.info(">>>>>>> get reservations via feign: " + getReservationNamesViaFeign());
          logger.info(">>>>>>> get reservations via rt: " + getReservationNames());
        } catch (Exception e) {
          logger.error(">>>>>>> get reservations: : " + e.getMessage());
        }
      }
    }).start();
  }
}
