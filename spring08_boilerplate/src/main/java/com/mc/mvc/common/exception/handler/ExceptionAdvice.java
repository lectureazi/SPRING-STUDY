package com.mc.mvc.common.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mc.mvc.common.exception.HandlableException;

@Component

// @ControllerAdvice:  
//		com.mc.mvc 패키지 아래에 존재하는 Controller의 공통 작업(예외처리, 데이터바인딩)을 모듈화한 객체
@ControllerAdvice(basePackages = "com.mc.mvc")
public class ExceptionAdvice {
	
	// 지정한 예외가 Controller에서 발생하면 어노테이션이 선언된 메서드가 호출 된다.
	@ExceptionHandler(HandlableException.class)
	public String handlingHandlableException(HandlableException e, Model model) {
		model.addAttribute("msg", e.error.msg);
		model.addAttribute("redirect", e.error.redirect);
		return "common/alert-message";
	}
	
	
	
	
	
	
	

}
