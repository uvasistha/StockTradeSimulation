package com.stockuserservice;

import com.stockuserservice.Config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(Config.class)
@SpringBootApplication
public class StockUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockUserServiceApplication.class, args);
	}

}
