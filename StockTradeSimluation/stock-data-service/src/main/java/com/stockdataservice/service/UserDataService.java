package com.stockdataservice.service;

import com.stockdataservice.domain.User;

public interface UserDataService {

    User getUser(String id);

    Iterable<User> getAllUser();

    void saveUser(User user);

    void deleteUser(String id);

    Long getUserCount();

    Boolean userExists(String id);

    void updateUser(String id, String stock_list, String balance, String profit, String no_of_stock);

    void editUser(String id, String email, String mobile, String name, String premium_customer, String password);

}
