package com.lld.hotel.reservation.system.service.room;

import com.lld.hotel.reservation.system.model.Room;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<Room> getAvailableRooms(RoomType roomType, LocalDate checkIn, LocalDate checkOut);
    void addRoom(Room room);
}