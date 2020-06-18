package com.stockdataservice.repository;

import com.stockdataservice.domain.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface StockRepository extends CrudRepository<Stock, String> {

    @Modifying
    @Query("update User u set u.stock_list=:stock_list, u.balance=:balance, u.profit=:profit, u.no_of_stock=:no_of_stock where u.id = :id")
    public void updateStock(String id, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent);
}
