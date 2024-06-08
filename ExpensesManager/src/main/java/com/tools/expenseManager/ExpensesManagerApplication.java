package com.tools.expenseManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExpensesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesManagerApplication.class, args);
	}

}
