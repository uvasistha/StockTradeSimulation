package com.stockrealtime.stockrealtime.Interface.External;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockrealtime.stockrealtime.Model.SearchEndPoint;
import com.stockrealtime.stockrealtime.Model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;


@Slf4j
@Component
public class StockFetcher {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;
    private String key = null;
    private int index = 0;
    private int size = 1;
    List<ApiKey.Key> keys;


    @PostConstruct
    public void readKey(){
        List<ApiKey.Key> data = null;
        try {
            InputStream is = ApiKey.class.getResourceAsStream("/apikey");
            ApiKey apiKey = objectMapper.readValue(is,ApiKey.class);
            data = apiKey.getKeygen();
        } catch (Exception e){
            log.info("Couldn't Read Keys");
            e.printStackTrace();
        }
        log.info("keys" + data);
        this.keys=data;
        if(data!=null) {
            this.key = data.get(index).key;
            this.size = data.size();
        }
    }

    public Stock getStockData(String stockName){
        int start = 0;
        Stock stock = null;
        while(start < size) {
            final String uri = URI.quote.replace("StockName", stockName).replace("StockKey", key);
            log.info(uri);
            try {
                stock = objectMapper.readValue(restTemplate.getForObject(uri, String.class), Stock.class);
                index = (index + 1) % size;
                key = keys.get(index).key;
                start = start + 1;
                break;
            } catch (Exception e) {
                log.info("External API Failed Key Switch");
                index = (index + 1) % size;
                key = keys.get(index).key;
                start = start + 1;
            }
        }
        return stock;
    }

    public SearchEndPoint getStockSymbol(String stockName){
        int start = 0;
        SearchEndPoint stock = null;
        while(start < size) {
            final String uri = URI.search.replace("StockName", stockName).replace("StockKey", key);
            log.info(uri);
            try {
                stock = objectMapper.readValue(restTemplate.getForObject(uri, String.class), SearchEndPoint.class);
                index = (index + 1) % size;
                key = keys.get(index).key;
                start = start + 1;
                break;
            } catch (Exception e) {
                log.info("External API Failed Key Switch");
                index = (index + 1) % size;
                key = keys.get(index).key;
                start = start + 1;
            }
        }
        return stock;
    }

}

