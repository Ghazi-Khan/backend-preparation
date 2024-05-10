package com.silkroutestore.product.exception;

import com.silkroutestore.product.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException productServiceException) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .errorCode(productServiceException.getErrorCode())
                        .errorMessage(productServiceException.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
