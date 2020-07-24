package com.stockuserservice.Interface.REST;

import com.stockuserservice.Interface.External.ExternalHandler;
import com.stockuserservice.Interface.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Handler {

    @Autowired
    ExternalHandler externalHandler;

    String validateUser(User user){
       User dbUser = externalHandler.getUser(user.getId());
       String response = "NO Access";
       if(dbUser == null)
           return response;
       if(dbUser.getId().equals(user.getId()) && dbUser.getPassword().equals(user.getPassword())){
           response = "Allow Access";
       }
       return response;
    }

    User getUser(String userID){
        return externalHandler.getUser(userID);
    }

    void saveUser(User user){
        externalHandler.saveUser(user);
    }

    void editUser(User user){
        externalHandler.editUser(user);
    }

    //User has stock name (stockList) and number of stock (stock)
    void updateUser(User userInvoice ,boolean purchase){
        User dbUser = externalHandler.getUser(userInvoice.getId());
        if(purchase){
            dbUser.setStock_list(dbUser.getStock_list().concat(userInvoice.getStock_list()));
            dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock())+Integer.parseInt(userInvoice.getNo_of_stock())));
        }
        else {
            dbUser.setStock_list(dbUser.getStock_list().replace(userInvoice.getStock_list(),""));
            dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock())-Integer.parseInt(userInvoice.getNo_of_stock())));
        }
        String profit = externalHandler.getProfit(userInvoice.getId(),userInvoice);
        String balance = externalHandler.getBalance(userInvoice.getId(),userInvoice);

        dbUser.setProfit(profit);
        dbUser.setBalance(balance);
        externalHandler.updateUser(dbUser);
    }
}
