package com.lld.hotel.reservation.system.model;

import com.lld.hotel.reservation.system.service.room.RoomType;
import lombok.Getter;

@Getter
public class Room {
    private final int roomNumber;
    private final RoomType roomType;
    private volatile boolean isAvailable;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }
}