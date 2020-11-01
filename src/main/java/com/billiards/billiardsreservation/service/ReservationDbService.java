package com.billiards.billiardsreservation.service;

import com.billiards.billiardsreservation.domain.Reservation;
import com.billiards.billiardsreservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ReservationDbService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Set<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> get(Long id){
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public void delete(Long id){
        reservationRepository.deleteById(id);
    }
}
