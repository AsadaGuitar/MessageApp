package com.example.domain.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@NoArgsConstructor
@AllArgsConstructor
public class Friend {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	 * targetEntityはつけない
	 */
	@ManyToOne
	private Account account;
	
	private Integer friendId;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Account getAccount() {
		return account;
	}
	
	public Account acquireAccount() {
		return getAccount();
	}

	
	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	
	
}
