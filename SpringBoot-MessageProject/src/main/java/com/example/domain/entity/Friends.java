package com.example.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="friends")
@Data
public class Friends {
	
	@Id
	private Integer friendsId;
	
	@Column
	private String friendsName;
	
	@ManyToOne(targetEntity=Account.class)
	private Account account;
	
	private String date;
}
