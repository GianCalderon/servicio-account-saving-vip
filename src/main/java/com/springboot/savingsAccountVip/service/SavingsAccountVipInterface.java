package com.springboot.savingsAccountVip.service;

import com.springboot.savingsAccountVip.document.SavingsAccountVip;
import com.springboot.savingsAccountVip.dto.AccountDto;
import com.springboot.savingsAccountVip.dto.OperationDto;
import com.springboot.savingsAccountVip.dto.PersonalVipDto;
import com.springboot.savingsAccountVip.dto.SavingsAccountVipDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingsAccountVipInterface{

	public Flux<SavingsAccountVip> findAll();
	public Mono<SavingsAccountVip> findById(String id);
	public Mono<SavingsAccountVip> save(SavingsAccountVip SavingsAccountVip);
	public Mono<SavingsAccountVip> update(SavingsAccountVip SavingsAccountVip, String id);
	public Mono<Void> delete(SavingsAccountVip SavingsAccountVip);

	
	public Mono<SavingsAccountVip> findByNumAccount(String id);
	public Mono<SavingsAccountVip> saveOperation(OperationDto operationDto);	
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto);     
	public Mono<SavingsAccountVipDto> saveHeadlines (SavingsAccountVipDto SavingsAccountVipDto);
	
	
}
