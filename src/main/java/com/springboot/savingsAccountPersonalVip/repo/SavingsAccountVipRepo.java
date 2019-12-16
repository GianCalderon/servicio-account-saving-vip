package com.springboot.savingsAccountPersonalVip.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.savingsAccountPersonalVip.document.SavingsAccountVip;

import reactor.core.publisher.Mono;

public interface SavingsAccountVipRepo extends ReactiveMongoRepository<SavingsAccountVip, String> {
	
	  public Mono<SavingsAccountVip> findByNumberAccount(String numberAccount);
	  

	  


}
