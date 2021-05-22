package com.example.domain.enums;

public enum Gender {

	MAN("男性"),
	WOMAN("女性"),
	SECRET("無記入");
	
	private String japanese;
	
	Gender(String japanese){
		this.japanese = japanese;
	}
	
	public String getJapanese() {
		return japanese;
	}
}
