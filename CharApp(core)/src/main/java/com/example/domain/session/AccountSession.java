package com.example.domain.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.domain.entity.Account;

import lombok.Data;

@Component
@Data
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AccountSession implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Account account;
}
