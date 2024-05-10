package com.silkroutestore.product.service;

import com.silkroutestore.product.entity.Product;
import com.silkroutestore.product.exception.ProductServiceCustomException;
import com.silkroutestore.product.model.ProductRequest;
import com.silkroutestore.product.model.ProductResponse;
import com.silkroutestore.product.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
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

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product =
                productRepository
                        .findById(productId)
                        .orElseThrow(()-> new ProductServiceCustomException(
                                "product not found with id: " + productId,
                                "PRODUCT_NOT_FOUND"
                        ));

        ProductResponse productResponse =
                new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        Product product
                = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "product with id " + productId + " not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity has been updated successfully");
    }
}
