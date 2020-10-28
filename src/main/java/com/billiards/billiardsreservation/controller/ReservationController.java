package com.billiards.billiardsreservation.controller;

import com.billiards.billiardsreservation.domain.ReservationDto;
import com.billiards.billiardsreservation.domain.ReservationType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("v1/reservation")
public class ReservationController {

    @PostMapping(value = "create")
    public void create(ReservationDto reservationDto){
    }

    @GetMapping(value = "get")
    public ReservationDto get(Long id){
        return new ReservationDto(1L, LocalDate.now(), LocalTime.MIDNIGHT, ReservationType.TABLE);
    }

    @GetMapping(value = "get_all")
    public Set<ReservationDto>getAll(){
        return new HashSet<>();
    }

    @PutMapping(value = "edit")
    public ReservationDto edit(ReservationDto reservationDto){
        return new ReservationDto(1L, LocalDate.now(), LocalTime.now(), ReservationType.BILLIARDS);
    }

    @DeleteMapping(value = "cancel")
    public void cancel(Long id){
    }
}
