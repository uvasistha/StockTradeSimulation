package com.stockrealtime.stockrealtime.Interface.REST;

import com.stockrealtime.stockrealtime.Interface.External.StockFetcher;
import com.stockrealtime.stockrealtime.Model.SearchEndPoint;
import com.stockrealtime.stockrealtime.Model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Handler {

    @Autowired
    StockFetcher stockFetcher;

    public Stock getStockInfo(String stockName){
       return stockFetcher.getStockData(stockName);
    }

    public SearchEndPoint getStockSymbol(String stockName){
        return stockFetcher.getStockSymbol(stockName);
    }
}
