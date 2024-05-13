package com.silkroutestore.payment.service;

import com.silkroutestore.payment.model.PaymentRequest;
import com.silkroutestore.payment.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
