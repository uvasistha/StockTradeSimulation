package com.stockuserservice.Interface.External;

import com.stockuserservice.Interface.Model.StockUser;
import com.stockuserservice.Interface.Model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExternalHandler {

    @Autowired
    DBManager dbManager;
    @Autowired
    StockManager stockManager;

    public User getUser(String userID){
        return dbManager.getUser(userID);
    }

    public void saveUser(User user){
        dbManager.saveUser(user);
    }

    public void updateUser(User user){
        dbManager.updateUser(user);
    }

    public void editUser(User user){
        dbManager.editUser(user);
    }

    public String getProfit(String userID, User userInvoice){
        return  stockManager.getProfit(userID, userInvoice);
    }

    public String getBalance(String userID, User userInvoice){
        return  stockManager.getBalance(userID, userInvoice);
    }

    public StockUser getUserStock(String id){return dbManager.getUserStock(id);}

    public List<StockUser> getUserAllStock(String userid){return dbManager.getUserAllStock(userid);}

    public void saveUserStock(StockUser stockUser){dbManager.saveUserStock(stockUser);}

    public void tradeUserStock(String id, String volume){dbManager.tradeUserStock(id, volume);}

}
