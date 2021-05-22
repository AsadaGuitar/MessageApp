package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.session.AccountSession;
import com.example.domain.session.RoomSession;
import com.example.service.interfaces.AccountService;
import com.example.service.other.CreateDateTime;

@Service
public class RoomSessionService {
	
	@Autowired
	AccountSession userAccount;
	
	@Autowired
	RoomSession roomSession;
	
	@Autowired
	AccountSessionService userAccountService;
	
	@Autowired 
	AccountService accountService;
	
	@Autowired
	CreateDateTime createDateTime;
	
	
	public Account getFriendAccount() {
		Integer roomId = roomSession.getRoom().getId();
		Integer friendId = userAccount.getAccount().getRoomPlaces()
				.stream().filter(x -> x.getRoomId() == roomId).map(x -> x.getFriendId()).findFirst().get();
		return accountService.findOne(friendId).get();
	}
	
	public List<String> getMessageList(){
		return roomSession.getRoom().getMessageList()
				.stream().map(x -> x.getComment()).collect(Collectors.toList());
	}

}
