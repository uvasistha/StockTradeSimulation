package com.stockdataservice.repository;

import com.stockdataservice.domain.StockUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StockUserRepository extends CrudRepository<StockUser, String> {

    @Modifying
    @Query("update StockUser u set u.stock_volume = :stock_volume where u.id = :id")
    void makeTrade(String id, String stock_volume);

    @Modifying
    @Query("select u from StockUser u where u.user_id = :user_id")
    Iterable<StockUser> findByuserId(String user_id);

}
