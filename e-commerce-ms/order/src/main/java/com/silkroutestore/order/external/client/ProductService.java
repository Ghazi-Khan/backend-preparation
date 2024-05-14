package com.silkroutestore.order.external.client;

import com.silkroutestore.order.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable(name = "id") long productId,
            @RequestParam long quantity
    );

    default ResponseEntity<Void> fallback(
        @PathVariable(name = "id") long productId,
        @RequestParam long quantity,
        Exception e
    ) {
        throw new CustomException("Product service is not available", "UNAVAILABLE", 500);
    }
}
