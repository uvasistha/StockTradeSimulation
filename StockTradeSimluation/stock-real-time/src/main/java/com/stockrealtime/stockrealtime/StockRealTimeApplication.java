package com.stockrealtime.stockrealtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(Config.class)
@SpringBootApplication
public class StockRealTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockRealTimeApplication.class, args);
	}

}
