package com.stockdataservice.Interface.Rest;

import com.stockdataservice.Interface.Rest.Model.Stock;
import com.stockdataservice.Interface.External.model.StockExternal;
import com.stockdataservice.Interface.Rest.Model.User;
import com.stockdataservice.service.DataDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Handler {
    
    @Autowired
    DataDataService dataService;
    @Autowired
    ExternalHandler externalHandler;
    
    public Stock getStock(String id){
        com.stockdataservice.domain.Stock stockdata = dataService.getStock(id);
        //try from externalAPI
        if(stockdata==null)
            return  externalHandler.fetchStock(id);

        Stock stock = Stock.builder().open(stockdata.getOpen_val()).previous_close(stockdata.getPrevious_close())
                .low(stockdata.getLow()).high(stockdata.getHigh())
                .price(stockdata.getPrice()).volume(stockdata.getVolume()).build();
        return stock;
    }


    public User getUser(String id){
        com.stockdataservice.domain.User userdata = dataService.getUser(id);
        if(userdata==null)
            return null;
        User user = User.builder().no_of_stock(userdata.getNo_of_stock()).name(userdata.getName())
                .balance(userdata.getBalance()).premium_customer(userdata.getPremium_customer())
                .profit(userdata.getProfit()).stock_list(userdata.getStock_list()).build();
        return user;
    }


    public String saveStock(StockExternal stock){
      return externalHandler.saveStock(stock);
    }


    public String saveUser(User user){
        String response = "success";
        com.stockdataservice.domain.User userData = com.stockdataservice.domain.User.builder()
                .balance(user.getBalance()).email(user.getEmail()).id(user.getId()).mobile(user.getMobile()).name(user.getName())
                .no_of_stock(user.getNo_of_stock()).password(user.getPassword()).premium_customer(user.getPremium_customer())
                .profit(user.getProfit()).stock_list(user.getStock_list()).build();
        try{
            dataService.saveUser(userData);
        }catch (Exception e){
            response ="couldn't save";
        }
        return response;
    }

    public String updateStock(StockExternal stock){
      return  externalHandler.updateStock(stock);
    }

    public String updateUser(User user){
        String response = "success";
        try{
            dataService.updateUser(user.getId(),user.getStock_list(),user.getBalance(),user.getProfit(),user.getNo_of_stock());
        }catch (Exception e){
            response ="couldn't update";
        }
        return response;
    }

    public String editUser(User user){
        String response = "success";
        try{
            dataService.editUser(user.getId(),user.getEmail(),user.getMobile(),user.getName(),user.getPremium_customer(),user.getPassword());
        }catch (Exception e){
            response ="couldn't update";
        }
        return response;
    }

}
