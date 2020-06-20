package com.stockdataservice.repository;

import com.stockdataservice.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, String> {
    //Table,column name all should be domain values

    @Modifying
    @Query("update User u set u.stock_list = :stock_list, u.balance = :balance, u.profit = :profit, u.no_of_stock = :no_of_stock where u.id = :id")
    void updateUser(String id, String stock_list, String balance, String profit, String no_of_stock);

    @Modifying
    @Query("update User u set u.email= :email, u.mobile= :mobile, u.name= :name, u.premium_customer= :premium_customer, u.password= :password where u.id = :id")
    void editUser(String id, String email, String mobile, String name, String premium_customer, String password);

}
