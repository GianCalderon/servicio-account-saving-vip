package com.springboot.savingsAccountPersonalVip.controller;

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

import com.springboot.savingsAccountPersonalVip.document.SavingsAccountVip;
import com.springboot.savingsAccountPersonalVip.dto.SavingsAccountVipDto;
import com.springboot.savingsAccountPersonalVip.service.SavingsAccountVipImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/savingsAccountPersonalVip")
public class SavingsAccountVipController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SavingsAccountVipController.class);

	@Autowired
	SavingsAccountVipImpl service;

	@GetMapping
	public Mono<ResponseEntity<Flux<SavingsAccountVip>>> toList() {

		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));

	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccountVip>> search(@PathVariable String id) {
		
		LOGGER.info("NUMERO DE CUENTA SavinAcount :--->"+id);

		return service.findById(id).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping
	public Mono<ResponseEntity<SavingsAccountVip>> save(@RequestBody SavingsAccountVip savingsAccountVip) {

		return service.save(savingsAccountVip)
				.map(s -> ResponseEntity.created(URI.create("/api/savingsAccountPersonalVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s));

	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<SavingsAccountVip>> update(@RequestBody SavingsAccountVip savingsAccountVip,
			@PathVariable String id) {
		
		
		LOGGER.info("CUENTA PARA ACTUALIZAR :--->"+savingsAccountVip.toString());

		return service.update(savingsAccountVip, id)
				.map(s -> ResponseEntity.created(URI.create("/api/savingsAccountPersonalVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}
	

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

		return service.findById(id).flatMap(s -> {
			return service.delete(s).then(Mono.just(new ResponseEntity<Void>(HttpStatus.ACCEPTED)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
	
	
//	OPERACION QUE EXPONEN SERVICIOS

	@PostMapping("/saveDto")
	public Mono<ResponseEntity<SavingsAccountVipDto>> saveDto(@RequestBody SavingsAccountVipDto savingsAccountVipDto) {

		LOGGER.info("controller --> "+savingsAccountVipDto.toString());

		return service.saveDto(savingsAccountVipDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccountPersonalVip"))
				.contentType(MediaType.APPLICATION_JSON).body(s));

	}
//	
//	@PostMapping("/operation")
//	public Mono<ResponseEntity<SavingsAccount>> operation(@RequestBody OperationDto operationDto) {
//
//		LOGGER.info(operationDto.toString());
//
//		return service.saveOperation(operationDto).map(s -> ResponseEntity.created(URI.create("/api/savingsAccountPersonalVip"))
//				.contentType(MediaType.APPLICATION_JSON).body(s));
//
//	}
//	
//	
//	@GetMapping("/cuenta/{numAccount}")
//	public Mono<ResponseEntity<SavingsAccount>> searchByNumDoc(@PathVariable String numAccount) {
//		
//		LOGGER.info("NUMERO DE CUENTA :--->"+numAccount);
//
//		return service.findByNumAccount(numAccount).map(s -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(s))
//				.defaultIfEmpty(ResponseEntity.notFound().build());
//
//	}

	
	
	

}