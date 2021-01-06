package com.alipay.sofa.ms.spring.cloud.reservation.client.service;

import com.alipay.sofa.ms.spring.cloud.reservation.client.dto.Reservation;
import java.util.Collections;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceFallback implements ReservationService {

  @Override
  public String sayHi(String name) {
    return "DOWNGRADE";
  }

  @Override
  public Resources<Reservation> queryReservations() {
    return new Resources<>(Collections.emptyList());
  }

}
