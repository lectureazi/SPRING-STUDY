package com.mc.mvc.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.mc.mvc.board.dto.Board;
import com.mc.mvc.board.service.BoardService;
import com.mc.mvc.member.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoadController {
	
	private final BoardService boardService;
	
	@GetMapping("board-form")
	public void boardForm() {}
	
	@PostMapping("upload")
	public String upload(@RequestParam List<MultipartFile> files,  Board board, @SessionAttribute Member auth) {
		
		board.setUserId(auth.getUserId());
		boardService.insertBoard(board, files);
		return "redirect:/index";
	}
	
	
	
	
	
	
	
	
	
}
