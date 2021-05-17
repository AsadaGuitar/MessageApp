package com.example.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.entity.Account;

public interface AccountService {
	
	public Optional<Account> findOne(Integer id);
	
	public boolean isExist(Integer id);
	
	public List<Account> findAll();
	
	public Account create(Account account);
	
	public Account update(Account account);
	
	public void delete(Integer id);
}
