package com.stockdataservice.service;

import com.stockdataservice.domain.Stock;
import com.stockdataservice.domain.User;

public interface UserDataService {

    public User getUser(String id);

    public Iterable<User> getAllUser();

    public void saveUser(User user);

    public void deleteUser(String id);

    public Long getUserCount();

    public Boolean userExists(String id);

    public void updateUser(String id, String stock_list, String balance, String profit, String no_of_stock);

    public void editUser(String id, String email, String mobile, String name, String premium_customer, String password);

}
