package com.ticketnotificationservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {

        return new ObjectMapper();
    }
}