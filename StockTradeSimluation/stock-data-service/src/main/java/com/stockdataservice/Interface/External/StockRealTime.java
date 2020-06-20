package com.stockdataservice.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockdataservice.Interface.External.model.StockExternal;
import com.stockdataservice.Interface.External.model.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class StockRealTime {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private String key = null;

    @PostConstruct
    private void info(){
        log.info("External Interface Begins");
    }

    public StockExternal getStockData(String stockName){
        StockExternal stock =null;
        final String uri = URI.url.replace("id",stockName);
        try {
            stock = objectMapper.readValue(restTemplate.getForObject(uri, String.class), StockExternal.class);
        }catch (Exception e){
            log.info("External API Failed");
            e.printStackTrace();
        }
        return stock;
    }
}
