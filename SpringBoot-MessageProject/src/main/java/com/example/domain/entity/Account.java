package com.example.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.domain.enums.Educational;
import com.example.domain.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
//@Proxy(lazy=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	
	@OneToMany(mappedBy="account")
	private List<Friends> friendsList;
	
	public String getFullName() {
		return lastName + firstName;
	}
}
