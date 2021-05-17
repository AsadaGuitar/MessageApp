package com.example.domain.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.domain.entity.Friends;
import com.example.domain.enums.Educational;
import com.example.domain.enums.Gender;

import lombok.Data;

@Component
@Data
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserAccount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	
	private String fullName;
	
	private Collection<GrantedAuthority> authorities;
	
	@SuppressWarnings("unchecked")
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = (Collection<GrantedAuthority>) authorities;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	private List<Friends> friendsList;	
}
