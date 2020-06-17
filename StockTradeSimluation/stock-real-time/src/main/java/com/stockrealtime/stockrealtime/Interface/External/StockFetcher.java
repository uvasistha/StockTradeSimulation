package com.stockrealtime.stockrealtime.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockrealtime.stockrealtime.Model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.InputStream;


@Slf4j
@Component
public class StockFetcher {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
    private String key = null;


    @PostConstruct
    public void readKey(){
        String data = null;
        try {
            InputStream is = ApiKey.class.getResourceAsStream("/apikey");
            ApiKey apiKey = objectMapper.readValue(is,ApiKey.class);
            data = apiKey.getKey();
        } catch (Exception e){
            log.info("Couldn't Read Key");
            e.printStackTrace();
        }
        log.info("key" + data);
        this.key=data;
    }

    public Stock getStockData(String stockName){
        Stock stock =null;
        final String uri = URI.key.replace("StockName",stockName).replace("StockKey",key);
        try {
            stock = objectMapper.readValue(restTemplate.getForObject(uri, String.class), Stock.class);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
        return stock;
    }

}

