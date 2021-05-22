package com.example.service.interfaces;

import java.util.List;

import com.example.domain.entity.Account;
import com.example.domain.entity.RoomPlace;

public interface RoomPlaceService {
	
	//DBから指定されたAccountと関連のあるRoomPlaceを検索
	public List<RoomPlace> findByAccounts(Account account);
	
	public RoomPlace create(RoomPlace roomPlace);
	
	public RoomPlace update (RoomPlace roomPlace);
	
	public void delete(Integer id);
}
