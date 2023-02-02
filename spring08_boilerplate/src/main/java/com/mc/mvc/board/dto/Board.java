package com.mc.mvc.board.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class Board {

	private Integer bdIdx;
	private String userId;
	private LocalDateTime regDate;
	private String title;
	private String content;
	private Boolean isDel;
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
}
