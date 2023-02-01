package com.mc.mvc.member.service;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.mc.mvc.common.code.Code;
import com.mc.mvc.common.mail.MailSender;
import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.repository.MemberRepository;
import com.mc.mvc.member.validator.form.SignUpForm;

import lombok.RequiredArgsConstructor;

// 스프링이 Proxy객체를 만들 때, target객체의 이름은 Interface명 + Impl로 작성
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MailSender sender;
	private final RestTemplate restTemplate;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean existUser(String userId) {
		Member member = memberRepository.selectMemberByUserId(userId);
		return member != null;
	}

	@Override
	public void insertNewMember(SignUpForm form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		memberRepository.insertMember(form);
	}

	@Override
	public void authenticateEmail(SignUpForm form, String authToken){
		
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("userId", form.getUserId());
		body.put("authToken", authToken);
		body.put("mailTemplate", "signup-email-auth");
		
		RequestEntity<Map<String, Object>> request = 
				RequestEntity
				.post(Code.DOMAIN + "/mail")
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);
		
		ResponseEntity<String> response =  restTemplate.exchange(request, String.class);
		String html = response.getBody();
		
		sender.send(form.getEmail(), "회원가입을 환영합니다. 링크를 클릭해 회원가입을 완료하세요.", html);
		
	}

	@Override
	public Member authenticateUser(Member rowMember) {
		
		Member member = memberRepository.selectMemberByUserId(rowMember.getUserId());
		
		if(member == null) return null;
		if(!passwordEncoder.matches(rowMember.getPassword(), member.getPassword())) return null;
		
		return member;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
