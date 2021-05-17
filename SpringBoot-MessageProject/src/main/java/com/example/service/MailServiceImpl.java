package com.example.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.domain.session.MailPassword;
import com.example.service.interfaces.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	MailSender sender;

	@Autowired
	MailPassword mailPassword;
	
	/**
	 * 入力されたパスワードとメールで送信したパスワードの比較
	 * 
	 * @param userPass
	 * @return
	 */
	@Override
	public boolean authenticateMailPassword(String userPass) {
		if(mailPassword.getPassword().equals(userPass))
			return true;
		return false;
	}
	
	
	/**
	 * 認証用パスワードをユーザーのメールに送る
	 * 
	 * @param userMailAdress
	 * @param userName
	 */
	@Override
	public void sendMessage(String userMailAdress, String userName) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		//ランダムにパスワードを発行しセッションに保存
		String pass = randomPass();
		mailPassword.setPassword(pass);
		
		//メールフォーマットの作成
		msg.setFrom(ADRESS);
		msg.setTo(userMailAdress);
		msg.setSubject("Hello " + userName + " !");
		msg.setText("password : " + pass);
		
		//メールの送信
		this.sender.send(msg);
	}
	
	String randomPass() {
		Random rdm = new Random();
		int num = rdm.nextInt(10000000) + 100000000;
		String strR = Integer.toString(num);
	
		return strR.substring(3, 8);
	}
}
