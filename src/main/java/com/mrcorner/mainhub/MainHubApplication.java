package com.mrcorner.mainhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MainHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainHubApplication.class, args);
	}

}
