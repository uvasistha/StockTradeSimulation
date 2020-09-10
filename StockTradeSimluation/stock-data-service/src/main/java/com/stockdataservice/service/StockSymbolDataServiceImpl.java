package com.stockdataservice.service;

import com.stockdataservice.domain.StockSymbol;
import com.stockdataservice.repository.StockSymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockSymbolDataServiceImpl implements StockSymbolDataService {

    @Autowired
    StockSymbolRepository stockSymbolRepository;

    public StockSymbol getSymbol(String id){
        if(stockSymbolRepository.existsById(id))
        return stockSymbolRepository.findById(id).get();
        return null;
    }

    public Iterable<StockSymbol> getSymbolList(){
       return stockSymbolRepository.findAll();
    }
}
