package com.stockuserservice.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockuserservice.Interface.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
public class StockManager {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private String key = null;

    @PostConstruct
    private void info(){
        log.info("StockManager Interface Begins");
    }

    //stock service calculate profit for user
    public String getProfit(String userID, User userInvoice){
        return  null;
    }

    //stock service calculate profit for user
    public String getBalance(String userID, User userInvoice){
        return  null;
    }
}
