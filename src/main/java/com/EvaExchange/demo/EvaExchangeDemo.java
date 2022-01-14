package com.EvaExchange.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EvaExchangeDemo {

	public static void main(String[] args) {
		SpringApplication.run(EvaExchangeDemo.class, args);
	}

}
