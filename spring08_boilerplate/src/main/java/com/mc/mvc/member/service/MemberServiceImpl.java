package com.mc.mvc.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

// 스프링이 Proxy객체를 만들 때, target객체의 이름은 Interface명 + Impl로 작성
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Member selectUserById() {
		return memberRepository.selectMemberByUserId("admin");
	}

	@Override
	public void insertNewMember(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.insertMember(member);
	}
	
	
	
	
	
	
	

}
