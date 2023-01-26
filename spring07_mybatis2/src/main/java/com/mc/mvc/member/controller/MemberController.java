package com.mc.mvc.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.service.MemberService;

//@Controller
//1. 해당 클래스의 인스턴스를 ApplicationContext에 빈으로 등록
//2. Controller 역할을 수행하기 위한 Annotation을 사용할 수 있게 해준다.
//		@RequstMapping : 컨트롤러의 메서드와 매핑시킹 요청 url을 지정, 원하는 http method를 지정할 수 있다.
//		@GetMapping	   : 컨트롤러의 메서드와 매핑시킹 GET 방식의 요청 url을 지정 
//		@PostMapping   : 컨트롤러의 메서드와 매핑시킹 POST 방식의 요청 url을 지정
//      @RequestParam  : content-type이 application/x-www-form-urlEncoded인 요청의 요청파라미터와 
//					    컨트롤러 메서드의 매개변수를 바인드
//						 	 name : 요청파라미터명, 매개변수에 바인드 시킬 요청파라미터명을 지정
//								 만약 매개변수명과 요청파라미터명이 일치할 경우 생략
//						     required : 특정 요청파라미터의 필수여부를 지정, default : true
//								 컨트롤러의 매개변수로 선언한 요청파라미터가 전달되지 않을 시 예외가 발생
//						  	 defaultValue : required가 false인 매개변수에, 요청파라미터가 전달 되지 않았을 경우 지정할 기본 값.
//		@ModelAttribute : 컨트롤러의 매개변수에 DTO를 선언할 경우 set + 요청파라미터명으로 요청파리미터를 객체에 바인드 
//						  요청파라미터가 바인된 DTO를 Model의 Attribute에 등록, 이때 Attribute name은 타입명(앞글자 소문자)을 따라간다.
//						  Model에 Attribute로 등록된 DTO에 있는 객체를 매개변수에 담아주는 어노테이션

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
		System.out.println("memberService : " + memberService.getClass());
	}

	@GetMapping("/signup")
	public void signup() {};
	
	@PostMapping("/mailauth")
	public String signup(Member member, Model model) {
		
		// redirect 하는 방법 : view Name을 반환할 때 앞에 redirect: 를 붙여준다.
		Member dummy = new Member();
		dummy.setUserId("dummy");
		dummy.setPassword("123124");
		dummy.setEmail("aaa@aaa.com");
		
		// model에 attribute이름을 지정하지 않으면 타입명을 따라간다. Member dummy => member
		// model.addAttribute(dummy);
		model.addAttribute("dummy", dummy);
		System.out.println(model);
		return "member/mypage";
	}
	
	@GetMapping("/checkId")
	public void checkId(){
		System.out.println(memberService.selectUserById());
	}
	
	
	
	
	
	
	

}
