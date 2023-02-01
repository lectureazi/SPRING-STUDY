package com.mc.mvc.board.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	Logger logger =  LoggerFactory.getLogger(this.getClass());
	private final BoardRepository boardRepository;
	

	@Override
	public void insertBoard(Board board, List<MultipartFile> files) {
		
		// 1. 게시글 내용을 board테이블에 insert
		boardRepository.insertBoard(board);
		
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		// 2. 파일 업로드
		for (MultipartFile multipartFile : files) {
			if(multipartFile.isEmpty()) continue;
			
			String uploadPath = createUploadPath();
			String originFileName = multipartFile.getOriginalFilename();
			String renameFileName = createRenameFileName(originFileName);
			transferFile(multipartFile, uploadPath, renameFileName);
			
			FileInfo fileInfo = new FileInfo();
			fileInfo.setOriginFileName(originFileName);
			fileInfo.setRenameFileName(renameFileName);
			fileInfo.setSavePath(uploadPath);
			fileInfo.setBdIdx(board.getBdIdx());
			fileInfos.add(fileInfo);
		}
		
		// 3. 파일메타데이터를 file-info 테이블에 insert
		for (FileInfo fileInfo : fileInfos) {
			boardRepository.insertFileInfo(fileInfo);
		}
		
	}

	private File transferFile(MultipartFile multipartFile, String uploadPath, String renameFileName) {
		
		File dest = new File(Code.STORAGE_PATH + "board/" + uploadPath + renameFileName);
		 
		 try {
			multipartFile.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			throw new HandlableException(ErrorCode.FAILED_UPLOAD_FILE, e);
		}
		 
		return dest;
	}
	
	private String createUploadPath() {
		// 폴더를 기능, 일자별로 생성, 지나치게 많은 파일이 저장된 폴더는 열리지 않음
		LocalDate now =  LocalDate.now();
		String uploadPath = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/";
		new File(Code.STORAGE_PATH + "board/" + uploadPath).mkdirs();
		
		return uploadPath;
	}
	
	private String createRenameFileName(String originFileName) {
		// 파일이름을 유니크하게 변경, 만약 다른 사용자가 같은 이름의 파일을 업로드하면 덮어써지거나, 에러가 나기 때문
		String subfix = "";
		
		if(originFileName.contains(".")){
			subfix = originFileName.substring(originFileName.lastIndexOf("."));
		}

		return UUID.randomUUID().toString().substring(0, 8) + subfix; 
	}
	
	
	
	
	
	
	
}
