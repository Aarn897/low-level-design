package com.lld.hotel.reservation.system.service.room;

import com.lld.hotel.reservation.system.model.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final List<Room> rooms = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<Room> getAvailableRooms(RoomType roomType, LocalDate checkIn, LocalDate checkOut) {
        // For simplicity, ignoring date conflicts
        List<Room> available = new ArrayList<>();
        synchronized (rooms) {
            for (Room room : rooms) {
                if (room.getRoomType() == roomType && room.isAvailable()) {
                    available.add(room);
                }
            }
        }
        return available;
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }
}
