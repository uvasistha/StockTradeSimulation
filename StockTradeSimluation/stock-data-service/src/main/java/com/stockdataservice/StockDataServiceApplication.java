package com.stockdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(Config.class)
@SpringBootApplication
public class StockDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDataServiceApplication.class, args);
	}

}
