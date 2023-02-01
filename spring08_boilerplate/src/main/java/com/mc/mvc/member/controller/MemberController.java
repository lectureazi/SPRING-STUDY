package com.mc.mvc.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.CookieGenerator;

import com.mc.mvc.common.code.ErrorCode;
import com.mc.mvc.common.exception.HandlableException;
import com.mc.mvc.common.validator.ValidatorResult;
import com.mc.mvc.member.dto.Member;
import com.mc.mvc.member.service.MemberService;
import com.mc.mvc.member.validator.SignUpFormValidator;
import com.mc.mvc.member.validator.form.SignUpForm;

import lombok.RequiredArgsConstructor;

//@Controller
//1. 해당 클래스의 인스턴스를 ApplicationContext에 빈으로 등록
//2. Controller 역할을 수행하기 위한 Annotation을 사용할 수 있게 해준다.
//		@RequstMapping : 컨트롤러의 메서드와 매핑시킹 요청 url을 지정, 원하는 http method를 지정할 수 있다.
//		@GetMapping	   : 컨트롤러의 메서드와 매핑시킹 GET 방식의 요청 url을 지정 
//		@PostMapping   : 컨트롤러의 메서드와 매핑시킹 POST 방식의 요청 url을 지정

//      @RequestParam  : content-type이 application/x-www-form-urlEncoded인 요청의 요청파라미터와 
//					    컨트롤러 메서드의 매개변수를 바인드
//						String또는 primitive type의 경우 default로 적용이 된다.
//						컨트롤러의 메서드의 매개변수가 Map일 경우, @RequestParam 을 명시적으로 적용 해줘야한다.
//						 	 name : 요청파라미터명, 매개변수에 바인드 시킬 요청파라미터명을 지정
//								 만약 매개변수명과 요청파라미터명이 일치할 경우 생략
//						     required : 특정 요청파라미터의 필수여부를 지정, default : true
//								 컨트롤러의 매개변수로 선언한 요청파라미터가 전달되지 않을 시 예외가 발생
//						  	 defaultValue : required가 false인 매개변수에, 요청파라미터가 전달 되지 않았을 경우 지정할 기본 값.

//		@RequestBody  :  request body를 읽어서 자바의 객체에 bind, Content type 'application/x-www-form-urlencoded' not supported
//		@ResponseBody : 컨트롤러의 메서드가 반환하는 값을 응답바디에 넣어준다.
// 		@SessionAttribute : servlet의 HttpSession객체에 저장된 속성에 담긴 값을 컨트롤러의 매개변수에 바인드
//		@PathVariable : url 템플릿에 담긴 파라미터값을 컨트롤러의 매개변수에 바인드

//		@ModelAttribute : 컨트롤러의 매개변수에 DTO를 선언할 경우 set + 요청파라미터명으로 요청파리미터를 객체에 바인드 
//						  요청파라미터가 바인된 DTO를 Model의 Attribute에 등록, 이때 Attribute name은 타입명(앞글자 소문자)을 따라간다.
//						  Model에 Attribute로 등록된 DTO에 있는 객체를 매개변수에 담아주는 어노테이션

//

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final SignUpFormValidator signUpFormValidator;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// ModelAttribute 가 동작하는 시점에 Data Binder가 수행
	// Controller메서드에 SignUpForm타입의 매개변수가 있을 경우 Validator가 실행된다.
	@InitBinder(value = "signUpForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(signUpFormValidator);
	}
	
	@GetMapping("/signup")
	public void signup() {};
	
	@PostMapping("/signup")
	public String authenticateEmail(
			@Validated SignUpForm form
			,Errors error // validator로 사용한 form 객체 바로 뒤에 errors객체를 선언 
			, Model model
			, HttpSession session) {
		
		ValidatorResult vr = new ValidatorResult();
		model.addAttribute("error", vr.getErrors());
		
		// validator를 통과하지 못했으면
		if(error.hasErrors()) {
			vr.addErrors(error);
			model.addAttribute("error", vr.getErrors());
			return "/member/signup";
		}
		
		// 발송한 이메일에 포함된 url로 사용자가 재요청할 때 사용자의 정보를 DB에 저장
		// 재요청때 까지 데이터를 저장하기 위해 세션객체에 사용자의 정보를 저장
		session.setAttribute("signupForm", form);
		
		// 사용자가 재요청시 해당 요청이 유효한 요청인지 확인하기 위한 token을 발행
		String authToken = UUID.randomUUID().toString();
		session.setAttribute("authToken", authToken);
		
		memberService.authenticateEmail(form, authToken);
		return "redirect:/index";
	}
	
	// @PathVariable : url에서 특정위치의 값을 컨트롤러의 매개변수에 바인드해준다.
	// /member/signupImpl/{authToken} : /member/signImpl/* 로 시작하는 모든 요청에 대해 메서드를 호출
	
	@GetMapping("/signupImpl/{authToken}")
	public String signUpImpl(HttpSession session, 
							@PathVariable String authToken,
							@SessionAttribute(name="authToken", required = false) String sessionToken,
							@SessionAttribute(name="signupForm", required = false) SignUpForm form,
							Model model) {
		
		// session에서 authToken을 꺼냈을 때 PathVariable로 받아온 authToken과 같지 않다면
		if(!authToken.equals(sessionToken)) {
			throw new HandlableException(ErrorCode.EXPRIATION_SIGNUP_TOKEN);
		}
		
		// 회원 정보를 데이터베이스에 저장
		memberService.insertNewMember(form);
		
		// 세션에 저장된 토큰을 삭제
		session.removeAttribute("authToken");
		
		return "redirect:/index";
	}
	
	@GetMapping("/checkId")
	@ResponseBody
	public Map<String, Boolean> checkId(String userId){
		//요청파라미터로 넘어온 회원의 존재 여부를 응답
		return Map.of("exist" , memberService.existUser(userId));
	}
	
	@GetMapping("/login")
	public void login() {}
	
	
	//RedirectAttributes : RedirectAttributes에 저장한 데이터를 redirect 시에 쿼리스트링으로 변환해서 응답
	// redirect 요청이 들어올 경우 model객체에 RedirectAttributes에 저장한 데이터를 옮겨준다.
	@PostMapping("/login")
	public String loginImpl(Member member, HttpSession session, RedirectAttributes redirectAttr) {
		
		Member auth = memberService.authenticateUser(member);
		
		if(auth == null) {
			redirectAttr.addFlashAttribute("msg", "아이디나 비밀번호가 틀렸습니다.");
			return "redirect:/member/login";
		}
		
		session.setAttribute("auth", auth);
		return "redirect:/index";
	}
	
	@GetMapping("mypage")
	public String mypage(@SessionAttribute(name="auth") Member auth,
						@CookieValue(name="JSESSIONID") String sessionId,
						HttpServletResponse response) {
		
		logger.debug("JSESSIONID 값 : " + sessionId);
		CookieGenerator cg = new CookieGenerator();
		cg.setCookieName("test_cookie");
		cg.addCookie(response, "testCookie_with_CookieGenerator");
		return "member/mypage";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("auth");
		return "redirect:/index";
	}
	
	
	
	
	

}
