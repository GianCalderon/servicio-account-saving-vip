package com.springboot.savingsAccountVip.service;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.savingsAccountVip.client.PersonalVipClient;
import com.springboot.savingsAccountVip.document.SavingsAccountVip;
import com.springboot.savingsAccountVip.dto.AccountClient;
import com.springboot.savingsAccountVip.dto.AccountDto;
import com.springboot.savingsAccountVip.dto.OperationDto;
import com.springboot.savingsAccountVip.dto.PersonalVipDto;
import com.springboot.savingsAccountVip.dto.SavingsAccountVipDto;
import com.springboot.savingsAccountVip.repo.SavingsAccountVipRepo;
import com.springboot.savingsAccountVip.util.CodAccount;
import com.springboot.savingsAccountVip.util.UtilConvert;

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
	PersonalVipClient client;

	
	@Override
	public Flux<SavingsAccountVip> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingsAccountVip> findById(String id) {
		
		return repo.findById(id);
	}
	
	@Override
	public Mono<SavingsAccountVip> findByNumAccount(String numberAccount) {
		
		return repo.findByNumberAccount(numberAccount);
	}

	@Override
	public Mono<SavingsAccountVip> save(SavingsAccountVip SavingsAccountVip) {
		
		SavingsAccountVip.setCreateDate(new Date());
		SavingsAccountVip.setUpdateDate(new Date());
		SavingsAccountVip.setIdOperation(new ArrayList<String>());
		return repo.save(SavingsAccountVip);
	}

	@Override
	public Mono<SavingsAccountVip> update(SavingsAccountVip SavingsAccountVip, String id) {
		
		return repo.findById(id).flatMap(s -> {

		s.setNameAccount(SavingsAccountVip.getNameAccount());
		s.setNumberAccount(SavingsAccountVip.getNumberAccount());
		s.setBalance(SavingsAccountVip.getBalance());
		s.setState(SavingsAccountVip.getState());
		s.setTea(SavingsAccountVip.getTea());
		s.setUpdateDate(SavingsAccountVip.getUpdateDate());
		s.setCreateDate(SavingsAccountVip.getCreateDate());
		s.setIdOperation(SavingsAccountVip.getIdOperation());
		
		return repo.save(s);
		});
	}
	
	@Override
	public Mono<Void> delete(SavingsAccountVip SavingsAccountVip) {
		
		return repo.delete(SavingsAccountVip);
	}

	
	/* Guarda una cuenta con multiples titulares */
	@Override
	public Mono<SavingsAccountVipDto> saveHeadlines(SavingsAccountVipDto SavingsAccountVipDto) {
	
	

		return save(convert.convertSavingsAccountVip(SavingsAccountVipDto)).flatMap(cuenta -> {

			SavingsAccountVipDto.getHeadlines().forEach(titular -> {

				titular.setIdAccount(cuenta.getId());
				titular.setNameAccount(cuenta.getNameAccount());
				titular.setNumberAccount(cuenta.getNumberAccount());

				client.save(titular).block();

			});

			return Mono.just(SavingsAccountVipDto);
		});
		
	}

	 /* Guarda una cuenta con un titular */
	@Override
	public Mono<PersonalVipDto> saveHeadline(AccountDto accountDto) {

		return client.extractAccounts(accountDto.getNumDoc()).collectList().flatMap(cuentas -> {
		
		int cont = 0;

	     for (int i = 0; i < cuentas.size(); i++) {

				AccountClient obj = cuentas.get(i);

				LOGGER.info("PRUEBA 3 --->" + accountDto.toString());

			    if (obj.getNumberAccount().substring(0, 6).equals(CodAccount.COD_CURRENT_ACCOUNT)) cont++;

			}
	     
			if (cont == 0) {

				return repo.save(convert.convertAccountDto(accountDto)).flatMap(cuenta -> {

					return client.findByNumDoc(accountDto.getNumDoc()).flatMap(titular -> {

						LOGGER.info("Flujo Inicial ---->: " + titular.toString());

						titular.setIdAccount(cuenta.getId());
						titular.setNameAccount(cuenta.getNameAccount());
						titular.setNumberAccount(cuenta.getNumberAccount());

						LOGGER.info("Flujo Final ----->: " + titular.toString());

						return client.update(titular, accountDto.getNumDoc()).flatMap(p->{
							
							p.setIdAccount(cuenta.getId());
							return Mono.just(p);
						});

					});

				});

			} else {

				return Mono.empty();
			}

		});

	}
	
	@Override
	public Mono<SavingsAccountVip> saveOperation(OperationDto operationDto) {

	return repo.findByNumberAccount(operationDto.getNumAccount()).flatMap(p->{

		if(operationDto.getTipoMovement().equals("debito")) {
			
			p.setBalance(p.getBalance()-operationDto.getAmount());
			return repo.save(p);
			
		}else if(operationDto.getTipoMovement().equals("abono")) {
			
			p.setBalance(p.getBalance()+operationDto.getAmount());
			return repo.save(p);
		}
		
		return repo.save(p);

	});

  }
	
}