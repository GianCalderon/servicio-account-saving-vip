package com.springboot.savingsAccountPersonalVip.dto;

import java.util.List;

import lombok.Data;

@Data
public class SavingsAccountVipDto {

	private String numberAccount;
	
	private Double tea;

	private String state;

	private Double balance;
 
	private List<PersonalVipDto> holders;	
	
	

}
