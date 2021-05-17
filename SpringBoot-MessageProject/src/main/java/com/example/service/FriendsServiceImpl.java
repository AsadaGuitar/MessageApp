package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.entity.Friends;
import com.example.domain.session.UserAccount;
import com.example.repository.FriendsRepository;
import com.example.service.interfaces.FriendsService;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService{

	@Autowired
	FriendsRepository friendsRepository;
	
	@Autowired
	UserAccount userAccount;
	
	//Friendsの一件検索
	@Override
	public Optional<Friends> findOne(Integer id) {
		return friendsRepository.findById(id);
	}
	
	@Override
	public List<Friends> findAll() {
		return friendsRepository.findAll();
	}
	
	//Friendsのリストを日付の新しい順に5件検索
	public List<Friends> findAllNewDate(Integer id){
		return friendsRepository.findAllByNewDate(id);
	}
	
	@Override
	public List<Friends> findAllByUserId(Integer id) {
		return friendsRepository.findAllByAccountId(id);
	}
	
	//ユーザーのFriendsLisｔに入力されたIDのフレンドがいるかどうか
	@Override
	public boolean isExist(Integer id) {
		
		System.out.println("isExist method is Start");
		
		
		//Friendsリストの中身があるかどうか
		if(userAccount.getFriendsList().size() <= 0)
			return false;
		
		System.out.println("success method 1");
		
		//入力されたIDを持つフレンドがいるかどうか
		for(Friends friend: userAccount.getFriendsList()) {
			if(friend.getFriendsId() == id)
				return true;
		}
		
		System.out.println("success method 2");
		
		return false;
	}
	
	@Override
	public Friends create(Friends friends) {
		return friendsRepository.save(friends);
	}
	
	@Override
	public Friends update(Friends friends) {
		return friendsRepository.save(friends);
	}
	
	@Override
	public void delete(Integer id) {
		friendsRepository.deleteById(id);
	}

}
