package com.silkroutestore.order.service;

import com.silkroutestore.order.entity.Order;
import com.silkroutestore.order.exception.CustomException;
import com.silkroutestore.order.external.client.PaymentService;
import com.silkroutestore.order.external.client.ProductService;
import com.silkroutestore.order.external.reponse.PaymentResponse;
import com.silkroutestore.order.external.reponse.ProductResponse;
import com.silkroutestore.order.external.request.PaymentRequest;
import com.silkroutestore.order.model.OrderRequest;
import com.silkroutestore.order.model.OrderResponse;
import com.silkroutestore.order.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
//        Code flow
//        Order entity -> Save the data with Status Order created
//        Product Service -> Block the products (reduce the quantity)
//        Payment Service -> Payments -> Success -> Completed else Cancelled

        log.info("placing the oder request {} ", orderRequest);

        productService.reduceQuantity(
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );

        log.info("creating order with status CREATED");

        Order order = Order
                .builder()
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .build();

        order = orderRepository.save(order);

        log.info("Calling payment service to complete the payment");
        PaymentRequest paymentRequest =
                PaymentRequest
                        .builder()
                        .orderId(order.getId())
                        .paymentMode(orderRequest.getPaymentMode())
                        .amount(orderRequest.getTotalAmount())
                        .build();

        String paymentStatus;

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully, changing order status to PLACED");
            paymentStatus = "PLACED";
        } catch (Exception e) {
            log.info("Error occurred in payment, changing status to FAILED");
            paymentStatus = "FAILED";
        }
        order.setOrderStatus(paymentStatus);
        orderRepository.save(order);

        log.info("order placed successfully with order id: {}", order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for the orderId {} ", orderId);
        Order order =
                orderRepository
                        .findById(orderId)
                        .orElseThrow(() -> new CustomException(
                                "Order not found with id: " + orderId,
                                "ORDER_NOT_FOUND",
                                HttpStatus.NOT_FOUND.value()
                        ));

        log.info("Invoking product -service to fetch product for id {} ", order.getProductId());

        ProductResponse productResponse =
                restTemplate.getForObject(
                        "http://PRODUCT-SERVICE/product/"+ order.getProductId(),
                        ProductResponse.class
                );

        log.info("Getting payment details from payment-service with orderId {} : ", order.getId());

        PaymentResponse paymentResponse =
                restTemplate.getForObject(
                        "http://PAYMENT-SERVICE/payment/order/" + order.getId(),
                        PaymentResponse.class
                );
        log.info("Payment details are - {} ", paymentResponse);

        OrderResponse.ProductDetails productDetails =
                OrderResponse
                        .ProductDetails
                        .builder()
                        .id(productResponse.getId())
                        .name(productResponse.getName())
                        .price(productResponse.getPrice())
                        .quantity(productResponse.getQuantity())
                        .build();

        OrderResponse.PaymentDetails paymentDetails =
                OrderResponse
                        .PaymentDetails
                        .builder()
                        .paymentId(paymentResponse.getPaymentId())
                        .paymentDate(paymentResponse.getPaymentDate())
                        .status(paymentResponse.getStatus())
                        .orderId(paymentResponse.getOrderId())
                        .amount(paymentResponse.getAmount())
                        .build();

        return
                OrderResponse
                        .builder()
                        .orderId(order.getId())
                        .orderDate(order.getOrderDate())
                        .amount(order.getAmount())
                        .orderStatus(order.getOrderStatus())
                        .productDetails(productDetails)
                        .paymentDetails(paymentDetails)
                        .build();
    }
}
