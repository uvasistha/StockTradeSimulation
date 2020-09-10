package com.stockdataservice.service;

import com.stockdataservice.domain.Stock;
import com.stockdataservice.domain.StockSymbol;
import com.stockdataservice.domain.StockUser;
import com.stockdataservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataDataServiceImpl implements DataDataService {

    @Autowired
    StockDataService stockDataService;
    @Autowired
    UserDataService userDataService;
    @Autowired
    StockSymbolDataService stockSymbolDataService;
    @Autowired
    StockUserDataService stockUserDataService;

    @Override
    public Stock getStock(String id) {
       return stockDataService.getStock(id);
    }

    @Override
    public void saveStock(Stock stock) {
        stockDataService.saveStock(stock);
    }

    @Override
    public Iterable<Stock> getAllStock() {
        return stockDataService.getAllStock();
    }

    @Override
    public Boolean stockExists(String id) {
        return stockDataService.stockExists(id);
    }

    @Override
    public Long getStockCount() {
        return stockDataService.getStockCount();
    }

    @Override
    public void updateStock(String id, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent) {
        stockDataService.updateStock(id, open_val, high, low, price, volume, latest_trading_day, previous_close, change, change_percent);
    }

    @Override
    public User getUser(String id) {
        return userDataService.getUser(id);
    }

    @Override
    public Iterable<User> getAllUser() {
        return userDataService.getAllUser();
    }

    @Override
    public void saveUser(User user) {
        userDataService.saveUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userDataService.deleteUser(id);
    }

    @Override
    public Long getUserCount() {
        return userDataService.getUserCount();
    }

    @Override
    public Boolean userExists(String id) {
        return userDataService.userExists(id);
    }

    @Override
    public void updateUser(String id, String stock_list, String balance, String profit, String no_of_stock) {
        userDataService.updateUser(id, stock_list, balance, profit, no_of_stock);
    }

    @Override
    public void editUser(String id, String email, String mobile, String name, String premium_customer, String password) {
        userDataService.editUser(id, email, mobile, name, premium_customer, password);
    }

    @Override
    public StockSymbol getSymbol(String id) {
        return stockSymbolDataService.getSymbol(id);
    }

    @Override
    public Iterable<StockSymbol> getSymbolList() {
        return stockSymbolDataService.getSymbolList();
    }

    @Override
    public StockUser getStockForUser(String id) {
        return stockUserDataService.getStockForUser(id);
    }

    @Override
    public void saveStockForUser(StockUser stockUser) {
        stockUserDataService.saveStockForUser(stockUser);
    }

    @Override
    public Iterable<StockUser> getAllStockForUser(String user_id) {
        return stockUserDataService.getAllStockForUser(user_id);
    }

    @Override
    public Boolean stockExistsForUser(String id) {
        return stockUserDataService.stockExistsForUser(id);
    }

    @Override
    public void makeTrade(String id, String stock_volume) {
         stockUserDataService.makeTrade(id, stock_volume);
    }

    @Override
    public void updateUserStockPrices(String id, String current_value, String price_of_stock, String change_percent) {
        stockUserDataService.updateUserStockPrices(id, current_value, price_of_stock, change_percent);
    }

    @Override
    public void delete(String id) {
        stockUserDataService.delete(id);
    }
}
