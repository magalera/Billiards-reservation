package com.billiards.billiardsreservation.controller;

import com.billiards.billiardsreservation.domain.Reservation;
import com.billiards.billiardsreservation.domain.ReservationDto;
import com.billiards.billiardsreservation.mapper.ReservationMapper;
import com.billiards.billiardsreservation.service.ReservationDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationDbService service;
    @Autowired
    private ReservationMapper mapper;

    @PostMapping
    public void create(@RequestBody ReservationDto reservationDto) {
        service.save(mapper.mapToReservation(reservationDto));
    }

    @GetMapping(value = "/{id}")
    public ReservationDto get(@PathVariable Long id) throws ReservationNotFoundException {
        return mapper.mapToReservationDto(service.findById(id).orElseThrow(ReservationNotFoundException::new));
    }

    @GetMapping(value = "/get_all")
    public Set<ReservationDto> getAll() {
        return mapper.mapToReservationsDto(service.getAllReservations());
    }

    @PutMapping
    public ReservationDto edit(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = service.save(mapper.mapToReservation(reservationDto));
        return mapper.mapToReservationDto(reservation);
    }

    @DeleteMapping(value = "/{id}")
    public void cancel(@PathVariable Long id) throws ReservationNotFoundException {
        service.findById(id).orElseThrow(ReservationNotFoundException::new);
        service.delete(id);
    }
}
