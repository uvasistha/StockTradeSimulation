package com.stockdataservice.repository;

import com.stockdataservice.domain.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface StockRepository extends CrudRepository<Stock, String> {
    //Table,column name all should be domain values

    @Modifying
    @Query("update Stock u set u.open_val= :open_val, u.high= :high, u.low= :low, u.price= :price, " +
            "u.volume= :volume, u.previous_close= :previous_close, u.latest_trading_day= :latest_trading_day, u.change= :change, u.change_percent= :change_percent where u.symbol = :symbol")
    void updateStock(String symbol, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent);

}
