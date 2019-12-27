package com.springboot.savingsAccountVip.dto;

import lombok.Data;

@Data
public class PersonalVipDto {
	
	private String idAccount;
	private String numberAccount="xxxxxxxxxxxxxx";
	private String nameAccount="Cuenta-Ahorro";
	
	private String tipoDoc;
	private String numDoc;
	private String name;
	private String apePat;
	private String apeMat;
	private String address;
}
