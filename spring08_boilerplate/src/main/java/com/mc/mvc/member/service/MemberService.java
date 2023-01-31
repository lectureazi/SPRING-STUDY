package com.mc.mvc.member.service;

import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.dto.validator.form.SignUpForm;

public interface MemberService {
	
	boolean existUser(String userId);

	void insertNewMember(SignUpForm form);

	void authenticateEmail(SignUpForm form, String authToken);

}
