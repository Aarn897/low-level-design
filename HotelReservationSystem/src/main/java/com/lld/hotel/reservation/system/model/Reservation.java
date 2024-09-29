package com.lld.hotel.reservation.system.model;

import com.lld.hotel.reservation.system.service.reservation.ReservationStatus;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class Reservation {
    private final String reservationId;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private volatile ReservationStatus status;

    public Reservation(String reservationId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.BOOKED;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}