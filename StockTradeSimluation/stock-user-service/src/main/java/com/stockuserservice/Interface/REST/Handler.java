package com.stockuserservice.Interface.REST;

import com.stockuserservice.Interface.External.ExternalHandler;
import com.stockuserservice.Interface.Model.StockUser;
import com.stockuserservice.Interface.Model.Trade;
import com.stockuserservice.Interface.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    List<StockUser> getUserPortfolioUser(String userid){
        return externalHandler.getUserAllStock(userid);
    }

    //User has stock name (stockList) and number of stock (stock)
    void updateUser(String id, boolean purchase, Trade trade){
        User dbUser = externalHandler.getUser(id);

//        //buy case
//        if(purchase){
//            // if  balance > trade.amount =>return
             //  if stock exist && trade.stockVolume + stock.Volume <100
            //  => trade.stockVolume += trade.stockVolume and  balance -= trade.amount
            //   if stock don't exist && stock count < 50
            //   => allow to buy and add it,increase total stocks count
//
//            dbUser.setStock_list(dbUser.getStock_list().concat(userInvoice.getStock_list()));
//            dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock())+Integer.parseInt(userInvoice.getNo_of_stock())));
//        }
        //sell case
//        else {
        //see if stock  exist &&   trade.stockVolume<= stockVolume
        //=>allow and trade.stockVolume += trade.stockVolume and  balance -= trade.amount
        // if stockVolume == 0 => remove it,decrease total stocks count
//            dbUser.setStock_list(dbUser.getStock_list().replace(userInvoice.getStock_list(),""));
//            dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock())-Integer.parseInt(userInvoice.getNo_of_stock())));
//        }

        //find price of portfolio
//        String profit = externalHandler.getProfit(userInvoice.getId(),userInvoice);
        //find balance now
//        String balance = externalHandler.getBalance(userInvoice.getId(),userInvoice);
//
//        dbUser.setProfit(profit);
//        dbUser.setBalance(balance);
//        externalHandler.updateUser(dbUser);
    }
}
