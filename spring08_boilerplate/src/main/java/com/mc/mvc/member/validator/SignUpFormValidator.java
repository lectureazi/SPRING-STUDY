package com.mc.mvc.member.validator;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mc.mvc.member.repository.MemberRepository;
import com.mc.mvc.member.validator.form.SignUpForm;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final MemberRepository memeberRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		logger.info("SignUpFormValidator.supports 호출");
		return SignUpForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tell", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		
		SignUpForm form = (SignUpForm) target;
		
		if(memeberRepository.selectMemberByUserId(form.getUserId()) != null) {
			errors.rejectValue("userId", "existsId", "이미 존재하는 아이디 입니다.");
		}
		
		//비밀번호가 8글자 이상의 숫자, 영문자, 특수문자 조합인지 확인
		
		if(!Pattern.compile("(?!.*[ㄱ-힣])(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9ㄱ-힣])(?=.{8,})").matcher(form.getPassword()).find()) {
			errors.rejectValue("password", "password.format", "비밀번호는 영문, 숫자, 특수문자 조합의 8자리 이상의 문자열입니다.");
		}
		
		//존재하는 이메일인지 확인
//		if(memeberRepository.selectMemberByEmail(form.getEmail()) != null) {
//			errors.rejectValue("email", "exsit.email", "이미 존재하는 이메일 입니다.");
//		}
		
		
		
	}
	
	
	
	
	
	
	
	

}
