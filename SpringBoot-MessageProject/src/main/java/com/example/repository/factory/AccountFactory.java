package com.example.repository.factory;

import com.example.domain.entity.Account;

public class AccountFactory {

	//同じInterfaceに属しているクラスが複数ある場合、Factoryを検討
	
	static Account getAccount() {
		return new Account();
	}
}
