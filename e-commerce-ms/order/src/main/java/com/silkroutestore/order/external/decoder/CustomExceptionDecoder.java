package com.silkroutestore.order.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silkroutestore.order.exception.CustomException;
import com.silkroutestore.order.external.reponse.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomExceptionDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse =
                    objectMapper.readValue(
                            response.body().asInputStream(),
                            ErrorResponse.class
                    );
            return new CustomException(
                    errorResponse.getErrorMessage(),
                    errorResponse.getErrorCode(),
                    response.status()
            );
        } catch (IOException e) {
            throw new CustomException(
                    "Internal Server Error",
                    "INTERNAL_SERVER_ERROR",
                    500
            );
        }
    }
}
