package com.stockrealtime.stockrealtime;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Config {

    @Bean
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Bean
    ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }
}
