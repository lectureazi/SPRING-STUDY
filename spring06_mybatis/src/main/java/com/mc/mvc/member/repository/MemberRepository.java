package com.mc.mvc.member.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.mc.mvc.member.dto.Member;

public interface MemberRepository {

	@Select("SELECT * FROM member WHERE user_id = #{userId}")
	 Member selectMemberByUserId(@Param("userId") String userId);
}
