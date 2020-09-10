package com.stockdataservice.Job;

import com.stockdataservice.domain.Stock;
import com.stockdataservice.domain.StockUser;
import com.stockdataservice.domain.User;
import com.stockdataservice.service.DataDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
public class PirceSync {

    @Autowired
    DataDataService dataService;

    @PostConstruct
    void info(){
        LocalTime localTime = LocalTime.now();
        log.info("PriceSyncService Begins @ " + localTime.toString());
        updateUserStockPrice();
    }

    //start after 1 min schedule for every 15 minutes to update
    @Scheduled(initialDelay = 1000*60, fixedDelay = 1000*60*15)
    public void updateUserStockPrice(){
        log.info("PriceSync Job");
        List<User> userList = (List<User>) dataService.getAllUser();
        userList.forEach(user -> {
            List<StockUser> stockUserList = (List<StockUser>)dataService.getAllStockForUser(user.getId());
            stockUserList.forEach(stock -> {
                Stock stockRealTime = dataService.getStock(stock.getStock_symbol());
                if(stockRealTime!=null) {
                    String current_value = String.valueOf(Double.parseDouble(stock.getStock_volume()) * Double.parseDouble(stockRealTime.getPrice()));
                    dataService.updateUserStockPrices(stock.getId(), current_value, stockRealTime.getPrice(), stockRealTime.getChange_percent());
                }
            });
        });
    }
}
