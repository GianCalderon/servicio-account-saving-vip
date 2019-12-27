package com.springboot.savingsAccountVip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountSavingsPersonalVipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountSavingsPersonalVipApplication.class, args);
	}

}
