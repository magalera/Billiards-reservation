package com.billiards.billiardsreservation.repository;

import com.billiards.billiardsreservation.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Set<Reservation> findAll();
}
