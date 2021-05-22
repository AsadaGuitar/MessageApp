package com.example.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.domain.enums.Educational;
import com.example.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account{
	
	/*
	 * ManyToManyのChildはgetterの名前を変える
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(name="accounts_room_places",
			joinColumns=@JoinColumn(name="account_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="room_place_id", referencedColumnName="id"))
//	@ToString.Exclude
	private List<RoomPlace> roomPlaces;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(name="accounts_rooms",
			joinColumns=@JoinColumn(name="account_id", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="room_id", referencedColumnName="id"))
//	@ToString.Exclude
	private List<Room> rooms;
	
	/**
	 * mappedByを付けることにより
	 * Friendがアカウントを設定して保存した時
	 * Account側でも保存される
	 */
	@OneToMany(mappedBy="account", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
//	@ToString.Exclude
	private List<Friend> friends;
	
	private String firstNameF;
	
	private String lastNameF;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private Gender gender;
	
	private Educational incumbent;
	
	private Educational education;
	
	private String mail;
	
	private String password;
	
	private boolean enabled;
	
	private boolean admin;
	
	public String getFullName() {
		return lastName + firstName;
	}
}
