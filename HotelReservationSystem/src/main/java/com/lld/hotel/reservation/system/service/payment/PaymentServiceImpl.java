package com.lld.hotel.reservation.system.service.payment;

import com.lld.hotel.reservation.system.service.payment.paymentmethods.PaymentMethod;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount);
    }
}
