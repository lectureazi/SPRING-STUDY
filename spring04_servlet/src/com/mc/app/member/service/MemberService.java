package com.mc.app.member.service;

import java.sql.Connection;
import java.util.Map;

import com.mc.app.common.util.JDBCTemplate;
import com.mc.app.member.dto.Member;
import com.mc.app.member.repository.MemberRepository;

public class MemberService {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	private MemberRepository memberRepository = new MemberRepository();

	public Map<String, Object> userAuthenticate(String userId, String password) {
		
		Connection conn = null;
		
		try {
			conn = jdt.getConnection();
			Member member = memberRepository.selectMemberById(conn, userId);
			
			if(member == null) return Map.of("isSuccess", false, "msg" , "아이디가 존재하지 않습니다.");
			if(!member.getPassword().equals(password)) return Map.of("isSuccess", false, "msg" , "비밀번호가 틀렸습니다.");
			return Map.of("isSuccess", true, "member" , member);
			
		} finally {
			jdt.close(conn);
		}
	}

	
	
	
	
	
	
	
}
