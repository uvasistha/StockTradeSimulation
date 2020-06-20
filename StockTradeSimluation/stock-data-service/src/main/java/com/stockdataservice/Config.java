package com.stockdataservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }
}
