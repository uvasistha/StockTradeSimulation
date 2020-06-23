package com.stockdataservice.Interface.Rest;

import com.stockdataservice.Interface.External.StockRealTime;
import com.stockdataservice.Interface.External.model.StockExternal;
import com.stockdataservice.Interface.Rest.Model.Stock;
import com.stockdataservice.service.DataDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class ExternalHandler {

    @Autowired
    StockRealTime stockRealTime;
    @Autowired
    DataDataService dataService;

    public Stock fetchStock(String stockName){
        //fetch from external api
        StockExternal stockExternal = stockRealTime.getStockData(stockName);
        if(stockExternal==null)
            return null;

        //save to db
        saveStock(stockExternal);
        //fetch from db
        com.stockdataservice.domain.Stock stockdata = dataService.getStock(stockName);
        if(stockdata==null)
            return  null;
        Stock stock = Stock.builder().open(stockdata.getOpen_val()).previous_close(stockdata.getPrevious_close())
                .low(stockdata.getLow()).high(stockdata.getHigh())
                .price(stockdata.getPrice()).volume(stockdata.getVolume()).build();
        return stock;
    }

    public String saveStock(StockExternal stock){
        if(stock==null)
            return null;

        StockExternal.Global_Quote global_quote = stock.getGlobal_quote();
        String response = "success";
        com.stockdataservice.domain.Stock stockData = com.stockdataservice.domain.Stock.builder()
                .change(global_quote.getChange()).change_percent(global_quote.getChange_percent())
                .high(global_quote.getHigh()).latest_trading_day(global_quote.getLatest_trading_day())
                .low(global_quote.getLow()).open_val(global_quote.getOpen()).previous_close(global_quote.getPrevious_close())
                .price(global_quote.getPrice()).symbol(global_quote.getSymbol()).volume(global_quote.getVolume())
                .build();
        try {
            dataService.saveStock(stockData);
        }
        catch (Exception e){
            response = "couldn't save";
            e.printStackTrace();
        }
        return  response;
    }

    public String updateStock(StockExternal stock){
        if(stock==null)
            return null;

        StockExternal.Global_Quote global_quote = stock.getGlobal_quote();
        String response = "success";
        try {
            dataService.updateStock(global_quote.getSymbol(),global_quote.getOpen(),global_quote.getHigh(),global_quote.getLow(),global_quote.getPrice(),global_quote.getVolume()
                    ,global_quote.getLatest_trading_day(),global_quote.getPrevious_close(),global_quote.getChange(),global_quote.getChange_percent());
        }
        catch (Exception e){
            response = "couldn't update";
            e.printStackTrace();
        }
        return  response;
    }

    //schedule for every one hour to update
    @Scheduled(initialDelay = 1000*60*60, fixedDelay = 1000*60*60)
    public void fetchOldStock() {
        LocalTime time = LocalTime.now();
        //International Market Update
        if (time.getHour() > 0 && time.getHour() < 21) {
            Iterable<com.stockdataservice.domain.Stock> allStock = dataService.getAllStock();
            AtomicInteger atomicInteger = new AtomicInteger(0);
            allStock.forEach(stock -> {
                if (atomicInteger.get() == 5) {
                    atomicInteger.set(0);
                    try {
                        Thread.sleep(1000 * 60 * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                StockExternal stockExternal = stockRealTime.getStockData(stock.getSymbol());
                atomicInteger.set(atomicInteger.get() + 1);
                if (stockExternal != null) {
                    updateStock(stockExternal);
                }
            });
        }
    }

    @PostConstruct
    void info(){
        LocalTime localTime = LocalTime.now();
        log.info("External Handler Begins @ " + localTime.toString());
    }
}
