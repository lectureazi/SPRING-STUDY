package bean.cheatsheet;

import java.util.Date;

public class Bean {
	
	private Code code;
	private Date date;
	
	public Bean() {
		System.out.println("Bean 기본 생성자");
	}

	public Bean(Date date) {
		super();
		this.date = date;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}
	
	public void setNumber(int num) {
		System.out.println("setNumber : " + num);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
