package com.mc.app.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mc.app.common.exception.DataAccessException;
import com.mc.app.common.util.JDBCTemplate;
import com.mc.app.member.dto.Member;

public class MemberRepository {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();

	public Member selectMemberById(Connection conn, String userId) {
		
		String sql = "select * from member where user_Id = ?";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			
			rset = pstm.executeQuery();
			while(rset.next()) {
				member = generateMember(rset);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException();
		}finally {
			jdt.close(rset, pstm);
		}
		
		return member;
	}
	
	private Member generateMember(ResultSet rset) throws SQLException {
		Member member;
		member = new Member();
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setGrade(rset.getString("grade"));
		member.setTell(rset.getString("tell"));
		member.setEmail(rset.getString("email"));
		member.setLeave(rset.getBoolean("is_leave"));
		member.setRegDate(rset.getTimestamp("reg_date").toLocalDateTime());
		member.setRentableDate(rset.getTimestamp("rentable_date").toLocalDateTime());
		return member;
	}


}
