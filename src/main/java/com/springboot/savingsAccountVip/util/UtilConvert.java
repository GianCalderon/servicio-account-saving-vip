package com.springboot.savingsAccountVip.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.savingsAccountVip.document.SavingsAccountVip;
import com.springboot.savingsAccountVip.dto.AccountDto;
import com.springboot.savingsAccountVip.dto.SavingsAccountVipDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);
	
	public SavingsAccountVip convertSavingsAccountVip(SavingsAccountVipDto SavingsAccountVipDto) {

                      LOGGER.info("convetir -->"+SavingsAccountVipDto);
		
		SavingsAccountVip SavingsAccountVip = new SavingsAccountVip();

		SavingsAccountVip.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		SavingsAccountVip.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		SavingsAccountVip.setTea(SavingsAccountVipDto.getTea());
		SavingsAccountVip.setState(SavingsAccountVipDto.getState());
		SavingsAccountVip.setBalance(SavingsAccountVipDto.getBalance());
		SavingsAccountVip.setCreateDate(new Date());
		SavingsAccountVip.setUpdateDate(new Date());
		SavingsAccountVip.setIdOperation(new ArrayList<String>());

		return SavingsAccountVip;

	}
	
	public SavingsAccountVip convertAccountDto(AccountDto accountDto) {

		SavingsAccountVip  SavingsAccountVip = new SavingsAccountVip();

		SavingsAccountVip.setNameAccount("Cuenta-Ahorro");
		SavingsAccountVip.setNumberAccount("00102020"+String.valueOf((int)(Math.random()*99999999+1)));
		SavingsAccountVip.setState(accountDto.getState());
		SavingsAccountVip.setBalance(accountDto.getBalance());
		SavingsAccountVip.setTea(accountDto.getTea());
		SavingsAccountVip.setCreateDate(new Date());
		SavingsAccountVip.setUpdateDate(new Date());
		SavingsAccountVip.setIdOperation(new ArrayList<String>());

		
		return SavingsAccountVip;

	}

}
