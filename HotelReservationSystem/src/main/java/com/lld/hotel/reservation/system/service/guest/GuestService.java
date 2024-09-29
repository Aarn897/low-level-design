package com.lld.hotel.reservation.system.service.guest;

import com.lld.hotel.reservation.system.model.Guest;

public interface GuestService {
    void addGuest(Guest guest);
    Guest getGuest(String guestId);
}