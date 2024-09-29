package com.lld.hotel.reservation.system.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Guest {
    private final String guestId;
    private final String name;
    private final String contactInfo;
}