package com.mc.mvc.board.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Board {

	private Integer bdIdx;
	private String userId;
	private LocalDateTime regDate;
	private String title;
	private String content;
	private Boolean isDel;
	
}
