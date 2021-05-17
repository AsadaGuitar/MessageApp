package com.example.service.interfaces;

public interface MailService {

	public static final String ADRESS = "********";
	
	public boolean authenticateMailPassword(String userPass);
	
	public void sendMessage(String userMailAdress, String userName);
}
