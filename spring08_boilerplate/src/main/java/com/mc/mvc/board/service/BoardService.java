package com.mc.mvc.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.board.dto.Board;

public interface BoardService {

	void insertBoard(Board board, List<MultipartFile> files);

}
