package com.silkroutestore.payment.controller;

import com.silkroutestore.payment.model.PaymentRequest;
import com.silkroutestore.payment.model.PaymentResponse;
import com.silkroutestore.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        long transactionId = paymentService.doPayment(paymentRequest);
        return new ResponseEntity<>(transactionId, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable long orderId) {
        PaymentResponse paymentResponse =
                paymentService.getPaymentDetailsByOrderId(orderId);

        return new ResponseEntity<>(
                paymentResponse,
                HttpStatus.OK
        );
    }

}
