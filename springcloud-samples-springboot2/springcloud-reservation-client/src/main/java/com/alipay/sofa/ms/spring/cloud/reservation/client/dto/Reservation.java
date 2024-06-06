package com.alipay.sofa.ms.spring.cloud.reservation.client.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * @author yiji@apache.org
 */
public class Reservation {
  private Long id;
  private String reservationName;

  public Long getId() {
    return id;
  }

  public String getReservationName() {
    return reservationName;
  }
}
