package bean.cheatsheet;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Bean {
	
	private Code code;
	private Date date;
	
	public Bean() {
		System.out.println("Bean 기본 생성자");
	}

	public Bean(Date date) {
		super();
		System.out.println("Bean 매개변수가 있는 생성자");
		this.date = date;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
		System.out.println("setCode : " + code);
	}
	
	public void setNumber(int num) {
		System.out.println("setNumber : " + num);
	}
	
	public void setArray(String[] arr) {
		System.out.println("setArray : " + Arrays.toString(arr));
	}
	
	public void setList(List<String> list) {
		System.out.println("setList : " + list);
	}
	
	public void setSet(Set<Integer> set) {
		System.out.println("setSet : " + set);
	}
	
	public void setMap(Map<String,String> map) {
		System.out.println("setMap : " + map);
	}
	
	public void setProperties(Properties props) {
		System.out.println("setProperties : " + props);
	}
	
	public void setScores(List<Score> scores) {
		System.out.println("setScores : " + scores);
	}

	@Override
	public String toString() {
		return "Bean [code=" + code + ", date=" + date + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
