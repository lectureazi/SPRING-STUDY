package com.mc.mvc.board.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import com.mc.mvc.board.dto.Board;
import com.mc.mvc.common.file.FileInfo;

@Repository
public interface BoardRepository {

	@Insert("insert into board(user_id, title, content) values(#{userId}, #{title}, #{content})")
	@Options(useGeneratedKeys = true, keyProperty = "bdIdx")
	void insertBoard(Board board);

	@Insert("insert into file_info(origin_file_name, rename_file_name, save_path, bd_idx)"
			+ " values(#{originFileName},#{renameFileName},#{savePath},#{bdIdx})")
	void insertFileInfo(FileInfo fileInfo);

}
