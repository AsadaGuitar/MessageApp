package com.example.domain.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MessageForm {

	private Integer toUser;
	
	private Integer fromUser;
	@NotEmpty
	private String comment;

	private String date;

	private String toName;

	private String fromName;

}
