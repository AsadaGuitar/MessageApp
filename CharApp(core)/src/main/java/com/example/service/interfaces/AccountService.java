package com.example.service.interfaces;

import java.util.List;
import java.util.Optional;


import com.example.domain.entity.Account;
import com.example.domain.form.AccountForm;

public interface AccountService {
	
	public Optional<Account> findOne(Integer id);
	
	public boolean isExist(Integer id);
	
	public List<Account> findAll();
		
	public Account createByAccountForm(AccountForm account);
	
	public Account update(Account account);
	
	public void delete(Integer id);
}
