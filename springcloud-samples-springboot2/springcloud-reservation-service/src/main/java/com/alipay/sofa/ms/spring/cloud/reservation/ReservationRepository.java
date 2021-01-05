package com.alipay.sofa.ms.spring.cloud.reservation;

import com.alipay.sofa.ms.spring.cloud.reservation.entity.Reservation;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author yiji@apache.org
 */
@RepositoryRestResource
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

  /**
   * http://localhost:8000/reservations/search/by-name?name=Tom
   * @param reservationName
   * @return
   */
  @RestResource(path = "by-name")
  List<Reservation> findByReservationName(@Param("name") String reservationName);
}
