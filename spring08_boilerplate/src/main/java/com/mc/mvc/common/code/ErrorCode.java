package com.mc.mvc.common.code;

public enum ErrorCode {
	
	EXPRIATION_SIGNUP_TOKEN("이미 만료된 토큰 입니다.", "/member/signup"),
	FAILED_SEND_EMAIL("이메일 전송에 실패했습니다.");

	public String msg;
	public String redirect;
	
	private ErrorCode(String msg) {
		this.msg = msg;
		this.redirect = "/index";
	}
	
	ErrorCode(String msg, String redirect) {
		this.msg = msg;
		this.redirect = redirect;
	}
	

}
