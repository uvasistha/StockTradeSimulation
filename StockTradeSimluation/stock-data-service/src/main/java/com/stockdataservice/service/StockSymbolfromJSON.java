package com.stockdataservice.service;

import com.stockdataservice.domain.StockSymbol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
public class StockSymbolfromJSON {

    @PostConstruct
    public void readKey(){
        //Allow to load all symbols name from csv/db to map
//        List<StockSymbol> data = null;
//        try {
//            InputStream is = (List<StockSymbol>).class.getResourceAsStream("/apikey");
//            ApiKey apiKey = objectMapper.readValue(is,ApiKey.class);
//            data = apiKey.getKeygen();
//        } catch (Exception e){
//            log.info("Couldn't Read Keys");
//            e.printStackTrace();
//        }
//        log.info("keys" + data);
//        this.keys=data;
//        if(data!=null) {
//            this.key = data.get(index).key;
//            this.size = data.size();
//        }
    }
}
