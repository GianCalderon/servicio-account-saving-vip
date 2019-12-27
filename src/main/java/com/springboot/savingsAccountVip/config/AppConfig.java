package com.springboot.savingsAccountVip.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	
//	@Value("${config.base.endpoint}")
//	private String url;
	
	@Bean
	public WebClient registrarWebClient() {
		
		
		return WebClient.create("http://localhost:8012/api/personalVip");

	}

}
