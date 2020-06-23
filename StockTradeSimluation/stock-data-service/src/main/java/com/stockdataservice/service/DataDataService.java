package com.stockdataservice.service;


import org.springframework.stereotype.Service;

@Service
public interface DataDataService extends  StockDataService,UserDataService, StockSymbolDataService {
}
