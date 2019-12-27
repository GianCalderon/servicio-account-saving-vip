package com.springboot.savingsAccountVip.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.savingsAccountVip.client.PersonalVipClient;
import com.springboot.savingsAccountVip.document.SavingsAccountVip;
import com.springboot.savingsAccountVip.dto.AccountDto;
import com.springboot.savingsAccountVip.dto.OperationDto;
import com.springboot.savingsAccountVip.dto.PersonalVipDto;
import com.springboot.savingsAccountVip.dto.SavingsAccountVipDto;
import com.springboot.savingsAccountVip.service.SavingsAccountVipImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/savingsAccountVip")
public class SavingsAccountVipController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccountVipController.class);

	@Autowired
	SavingsAccountVipImpl service;
	
	@Autowired
	PersonalVipClient client;

	@GetMapping
	public Mono<ResponseEntity<Flux<SavingsAccountVip>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccountVip>> search(@PathVariable String id) {
		
		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	
	@PostMapping
	public Mono<ResponseEntity<SavingsAccountVip>> save(@RequestBody SavingsAccountVip SavingsAccountVip) {

		return service.save(SavingsAccountVip)
				.map(s -> ResponseEntity.created(URI.create("/api/SavingsAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	

	@PutMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccountVip>> update(@RequestBody SavingsAccountVip SavingsAccountVip,
			@PathVariable String id) {
		
		
		LOGGER.info("Controller ----> "+SavingsAccountVip.toString());

		return service.update(SavingsAccountVip, id)
				.map(s -> ResponseEntity.created(URI.create("/api/SavingsAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	//OPERACIONES QUE CONSUMEN SERVICIO
	
	
	@PostMapping("/operation")
	public Mono<ResponseEntity<SavingsAccountVip>> operation(@RequestBody OperationDto operationDto) {

		LOGGER.info(operationDto.toString());

		return service.saveOperation(operationDto).map(s -> ResponseEntity.created(URI.create("/api/SavingsAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
	
	
	@PostMapping("/saveHeadline")
	public Mono<ResponseEntity<PersonalVipDto>> saveHeadline(@RequestBody AccountDto accountDto) {

		LOGGER.info("Controller ---> :"+accountDto.toString());

		return service.saveHeadline(accountDto).map(s -> ResponseEntity.created(URI.create("/api/SavingsAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<PersonalVipDto>(HttpStatus.CONFLICT));
				

	}
	
	
	@PostMapping("/saveHeadlines")
	public Mono<ResponseEntity<SavingsAccountVipDto>> saveHeadlines(@RequestBody SavingsAccountVipDto SavingsAccountVipDto) {

		LOGGER.info("Controller ----> "+SavingsAccountVipDto.toString());

		return service.saveHeadlines(SavingsAccountVipDto).map(s -> ResponseEntity.created(URI.create("/api/SavingsAccountVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(new ResponseEntity<SavingsAccountVipDto>(HttpStatus.CONFLICT));

	}
	
	
	@GetMapping("/cuenta/{numberAccount}")
	public Mono<ResponseEntity<SavingsAccountVip>> searchByNumAccount(@PathVariable String numberAccount) {
		
		LOGGER.info("NUMERO DE CUENTA :--->"+numberAccount);

		return service.findByNumAccount(numberAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	
	
	
	

}
