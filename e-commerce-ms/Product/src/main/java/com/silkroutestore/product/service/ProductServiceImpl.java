package com.silkroutestore.product.service;

import com.silkroutestore.product.entity.Product;
import com.silkroutestore.product.model.ProductRequest;
import com.silkroutestore.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("create-product request in service");
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        productRepository.save(product);

        log.info("product created successfully with id: "+ product.getId());
        return product.getId();
    }
}
