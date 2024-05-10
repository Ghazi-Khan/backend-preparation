package com.silkroutestore.product.controller;


import com.silkroutestore.product.model.ProductRequest;
import com.silkroutestore.product.model.ProductResponse;
import com.silkroutestore.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        log.info("create-product request in controller -" + productRequest);
        Long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        ProductResponse productResponse =
                productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable(name = "id") long productId,
            @RequestParam long quantity
    ) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
