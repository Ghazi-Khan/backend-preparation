package com.silkroutestore.payment.service;

import com.silkroutestore.payment.entity.TransactionDetails;
import com.silkroutestore.payment.model.PaymentMode;
import com.silkroutestore.payment.model.PaymentRequest;
import com.silkroutestore.payment.model.PaymentResponse;
import com.silkroutestore.payment.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording the payment details {} ", paymentRequest);

        TransactionDetails transactionDetails =
                TransactionDetails
                        .builder()
                        .orderId(paymentRequest.getOrderId())
                        .amount(paymentRequest.getAmount())
                        .referenceNumber(paymentRequest.getReferenceNumber())
                        .paymentDate(Instant.now())
                        .paymentMode(paymentRequest.getPaymentMode().name())
                        .paymentStatus("SUCCESS")
                        .build();

        transactionRepository.save(transactionDetails);

        log.info("Transaction completed with Id: {} ",transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting payment details for the oderId {} ", orderId);
        TransactionDetails transactionDetails =
                transactionRepository
                        .findByOrderId(orderId)
                        .orElseThrow(()-> new RuntimeException("Not transaction found"));

        return PaymentResponse
                        .builder()
                        .paymentId(transactionDetails.getId())
                        .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                        .status(transactionDetails.getPaymentStatus())
                        .amount(transactionDetails.getAmount())
                        .orderId(transactionDetails.getOrderId())
                        .paymentDate(transactionDetails.getPaymentDate())
                        .build();
    }
}
