package com.stockdataservice.service;

import com.stockdataservice.domain.StockUser;
import com.stockdataservice.repository.StockUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockUserDataServiceImpl implements StockUserDataService{

    @Autowired
    StockUserRepository stockUserRepository;

    @Override
    public StockUser getStockForUser(String id) {
        if(stockExistsForUser(id)){
            return stockUserRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void saveStockForUser(StockUser stockUser) {
        stockUserRepository.save(stockUser);
    }

    @Override
    public Iterable<StockUser> getAllStockForUser(String user_id) {
        return stockUserRepository.findByuserId(user_id);
    }

    @Override
    public Boolean stockExistsForUser(String id) {
        return stockUserRepository.existsById(id);
    }

    @Override
    @Transactional
    public void makeTrade(String id, String stock_volume) {
        if(stockExistsForUser(id))
        stockUserRepository.makeTrade(id, stock_volume);
    }
}
