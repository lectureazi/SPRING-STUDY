package com.mc.mvc.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mc.mvc.board.dto.Board;
import com.mc.mvc.common.paging.Paging;

@Repository
public interface BoardRepository {

	@Insert("insert into board(user_id, title, content) values(#{userId}, #{title}, #{content})")
	@Options(useGeneratedKeys = true, keyProperty = "bdIdx")
	void insertBoard(Board board);

	@Select("select * from board where is_del = 0 order by ${sortColumn} ${sortDirection} limit #{start}, #{cntPerPage}")
	List<Board> selectBoardList(Paging paging);
	
	@Select("select count(*) from board where is_del = 0")
	int countAllBoard();
	
	@Select("select * from board where is_del = 0 and bd_idx = #{bdIdx}")
	Board selectBoardByBdIdx(int bdIdx);

	@Update("update board set is_del = 1 where bd_idx = #{bdIdx}")
	void deleteBoardByBdIdx(int bdIdx);

	
}
