package com.example.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.domain.entity.Friends;

public interface FriendsService {

	public Optional<Friends> findOne(Integer id);
	
	public boolean isExist(Integer id);
	
	public List<Friends> findAll();
	
	public List<Friends> findAllByUserId(Integer id);
	
	public Friends create(Friends friends);
	
	public Friends update(Friends friends);
	
	public void delete(Integer id);
}
