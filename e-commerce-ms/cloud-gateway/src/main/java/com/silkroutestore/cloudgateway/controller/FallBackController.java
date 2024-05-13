package com.silkroutestore.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBack () {
        return "Order service fallback";
    }

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack () {
        return "Product service fallback";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack () {
        return "Payment service fallback";
    }
}
