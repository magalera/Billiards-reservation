package com.billiards.billiardsreservation.service;


import com.billiards.billiardsreservation.controller.ReservationNotFoundException;
import com.billiards.billiardsreservation.domain.Reservation;
import com.billiards.billiardsreservation.domain.ReservationType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@Transactional
@Commit
public class ReservationDbServiceTestSuite {
    @Autowired
    private ReservationDbService service;

    public Set<Reservation> saveAll() {
        Set<Reservation> reservations = new HashSet<>();
        Reservation reservation1 = service.save(new Reservation(null, LocalDate.now(), LocalTime.MIDNIGHT, ReservationType.TABLE));
        reservations.add(reservation1);
        Reservation reservation2 = service.save(new Reservation(null, LocalDate.now().minusDays(2L), LocalTime.MIDNIGHT, ReservationType.BILLIARDS));
        reservations.add(reservation2);
        Reservation reservation3 = service.save(new Reservation(null, LocalDate.now().plusDays(4L), LocalTime.MIDNIGHT, ReservationType.TABLE));
        reservations.add(reservation3);

        return reservations;
    }

    @Test
    public void testGetAllReservations() {
        //Given
        Set<Reservation> reservations = saveAll();

        //When
        Set<Reservation> allReservations = service.getAllReservations();

        //Then
        Assert.assertEquals(3, allReservations.size());

        //Clean up
        service.deleteAll(reservations);
    }

    @Test
    public void testFindById() {
        //Given
        Reservation reservation = service.save(new Reservation(null, LocalDate.now(), LocalTime.MIDNIGHT, ReservationType.TABLE));

        //When
        Reservation saved = service.save(reservation);
        Optional<Reservation> actual = service.findById(saved.getReservationId());

        //Then
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(reservation.getReservationId(), actual.get().getReservationId());
        Assert.assertEquals(reservation.getDate(), actual.get().getDate());
        Assert.assertEquals(reservation.getTime(), actual.get().getTime());
        Assert.assertEquals(reservation.getType(), actual.get().getType());

        //Clean up
        service.delete(actual.get().getReservationId());
    }

    @Test
    public void testCreate() throws ReservationNotFoundException {
        //Given
        Reservation reservation = new Reservation(null, LocalDate.of(2020, 11, 2), LocalTime.of(5, 30), ReservationType.TABLE);

        //When
        Reservation actual = service.save(reservation);

        //Then
        Optional<Reservation> byId = service.findById(actual.getReservationId());
        Assert.assertTrue(byId.isPresent());
        Assert.assertEquals(reservation.getDate(), actual.getDate());
        Assert.assertEquals(reservation.getTime(), actual.getTime());

        //Clean up
        Reservation findReservation = service.findById(byId.get().getReservationId())
                .orElseThrow(ReservationNotFoundException::new);
        service.delete(findReservation.getReservationId());
    }

    @Test
    public void testDelete() {
        //Given
        Reservation reservation = new Reservation(null, LocalDate.of(2020, 11, 2), LocalTime.of(5, 30), ReservationType.TABLE);

        //When
        Reservation actual = service.save(reservation);
        service.delete(actual.getReservationId());

        //Then
        Optional<Reservation> notExist = service.findById(actual.getReservationId());
        Assert.assertFalse(notExist.isPresent());
    }

    @Test
    public void testDeleteAll() {
        //Given
        Set<Reservation> reservations = saveAll();

        //When
        service.deleteAll(reservations);

        //Then
        Set<Reservation> notExistReservations = service.getAllReservations();
        Assert.assertTrue(notExistReservations.isEmpty());
    }
}