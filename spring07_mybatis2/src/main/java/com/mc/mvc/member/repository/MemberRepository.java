package com.mc.mvc.member.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mc.mvc.member.dto.Member;

@Repository
public interface MemberRepository {

	@Select("SELECT * FROM member WHERE user_id = #{userId}")
	 Member selectMemberByUserId(@Param("userId") String userId);
}
