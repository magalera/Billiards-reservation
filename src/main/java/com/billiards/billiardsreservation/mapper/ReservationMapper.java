package com.billiards.billiardsreservation.mapper;

import com.billiards.billiardsreservation.domain.Reservation;
import com.billiards.billiardsreservation.domain.ReservationDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    public Reservation mapToReservation(ReservationDto reservationDto) {
        return new Reservation(
                reservationDto.getReservationId(),
                reservationDto.getDate(),
                reservationDto.getTime(),
                reservationDto.getType()
        );
    }

    public ReservationDto mapToReservationDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getReservationId(),
                reservation.getDate(),
                reservation.getTime(),
                reservation.getType()
        );
    }

    public Set<Reservation> mapToReservations(Set<ReservationDto> reservationsDto) {
        return reservationsDto.stream()
                .map(reservationDto -> new Reservation(
                        reservationDto.getReservationId(),
                        reservationDto.getDate(),
                        reservationDto.getTime(),
                        reservationDto.getType()))
                .collect(Collectors.toSet());
    }

    public Set<ReservationDto> mapToReservationsDto(Set<Reservation> reservations) {
        return reservations.stream()
                .map(reservation -> new ReservationDto(
                        reservation.getReservationId(),
                        reservation.getDate(),
                        reservation.getTime(),
                        reservation.getType()))
                .collect(Collectors.toSet());
    }
}
