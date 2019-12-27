package com.springboot.savingsAccountVip.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.savingsAccountVip.document.SavingsAccountVip;

import reactor.core.publisher.Mono;

public interface SavingsAccountVipRepo extends ReactiveMongoRepository<SavingsAccountVip, String> {
	
	  public Mono<SavingsAccountVip> findByNumberAccount(String numberAccount);
	  

	  


}
