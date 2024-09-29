package com.lld.hotel.reservation.system.service.payment.paymentmethods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CashPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        log.info("Paid Rs {} in cash", amount);
    }
}