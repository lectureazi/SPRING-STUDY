package com.mc.mvc.member;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;



//가상으로 만들어지는 web.xml을 사용해 테스트환경을 구축
@WebAppConfiguration

//JUNIT의 실행방법을 지정
@RunWith(SpringJUnit4ClassRunner.class)

//테스트 때 사용할 가상의 ApplicationContext를 생성하고 관리
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberControllerTest {
	
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
	public void testSendAuthenticateMail() throws Exception {
		mockMvc.perform(post("/member/mailauth")
				.param("userId", "testUserWithEmail")
				.param("password", "1234")
				.param("email", "azimemory@gmail.com")
				.param("tell", "010-0119-0112"))
		.andDo(print());
	}
	
	@Test
	public void testCheckId() throws Exception {
		mockMvc.perform(get("/member/checkId")
				.param("userId", "admin"))
				.andDo(print());
	}
	
	@Test
	public void testSignUpFormValidator() throws Exception {
		mockMvc.perform(post("/member/mailauth")
				.param("userId", "testUserWithEmail")
				.param("password", "1234")
				.param("email", "azimemory@gmail.com")
				.param("tell", "010-0119-0112"))
		.andDo(print());
	}
	
	
	
	
	
	

}
