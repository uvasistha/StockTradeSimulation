package com.stockdataservice.Interface.Rest;

import com.stockdataservice.Interface.External.StockRealTime;
import com.stockdataservice.Interface.External.model.StockExternal;
import com.stockdataservice.Interface.Rest.Model.Stock;
import com.stockdataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalHandler {

    @Autowired
    StockRealTime stockRealTime;
    @Autowired
    DataService dataService;

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
}
