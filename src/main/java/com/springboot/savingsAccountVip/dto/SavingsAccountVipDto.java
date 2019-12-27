package com.springboot.savingsAccountVip.dto;

import java.util.List;

import lombok.Data;

@Data
public class SavingsAccountVipDto {

	
	private Double tea;
	private String state;
	private Double balance;
	
	private List<PersonalVipDto> headlines;
	

}
