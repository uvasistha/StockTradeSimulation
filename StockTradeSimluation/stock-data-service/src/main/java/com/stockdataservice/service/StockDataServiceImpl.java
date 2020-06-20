package com.stockdataservice.service;
import com.stockdataservice.domain.Stock;
import com.stockdataservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDataServiceImpl implements StockDataService {

    @Autowired
    StockRepository stockRepository;

    public Stock getStock(String id){
        if(stockExists(id))
        return stockRepository.findById(id).get();
        return null;
    }

    public void saveStock(Stock stock){
         stockRepository.save(stock);
    }

    public Iterable<Stock> getAllStock(){
        return stockRepository.findAll();
    }

    public Boolean stockExists(String id){
        return  stockRepository.existsById(id);
    }

    public Long getStockCount(){
        return stockRepository.count();
    }

    public void updateStock(String id, String open_val, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent){
        if(stockExists(id))
        stockRepository.updateStock(id,open_val,high,low,price,volume,latest_trading_day,previous_close,change,change_percent);
    }
}
