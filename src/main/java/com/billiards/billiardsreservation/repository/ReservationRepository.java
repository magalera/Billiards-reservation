package com.billiards.billiardsreservation.repository;

import com.billiards.billiardsreservation.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    Set<Reservation> findAll();

    @Override
    Optional<Reservation> findById(Long id);

    @Override
    Reservation save(Reservation reservation);

    @Override
    void deleteById(Long id);
}
