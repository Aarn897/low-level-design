package com.lld.hotel.reservation.system.service.guest;

import com.lld.hotel.reservation.system.model.Guest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GuestServiceImpl implements GuestService {
    private final ConcurrentMap<String, Guest> guests = new ConcurrentHashMap<>();

    @Override
    public void addGuest(Guest guest) {
        guests.put(guest.getGuestId(), guest);
    }

    @Override
    public Guest getGuest(String guestId) {
        return guests.get(guestId);
    }
}