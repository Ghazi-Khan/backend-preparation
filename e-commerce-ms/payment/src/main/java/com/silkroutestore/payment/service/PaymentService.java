package com.silkroutestore.payment.service;

import com.silkroutestore.payment.model.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
