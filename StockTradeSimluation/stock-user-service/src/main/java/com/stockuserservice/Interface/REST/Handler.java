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
        //Defaults
        user.setNo_of_stock("0");
        user.setBalance("1000");
        user.setStock_list("Empty");
        user.setProfit("0");
        externalHandler.saveUser(user);
    }

    void editUser(User user){
        //contains id password and all for edit
        externalHandler.editUser(user);
    }

    List<StockUser> getUserPortfolioUser(String userid){
        return externalHandler.getUserAllStock(userid);
    }

    String updateUser(String id, boolean purchase, Trade trade) {
        //TODO calculate stock price add date
        User dbUser = externalHandler.getUser(id);
       if(dbUser==null)
           return  "User not found";
        String uniqueID = id + "_" + trade.getStockSymbol();
        StockUser stockUser =null;
        stockUser = externalHandler.getUserStock(uniqueID);

//       //buy case
        if (purchase) {
            if (Double.parseDouble(dbUser.getBalance()) < Double.parseDouble(trade.getAmount()))
                return "Insufficient Balance";
            else if (stockUser != null && Integer.parseInt(trade.getStockVolume()) + Integer.parseInt(stockUser.getStock_volume()) < 100) {
                String totalUnits = String.valueOf(Integer.parseInt(trade.getStockVolume()) + Integer.parseInt(stockUser.getStock_volume()));
                String updatedBalance = String.valueOf(Double.parseDouble(dbUser.getBalance()) - Double.parseDouble(trade.getAmount()));
                dbUser.setBalance(updatedBalance);
                externalHandler.tradeUserStock(uniqueID, totalUnits);
                externalHandler.updateUser(dbUser);
                return "Purchase :Stocks for " + trade.getStockName() + " updated";
            } else if (stockUser == null && Integer.parseInt(dbUser.getNo_of_stock()) < 50) {
                StockUser stockUserNew = StockUser.builder()
                        .id(uniqueID)
                        .stock_name(trade.getStockName())
                        .stock_symbol(trade.getStockSymbol())
                        .stock_volume(trade.getStockVolume())
                        .user_id(id).build();
                String updatedBalance = String.valueOf(Double.parseDouble(dbUser.getBalance()) - Double.parseDouble(trade.getAmount()));
                dbUser.setBalance(updatedBalance);
                dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock()) + 1));
                externalHandler.saveUserStock(stockUserNew);
                externalHandler.updateUser(dbUser);
                return "Purchase : Stocks for " + trade.getStockName() + " purchased";
            }
            else return "Can't have more than 100 volumes or more than 50 stocks";
        }
        //sell case
        else {
            if (stockUser == null) return "Invalid Action : Can't sell not owned stock";
            else if (Integer.parseInt(trade.getStockVolume()) > Integer.parseInt(stockUser.getStock_volume()))
                return "Invalid Action : Can't sell more than bought";
            else {
                String totalUnits = String.valueOf(Integer.parseInt(stockUser.getStock_volume()) - Integer.parseInt(trade.getStockVolume()));
                String updatedBalance = String.valueOf(Double.parseDouble(dbUser.getBalance()) + Double.parseDouble(trade.getAmount()));
                dbUser.setBalance(updatedBalance);
                if (Integer.parseInt(stockUser.getStock_volume()) - Integer.parseInt(trade.getStockVolume()) == 0)
                    dbUser.setNo_of_stock(String.valueOf(Integer.parseInt(dbUser.getNo_of_stock()) - 1));
                externalHandler.tradeUserStock(uniqueID, totalUnits);
                externalHandler.updateUser(dbUser);
                return "Sale : Stocks for " + trade.getStockName() + " updated";
            }
        }
    }
}
