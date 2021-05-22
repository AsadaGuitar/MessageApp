package com.example.domain.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


import lombok.Data;

@Component
@Data
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MailPassword {

	private String password;
}
