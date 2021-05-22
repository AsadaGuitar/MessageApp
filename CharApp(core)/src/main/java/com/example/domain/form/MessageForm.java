package com.example.domain.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MessageForm {
	
	@NotEmpty
	@Size(min=1, max=50)
	private String comment;

}
