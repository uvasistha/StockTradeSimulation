package com.stockdataservice.service;

import com.stockdataservice.domain.Stock;
import com.stockdataservice.domain.User;

public interface StockDataService {

    public Stock getStock(String id);

    public void saveStock(Stock stock);

    public Iterable<Stock> getAllStock();

    public Boolean stockExists(String id);

    public Long getStockCount();

    public void updateStock(String id, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent);
}
