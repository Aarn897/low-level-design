package com.lld.hotel.reservation.system.service.payment.paymentmethods;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class OnlinePayment implements PaymentMethod {

    private final String email;

    @Override
    public void pay(double amount) {
        log.info("Paid Rs {} online via: {}", amount, email);
    }
}