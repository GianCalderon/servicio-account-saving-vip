package com.springboot.savingsAccountPersonalVip.util;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.savingsAccountPersonalVip.document.SavingsAccountVip;
import com.springboot.savingsAccountPersonalVip.dto.SavingsAccountVipDto;

@Component
public class UtilConvert {
	
	
	public SavingsAccountVip convertSavingsAccountPersonalVip(SavingsAccountVipDto savingsAccountVipDto) {

		SavingsAccountVip savingsAccountVip = new SavingsAccountVip();

		savingsAccountVip.setName("Cuenta-Ahorro-PersonalVip");
		savingsAccountVip.setNumberAccount(savingsAccountVipDto.getNumberAccount());
		savingsAccountVip.setTea(savingsAccountVipDto.getTea());
		savingsAccountVip.setState(savingsAccountVipDto.getState());
		savingsAccountVip.setBalance(savingsAccountVipDto.getBalance());
		savingsAccountVip.setCreateDate(new Date());
		savingsAccountVip.setUpdateDate(new Date());
		savingsAccountVip.setIdOperation(new ArrayList<String>());

		return savingsAccountVip;

	}

}
