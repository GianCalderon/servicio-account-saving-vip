package com.springboot.savingsAccountPersonalVip.service;

import com.springboot.savingsAccountPersonalVip.document.SavingsAccountVip;
import com.springboot.savingsAccountPersonalVip.dto.SavingsAccountVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountVipInterface{

	public Flux<SavingsAccountVip> findAll();
	public Mono<SavingsAccountVip> findById(String id);
	public Mono<SavingsAccountVip> save(SavingsAccountVip savingsAccountVip);
	public Mono<SavingsAccountVip> update(SavingsAccountVip savingsAccountVip, String id);
	public Mono<Void> delete(SavingsAccountVip savingsAccountVip);
	
	
	public Mono<SavingsAccountVipDto> saveDto(SavingsAccountVipDto savingsAccountVipDto);
//	public  Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto);
//	
//	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount);
//	
//	public Mono<SavingsAccount> findByNumAccount(String id);
//	
//	public Mono<SavingsAccount> saveOperation(OperationDto operationDto);
	
}
