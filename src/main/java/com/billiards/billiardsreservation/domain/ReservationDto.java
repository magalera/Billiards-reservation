package com.billiards.billiardsreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private Long reservationId;
    private LocalDate date;
    private LocalTime time;
    private ReservationType type;
}
