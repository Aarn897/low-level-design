package com.lld.hotel.reservation.system.service.reservation;

import com.lld.hotel.reservation.system.model.Guest;
import com.lld.hotel.reservation.system.model.Reservation;
import com.lld.hotel.reservation.system.model.Room;
import com.lld.hotel.reservation.system.service.room.RoomService;
import com.lld.hotel.reservation.system.service.guest.GuestService;
import com.lld.hotel.reservation.system.service.room.RoomType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private final RoomService roomService;
    private final GuestService guestService;
    private final ConcurrentMap<String, Reservation> reservations = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();

    public ReservationServiceImpl(RoomService roomService, GuestService guestService) {
        this.roomService = roomService;
        this.guestService = guestService;
    }

    @Override
    public Reservation bookRoom(Guest guest, RoomType roomType, LocalDate checkIn, LocalDate checkOut) throws Exception {
        lock.lock();
        try {
            List<Room> availableRooms = roomService.getAvailableRooms(roomType, checkIn, checkOut);
            if (availableRooms.isEmpty()) {
                throw new Exception("No available rooms of type: " + roomType);
            }
            Room room = availableRooms.get(0);
            room.setAvailability(false);
            String reservationId = UUID.randomUUID().toString();
            Reservation reservation = new Reservation(reservationId, guest, room, checkIn, checkOut);
            reservations.put(reservationId, reservation);
            return reservation;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void checkIn(String reservationId) throws Exception {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new Exception("Reservation not found");
        }
        if (reservation.getStatus() != ReservationStatus.BOOKED) {
            throw new Exception("Cannot check in. Current status: " + reservation.getStatus());
        }
        reservation.setStatus(ReservationStatus.CHECKED_IN);
        log.info("Checked in reservation: {}", reservationId);
    }

    @Override
    public void checkOut(String reservationId) throws Exception {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new Exception("Reservation not found");
        }
        if (reservation.getStatus() != ReservationStatus.CHECKED_IN) {
            throw new Exception("Cannot check out. Current status: " + reservation.getStatus());
        }
        reservation.setStatus(ReservationStatus.CHECKED_OUT);
        reservation.getRoom().setAvailability(true);
        log.info("Checked out reservation: {}", reservationId);
    }

    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }
}
