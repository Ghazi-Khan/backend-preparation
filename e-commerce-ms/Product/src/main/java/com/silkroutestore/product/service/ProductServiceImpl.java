package com.silkroutestore.product.service;

import com.silkroutestore.product.entity.Product;
import com.silkroutestore.product.model.ProductRequest;
import com.silkroutestore.product.model.ProductResponse;
import com.silkroutestore.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(()-> new RuntimeException(
                                "product not found with id: " + productId
                        ));

        ProductResponse productResponse =
                new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }
}
