package com.mc.mvc.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.repository.MemberRepository;

// 스프링이 Proxy객체를 만들 때, target객체의 이름은 Interface명 + Impl로 작성
@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberRepository memberRepository;
	
	// MemberRepository를 생성자 주입 받음
	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
		System.out.println("memberRepository : " + memberRepository.getClass());
	}

	@Override
	public Member selectUserById() {
		return memberRepository.selectMemberByUserId("admin");
	}
	
	
	
	
	
	
	

}
