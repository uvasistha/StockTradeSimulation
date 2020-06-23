package com.stockdataservice.repository;

import com.stockdataservice.domain.StockSymbol;
import org.springframework.data.repository.CrudRepository;

public interface StockSymbolRepository extends CrudRepository<StockSymbol, String> {
}
