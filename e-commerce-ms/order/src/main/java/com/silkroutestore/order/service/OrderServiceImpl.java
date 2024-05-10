package com.silkroutestore.order.service;

import com.silkroutestore.order.entity.Order;
import com.silkroutestore.order.model.OrderRequest;
import com.silkroutestore.order.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
//        Code flow
//        Order entity -> Save the data with Status Order created
//        Product Service -> Block the products (reduce the quantity)
//        Payment Service -> Payments -> Success -> Completed else Cancelled

        log.info("placing the oder request {} ", orderRequest);

        Order order = Order
                .builder()
                .quantity(orderRequest.getQuantity())
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .orderDate(Instant.now())
                .productId(orderRequest.getProductId())
                .build();

        order = orderRepository.save(order);

        log.info("order placed successfully with order id: " + order.getId());
        return 0;
    }
}