package com.silkroutestore.order.config;


import com.silkroutestore.order.external.decoder.CustomExceptionDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomExceptionDecoder();
    }
}
