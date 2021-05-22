package com.example.domain.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AddFriendForm {
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]*$")
	private String friendId;
}
