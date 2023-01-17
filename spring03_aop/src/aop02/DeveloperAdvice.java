package aop02;

import org.springframework.stereotype.Component;

//공통관심사를 구현할 클래스
@Component
public class DeveloperAdvice {
	
	public void before() {
		System.out.println("출근 카드를 찍는다.");
	}
	
	public void afterReturning() {
		System.out.println();
		System.out.println("퇴근을 한다.");
	}
	
	public void afterThrowing() {
		System.out.println("쉬는 날이었다.");
	}
	
	public void after() {
		System.out.println("집에 간다.");
	}

}
