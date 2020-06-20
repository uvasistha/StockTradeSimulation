package com.stockdataservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Config {

    @Bean
    ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }

    @Bean
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
