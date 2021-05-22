package com.example.service.interfaces;

import com.example.domain.form.AccountForm;

public interface MailService {

	public static final String ADRESS = "";
	
	public boolean authenticateMailPassword(String userPassword, String mailPassword);
	
	public String sendMessage(AccountForm accountForm);
	
}
