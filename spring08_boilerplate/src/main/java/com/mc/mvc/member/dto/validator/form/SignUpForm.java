package com.mc.mvc.member.dto.validator.form;

import lombok.Data;

@Data
public class SignUpForm {
	
	private String userId;
	private String password;
	private String tell;
	private String email;

}
