package com.mc.mvc.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//가상으로 만들어지는 web.xml을 사용해 테스트환경을 구축
@WebAppConfiguration

//JUNIT의 실행방법을 지정
@RunWith(SpringJUnit4ClassRunner.class)

//테스트 때 사용할 가상의 ApplicationContext를 생성하고 관리
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MeberServiceImplTest {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage templateMessage;
	
	@Test
	public void testSendMail() {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("azimemory@gmail.com");
        msg.setText("hello");
        msg.setSubject("mail Test");
        try {
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
	}
	
	
	
	
	
	
	
	
	
	

}
