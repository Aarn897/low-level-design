package com.lld.hotel.reservation.system.service.payment.paymentmethods;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CreditCardPayment implements PaymentMethod {

    private final String cardNumber;

    @Override
    public void pay(double amount) {
        log.info("Paid Rs" + amount + " using Credit Card: " + cardNumber);
    }
}