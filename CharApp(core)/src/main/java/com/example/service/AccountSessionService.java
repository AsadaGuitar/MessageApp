package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.entity.Account;
import com.example.domain.entity.Friend;
import com.example.domain.entity.Room;
import com.example.domain.entity.RoomPlace;
import com.example.domain.session.AccountSession;
import com.example.service.interfaces.AccountService;

@Service
public class AccountSessionService {
	
	@Autowired
	AccountSession userAccount;
	
	@Autowired
	AccountService accountService;
	
	
	public Room getRoomByFriendId(Integer friendId) throws Exception{
		
		Optional<RoomPlace> roomPlace = getRoomPlaceByFriendId(friendId);
		roomPlace.orElseThrow(Exception::new);
		
		Integer roomId = roomPlace.get().getRoomId();
		
		Optional<Room> room = getRoomByRoomId(roomId);
		room.orElseThrow(Exception::new);
		
		return room.get();
	}
	
	public Optional<Room> getRoomByRoomId(Integer roomId){
		List<Room> rooms = userAccount.getAccount().getRooms();
		return rooms.stream()
				.filter(x -> x.getId() == roomId).findFirst();
	}
	
	public Optional<RoomPlace> getRoomPlaceByFriendId(Integer friendId){
		return userAccount.getAccount().getRoomPlaces().stream()
				.filter(x -> x.getFriendId() == friendId).findFirst();
	}
	
	public List<Account> getFriendList(){
		
		List<Friend> friendList =  userAccount.getAccount().getFriends();
		List<Integer> friendIdList = 
				friendList.stream().map(x -> x.getFriendId()).collect(Collectors.toList());
		
		List<Account> friendAccountList = new ArrayList<Account>();
		for(Integer id : friendIdList) {
			friendAccountList.add(accountService.findOne(id).get());
		}
		
		return friendAccountList;
	}
}
