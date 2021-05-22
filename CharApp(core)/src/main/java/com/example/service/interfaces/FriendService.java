package com.example.service.interfaces;

import java.util.List;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;

public interface FriendService {

	public Friend create(Friend friend);

	public List<Friend> findByAccount(Account account);
}
