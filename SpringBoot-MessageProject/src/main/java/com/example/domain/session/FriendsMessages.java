package com.example.domain.session;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.domain.entity.Message;

import lombok.Data;

@Component
@Data
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FriendsMessages {

	private List<Message> messageList;
	
	private boolean switcher = true;
}
