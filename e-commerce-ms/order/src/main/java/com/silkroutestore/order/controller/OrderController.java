package com.silkroutestore.order.controller;


import com.silkroutestore.order.model.OrderRequest;
import com.silkroutestore.order.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(
                orderId,
                HttpStatus.CREATED
        );
    }
}
