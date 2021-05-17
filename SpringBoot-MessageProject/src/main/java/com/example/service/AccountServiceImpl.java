package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.form.SignupForm;
import com.example.repository.AccountRepository;
import com.example.service.interfaces.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	/**
	 * Accountの検索
	 */
	@Override
	public Optional<Account> findOne(Integer id) {
		return accountRepository.findById(id);
	}
		
	/**
	 * Accountの有無を確認
	 */
	@Override
	public boolean isExist(Integer id) {
		System.out.println("service method start");
		if(findOne(id).orElse(null) == null)
			return false;
		
		return true;
	}
		
	/**
	 * Accountを全件取得
	 */
	@Override
	public List<Account> findAll(){
		return (List<Account>) accountRepository.findAll();
	}
	
	//アカウントの作成
	@Override
	public Account create (Account account) {
		return accountRepository.save(account);
	}
	
	/**
	 * SignupFormを元にアカウントを作成
	 * 
	 * @param form
	 * @return
	 */
	public Account createByForm(SignupForm form) {
		
		//SignupFormをAccountに変換
		Account account = new Account();
		BeanUtils.copyProperties(form, account);
		
		//Accountの情報を追加(パスワードを変換させる)
		String password = passwordEncoder.encode(form.getPassword());
		account.setPassword(password);
		account.setEnabled(true);
		account.setAdmin(false);
		
		//Accountを作成
		return accountRepository.save(account);
	}
	
	/**
	 * Accountの更新
	 */
	@Override
	public Account update(Account account) {
		return accountRepository.save(account);
	}
	
	/**
	 * Accoount の削除
	 */
	@Override
	public void delete(Integer id) {
		
	}
}
