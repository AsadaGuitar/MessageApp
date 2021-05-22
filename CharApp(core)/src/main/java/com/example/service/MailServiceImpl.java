package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.domain.form.AccountForm;
import com.example.service.interfaces.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	MailSender sender;

	
	/**
	 * 入力されたパスワードとメールで送信したパスワードの比較
	 * 
	 * @param userPass
	 * @return
	 */
	@Override
	public boolean authenticateMailPassword(String userPassword, String mailPassword) {
		
		System.out.println(userPassword);
		System.out.println(mailPassword);
		if(mailPassword.equals(userPassword))
			return true;
		return false;
	}
	
	/**
	 * 認証用パスワードをユーザーのメールに送り
	 * 認証用パスワードを返す。
	 * 
	 * @param userMailAdress
	 * @param userName
	 */
	@Override
	public String sendMessage(AccountForm accountForm) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		//ランダムにパスワードを発行しセッションに保存
		String pass = randomPass();
		
		//メールフォーマットの作成
		msg.setFrom(ADRESS);
		msg.setTo(accountForm.getMail());
		msg.setSubject("Hello " + accountForm.getFullName() + " !");
		msg.setText("password : " + pass);
		
		//メールの送信
		this.sender.send(msg);
		
		return pass;
	}
	
	private String randomPass() {
		Random rdm = new Random();
		int num = rdm.nextInt(10000000) + 100000000;
		String strR = Integer.toString(num);
	
		return strR.substring(3, 8);
	}
	
}
