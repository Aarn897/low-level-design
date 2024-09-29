package com.lld.hotel.reservation.system.service.reservation;

import com.lld.hotel.reservation.system.model.Guest;
import com.lld.hotel.reservation.system.model.Reservation;
import com.lld.hotel.reservation.system.service.room.RoomType;
import java.time.LocalDate;

interface ReservationService {
    Reservation bookRoom(Guest guest, RoomType roomType, LocalDate checkIn, LocalDate checkOut) throws Exception;
    void checkIn(String reservationId) throws Exception;
    void checkOut(String reservationId) throws Exception;
}