package com.example.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.Room;

@Service
public interface RoomService {

	//DBから指定のアカウントと関連のあるRoomを検索
	public List<Room> findByAccounts(Account account);
	
	public Optional<Room> findOne(Integer id);
	
	public Room create(Room room);
	
	public Room update(Room room);
	
	public void delete(Integer id);
}
