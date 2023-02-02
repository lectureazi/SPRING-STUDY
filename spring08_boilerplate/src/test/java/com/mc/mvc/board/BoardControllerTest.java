package com.mc.mvc.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.mc.mvc.member.dto.Member;

//가상으로 만들어지는 web.xml을 사용해 테스트환경을 구축
@WebAppConfiguration

//JUNIT의 실행방법을 지정
@RunWith(SpringJUnit4ClassRunner.class)

//테스트 때 사용할 가상의 ApplicationContext를 생성하고 관리
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class BoardControllerTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	// 테스트를 수행하기 전에 실행될 메서드
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(context).build();
	}
	
	@Test
	public void testUpload() throws Exception {
		Member member = new Member();
		member.setUserId("logintest");
		
		MockMultipartFile file1 = new MockMultipartFile("files", "ofn1.txt", "text/plain", "파일업로드테스트1".getBytes());
		MockMultipartFile file2 = new MockMultipartFile("files", "ofn2.txt", "text/plain", "파일업로드테스트2".getBytes());
		
		mockMvc.perform(multipart("/board/upload")
				.file(file1)
				.file(file2)
				.param("title", "게시글업로드테스트")
				.param("content", "게시글업로드테스트 중입니다.")
				.sessionAttr("auth", member))
		.andDo(print());
		
	}
	
	
	
	

}









