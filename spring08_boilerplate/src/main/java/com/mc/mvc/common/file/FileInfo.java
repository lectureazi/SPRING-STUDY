package com.mc.mvc.common.file;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FileInfo {
	
	private int flIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private LocalDateTime regDate;
	private Boolean isDel;
	private int bdIdx;
}
