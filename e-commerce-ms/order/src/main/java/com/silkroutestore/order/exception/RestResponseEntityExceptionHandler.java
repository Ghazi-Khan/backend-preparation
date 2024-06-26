package com.silkroutestore.order.exception;

import com.silkroutestore.order.external.reponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException) {
        return new ResponseEntity<>(
                ErrorResponse
                        .builder()
                        .errorCode(customException.getMessage())
                        .errorMessage(customException.getErrorCode())
                        .build(),
                HttpStatus.valueOf(customException.getStatus())
        );
    }
}
