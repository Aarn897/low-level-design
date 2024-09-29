package com.lld.hotel.reservation.system.service.payment;

import com.lld.hotel.reservation.system.service.payment.paymentmethods.PaymentMethod;

public interface PaymentService {
    void processPayment(PaymentMethod paymentMethod, double amount);
}
