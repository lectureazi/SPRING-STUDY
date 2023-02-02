package com.mc.mvc.board.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.board.dto.Board;
import com.mc.mvc.board.repository.BoardRepository;
import com.mc.mvc.common.code.Code;
import com.mc.mvc.common.code.ErrorCode;
import com.mc.mvc.common.exception.HandlableException;
import com.mc.mvc.common.file.FileInfo;
import com.mc.mvc.common.file.FileRepository;
import com.mc.mvc.common.file.FileUtil;
import com.mc.mvc.common.paging.Paging;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	private final BoardRepository boardRepository;
	private final FileRepository fileRepository;
	private final FileUtil fileUtil;

	@Override
	public void insertBoard(Board board, List<MultipartFile> files) {
		
		// 1. 게시글 내용을 board테이블에 insert
		boardRepository.insertBoard(board);
		
		// 2. 파일 업로드
		FileInfo fileInfo = new FileInfo();
		fileInfo.setGroupName("board");
		fileInfo.setGnIdx(board.getBdIdx());
		fileUtil.uploadFile(fileInfo, files);
		
	}

	@Override
	public FileInfo selectFileInfo(String flIdx) {
		FileInfo fileInfo = fileRepository.selectFileInfo(flIdx);
		return fileInfo;
	}

	@Override
	public Map<String,Object> selectBoardList(int page) {
		
		int total = boardRepository.countAllBoard();
		
		Paging paging = Paging.builder()
						.cntPerPage(10)
						.currentPage(page)
						.total(total)
						.blockCnt(10)
						.build();
		
		return Map.of("boardList",boardRepository.selectBoardList(paging),"paging", paging);
	}

	@Override
	public Map<String, Object> selectBoardContentByBdIdx(int bdIdx) {
		Board board = boardRepository.selectBoardByBdIdx(bdIdx);
		List<FileInfo> files = fileRepository.selectFileWithGroup(Map.of("groupName","board", "gnIdx", board.getBdIdx()));
		return Map.of("board", board, "files", files);
	}

	@Override
	public void deleteBoardByBdIdx(int bdIdx) {
		
		// 1. 게시글 삭제
		boardRepository.deleteBoardByBdIdx(bdIdx);
		
		// 2. 게시글에 첨부된 파일 삭제
		List<FileInfo> files = fileRepository.selectFileWithGroup(Map.of("groupName", "board", "gnIdx", bdIdx));
		
		fileRepository.deleFileByGroup(Map.of("groupName", "board", "gnIdx", bdIdx));

		for (FileInfo fileInfo : files) {
			System.out.println();
			new File(fileInfo.getFullPath()).delete();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
