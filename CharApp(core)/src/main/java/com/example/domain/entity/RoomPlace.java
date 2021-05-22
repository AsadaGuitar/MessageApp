package com.example.domain.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="room_place")
@NoArgsConstructor
@AllArgsConstructor
public class RoomPlace {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer roomId;
	
	private Integer friendId;
	
	@ManyToMany(mappedBy="roomPlaces", fetch=FetchType.LAZY)
	private List<Account> accounts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoomId() {
		return roomId;
	}
	
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	private List<Account> getAccounts(){
		return accounts;
	}
	
	public List<Account> acquireAccounts(){
		return getAccounts();
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
