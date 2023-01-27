package com.mc.mvc.common.code;

public enum Code {
	
	DOMAIN("http://localhost:8080"),
	SMTP_FROM("lecture_azi@naver.com");

	public String desc;
	
	Code(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
}
