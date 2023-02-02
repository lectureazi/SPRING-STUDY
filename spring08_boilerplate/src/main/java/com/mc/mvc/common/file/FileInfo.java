package com.mc.mvc.common.file;

import java.time.LocalDateTime;

import com.mc.mvc.common.code.Code;

import lombok.Data;

@Data
public class FileInfo {
	
	private int flIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private LocalDateTime regDate;
	private Boolean isDel;
	private String groupName;
	private int gnIdx;
	
	public String getFullPath() {
		return Code.STORAGE_PATH + groupName + "/" + savePath + renameFileName;
	}
	
	
	
	
	
}
