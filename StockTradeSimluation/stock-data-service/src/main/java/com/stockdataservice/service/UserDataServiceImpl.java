package com.stockdataservice.service;

import com.stockdataservice.domain.User;
import com.stockdataservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    UserRepository userRepository;

    public User getUser(String id){
        return userRepository.findById(id).get();
    }

    public Iterable<User> getAllUser(){
        return  userRepository.findAll();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(String id){
        userRepository.delete(getUser(id));
    }

    public Long getUserCount(){
        return userRepository.count();
    }

    public Boolean userExists(String id){
        return  userRepository.existsById(id);
    }

    public void updateUser(String id, String stock_list, String balance, String profit, String no_of_stock){
        userRepository.updateUser(id,stock_list,balance,profit,no_of_stock);
    }

    public void editUser(String id, String email, String mobile, String name, String premium_customer, String password){
       userRepository.editUser(id,email,mobile,name,premium_customer,password);
    }
}
