package com.mc.mvc.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// 1. 해당 클래스의 인스턴스를 ApplicationContext에 빈으로 등록
// 2. Controller 역할을 수행하기 위한 Annotation을 사용할 수 있게 해준다.

@Controller
public class IndexController {
	
	// contextPath 생략
	// 일반적인 Spring의 Controller는 String viewName 또는 ModelAndView 객체에 viewName을 담아서 반환
	// 만약 return이 없을 경우 getMapping에 있는 주소와 동일한 이름의 viewName을 반환한 것으로 간주
	// return "index"; 가 생략된 것과 같다.
	// ViewResolver에 의해 /WEB-INF/views/index.jsp 로 forward가 된다.
	@GetMapping("/index")
	public String index(){return "index";}
	
	
	
	
	
	
	
	
	
	
	
}
