package com.example.domain.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.domain.enums.Educational;
import com.example.domain.enums.Gender;
import com.example.domain.validation.CheckSame;
import com.example.domain.validation.IsMatchEducational;

import lombok.Data;

@Component
@Data
@IsMatchEducational(param="age", enumType="incumbent")
@CheckSame(password="password", check="rePassword")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SignupForm {
	
	@NotEmpty
	@Size(min=1,max=20)
	@Pattern(regexp="^[\u3040-\u309F]+$")
	private String firstNameF;
	@NotEmpty
	@Size(min=1,max=20)
	@Pattern(regexp="^[\u3040-\u309F]+$")
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
	private Educational incumbent; //現職
	@NotNull
	private Educational education; //最終学歴
	@NotEmpty
	@Email
	private String mail; //確認用のメール
	
	@NotEmpty
	@Size(min=8,max=99)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;
	
	@NotEmpty
	@Size(min=8,max=99)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String rePassword;
}
