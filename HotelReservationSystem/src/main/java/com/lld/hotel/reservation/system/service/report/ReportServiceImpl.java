package com.lld.hotel.reservation.system.service.report;

import com.lld.hotel.reservation.system.model.Reservation;
import com.lld.hotel.reservation.system.service.reservation.ReservationStatus;
import com.lld.hotel.reservation.system.service.room.RoomType;
import com.lld.hotel.reservation.system.service.reservation.ReservationServiceImpl;
import java.util.EnumMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReservationServiceImpl reservationService;

    @Override
    public void generateOccupancyReport() {
        Map<RoomType, Integer> occupancy = new EnumMap<>(RoomType.class);
        for (RoomType type : RoomType.values()) {
            occupancy.put(type, 0);
        }
        for (Reservation reservation : reservationService.getAllReservations()) {
            if (reservation.getStatus() == ReservationStatus.BOOKED || reservation.getStatus() == ReservationStatus.CHECKED_IN) {
                RoomType type = reservation.getRoom().getRoomType();
                occupancy.put(type, occupancy.get(type) + 1);
            }
        }
        log.info("Occupancy Report :");
        for (Map.Entry<RoomType, Integer> entry : occupancy.entrySet()) {
            log.info("{} : {} rooms occupied", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void generateRevenueReport() {
        // For simplicity, assume fixed pricing
        Map<RoomType, Double> pricing = new EnumMap<>(RoomType.class);
        pricing.put(RoomType.SINGLE, 100.0);
        pricing.put(RoomType.DOUBLE, 150.0);
        pricing.put(RoomType.DELUXE, 200.0);
        pricing.put(RoomType.SUITE, 300.0);

        double totalRevenue = 0.0;
        for (Reservation reservation : reservationService.getAllReservations()) {
            if (reservation.getStatus() == ReservationStatus.CHECKED_OUT) {
                RoomType type = reservation.getRoom().getRoomType();
                long days = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
                totalRevenue += pricing.get(type) * days;
            }
        }
        log.info("Total Revenue: Rs {}", totalRevenue);
    }
}