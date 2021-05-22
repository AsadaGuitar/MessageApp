package com.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean 
	AuthenticationSuccessHandler customSuccessHandler() {
	    
	    return new CustomSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http.authorizeRequests() 
		.mvcMatchers("/adminHome").hasRole("ADMIN")
		.mvcMatchers("/home/messagePost").hasRole("USER")
		.mvcMatchers("/home").hasRole("USER")
		.mvcMatchers("/signinForm").permitAll()
		.mvcMatchers("/signinForm/error").permitAll()
		.mvcMatchers("/signupForm").permitAll()
		.mvcMatchers("/signup").permitAll()
		.mvcMatchers("/confirmation").permitAll()
		.mvcMatchers("/setPassword").permitAll()
		.anyRequest().authenticated(); 
		
		http.exceptionHandling()
		.accessDeniedPage("/loginForm");
			
		http.formLogin()
			.loginPage("/loginForm").permitAll()
			.loginProcessingUrl("/authenticate")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(customSuccessHandler())
			.failureForwardUrl("/signinForm/error");
					
		http.logout()
		    .logoutUrl("/logout")
			.logoutSuccessUrl("/loginForm").permitAll();
	}
		
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
