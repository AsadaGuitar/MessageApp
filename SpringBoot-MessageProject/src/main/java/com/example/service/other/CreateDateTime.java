package com.example.service.other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class CreateDateTime {

	public String getAllSlash() {
		LocalDateTime nowDateTime = LocalDateTime.now(); 
		DateTimeFormatter java8Format = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm");
		return nowDateTime.format( java8Format );
	}
}
