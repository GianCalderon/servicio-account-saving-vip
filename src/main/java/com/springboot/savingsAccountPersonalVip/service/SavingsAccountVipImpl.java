package com.springboot.savingsAccountPersonalVip.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccountPersonalVip.client.PersonalClientVip;
import com.springboot.savingsAccountPersonalVip.document.SavingsAccountVip;
import com.springboot.savingsAccountPersonalVip.dto.SavingsAccountVipDto;
import com.springboot.savingsAccountPersonalVip.repo.SavingsAccountVipRepo;
import com.springboot.savingsAccountPersonalVip.util.UtilConvert;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingsAccountVipImpl implements SavingsAccountVipInterface {


	private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccountVipImpl.class);

	
	@Autowired
    SavingsAccountVipRepo repo;
	
	@Autowired
	UtilConvert convert;

	@Autowired
	PersonalClientVip webCLient;
//	
//	@Autowired
//	ManageOperationClient webCLientOpe;
	
	
	@Override
	public Flux<SavingsAccountVip> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingsAccountVip> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<SavingsAccountVip> save(SavingsAccountVip savingsAccountVip) {
		// TODO Auto-generated method stub
		return repo.save(savingsAccountVip);
	}
	
	@Override
	public Mono<SavingsAccountVip> update(SavingsAccountVip savingsAccountVip, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setName(savingsAccountVip.getName());
		s.setNumberAccount(savingsAccountVip.getNumberAccount());
		s.setBalance(savingsAccountVip.getBalance());
		s.setState(savingsAccountVip.getState());
		s.setTea(savingsAccountVip.getTea());
		s.setUpdateDate(new Date());
		s.setIdOperation(savingsAccountVip.getIdOperation());
		
		return repo.save(s);
		});
	}
	

	@Override
	public Mono<Void> delete(SavingsAccountVip savingsAccountVip) {
		
		return repo.delete(savingsAccountVip);
	}
	
	
	@Override
	public Mono<SavingsAccountVipDto> saveDto(SavingsAccountVipDto savingsAccountVipDto) {
	
		LOGGER.info("service --> "+savingsAccountVipDto.toString());

		return save(convert.convertSavingsAccountPersonalVip(savingsAccountVipDto)).flatMap(sa -> {

			savingsAccountVipDto.getHolders().forEach(p -> {

				p.setNameAccount(sa.getName());
				p.setIdCuenta(sa.getId());

				webCLient.save(p).block();

			});

			return Mono.just(savingsAccountVipDto);
		});
		
	}


	
	
//	@Override
//	public Mono<SavingsAccount> updateClient(SavingsAccount savingsAccount, String numAccount) {
//		
//		return repo.findByNumberAccount(numAccount).flatMap(s -> {
//
//		s.setNumberAccount(savingsAccount.getNumberAccount());
//		s.setBalance(savingsAccount.getBalance());
//		s.setState(savingsAccount.getState());
//		return repo.save(s);
//		});
//	}
//	
//	@Override
//	public Mono<SavingsAccountVip> findByNumAccount(String numAccount) {
//		
//		return repo.findByNumberAccount(numAccount);
//	}
//	
//	
//	@Override
//	public Mono<SavingsAccountDto> saveDto(SavingsAccountDto savingsAccountDto) {
//	
//	
//
//		return save(convert.convertSavingsAccount(savingsAccountDto)).flatMap(sa -> {
//
//			savingsAccountDto.getHolders().forEach(p -> {
//
//				p.setNameAccount(sa.getName());
//				p.setIdCuenta(sa.getId());
//
//				webCLient.save(p).block();
//
//			});
//
//			return Mono.just(savingsAccountDto);
//		});
//		
//	}
//	
//	@Override
//	public Mono<SavingsAccount> saveOperation(OperationDto operationDto) {
//		
//		
//		
//	return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{
//		
//	
//		if(operationDto.getTipoMovement().equals("debito")) {
//			
//			p.setBalance(p.getBalance()-operationDto.getAmount());
//			return repo.save(p);
//			
//		}else if(operationDto.getTipoMovement().equals("abono")) {
//			
//			p.setBalance(p.getBalance()+operationDto.getAmount());
//			return repo.save(p);
//		}
//		
//		return repo.save(p);
//	
//
//
//	});
//				
//				
//
//
//  }

}