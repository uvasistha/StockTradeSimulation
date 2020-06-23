package com.stockdataservice.service;

import com.stockdataservice.domain.StockSymbol;

public interface StockSymbolDataService {

    public StockSymbol getSymbol(String id);

    public Iterable<StockSymbol> getSymbolList();
}
