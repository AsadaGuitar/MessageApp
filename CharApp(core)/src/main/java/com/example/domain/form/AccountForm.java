package com.example.domain.form;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.domain.enums.Educational;
import com.example.domain.enums.Gender;
import com.example.domain.validation.CheckSame;
import com.example.domain.validation.IsMatchEducational;

import lombok.Data;

@Data
@IsMatchEducational(param="age", enumType="incumbent")
@CheckSame(password="password", check="rePassword")
public class AccountForm {

	@NotEmpty
	@Size(min=1,max=20)
	@Pattern(regexp="^[\u3040-\u309F]+$", message="{furigana}")
	private String firstNameF;
	
	@NotEmpty
	@Size(min=1,max=20)
	@Pattern(regexp="^[\u3040-\u309F]+$", message="{furigana}")
	private String lastNameF;
	
	@NotEmpty
	@Size(min=1,max=10)
	private String firstName;
	
	@NotEmpty
	@Size(min=1,max=10)
	private String lastName;
	
	@NotNull
	private int age;
	
	@NotNull
	private Gender gender;
	
	@NotNull
	private Educational incumbent;
	
	@NotNull
	private Educational education;
	
	@NotEmpty
	@Email
	private String mail;
	
	@NotEmpty
	@Size(min=8,max=99)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;
	
	@Size(min=8,max=99)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String rePassword;
	
	public String getFullName() {
		return lastName + firstName;
	}
}
