package com.example.service.entityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;
import com.example.repository.FriendRepository;
import com.example.service.interfaces.FriendService;

@Service
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	FriendRepository friendRepository;

	@Override
	public Friend create(Friend friend) {
		return friendRepository.save(friend);
	}

	@Override
	public List<Friend> findByAccount(Account account) {
		return friendRepository.findByAccount(account);
	}

}
