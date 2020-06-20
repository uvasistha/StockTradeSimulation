package com.stockdataservice.service;

import com.stockdataservice.domain.Stock;

public interface StockDataService {

    Stock getStock(String id);

    void saveStock(Stock stock);

    Iterable<Stock> getAllStock();

    Boolean stockExists(String id);

    Long getStockCount();

    void updateStock(String id, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent);
}
