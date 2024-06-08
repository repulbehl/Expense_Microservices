package com.users.usersMicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsermicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermicroservicesApplication.class, args);
	}

}
