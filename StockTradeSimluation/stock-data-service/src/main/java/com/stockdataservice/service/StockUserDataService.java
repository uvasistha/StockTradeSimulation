package com.stockdataservice.service;

import com.stockdataservice.domain.StockUser;

public interface StockUserDataService {

    //includes id means => stockUser_id

    StockUser getStockForUser(String id);

    void saveStockForUser(StockUser stockUser);

    Iterable<StockUser> getAllStockForUser(String user_id);

    Boolean stockExistsForUser(String id);

    void makeTrade(String id, String stock_volume);

    void updateUserStockPrices(String id, String current_value, String price_of_stock, String change_percent);

    public void delete(String id);
}
