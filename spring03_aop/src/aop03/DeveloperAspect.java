package aop03;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


//공통관심사를 구현할 클래스
@Component
@Aspect
public class DeveloperAspect {
	
	@Before("execution(* aop03.*.*(..))")
	public void before() {
		System.out.println("출근 카드를 찍는다.");
	}
	
	@After("execution(* aop03.*.*(..))")
	public void after() {
		System.out.println("집에 간다.");
	}
	
	@AfterReturning(pointcut = "execution(* aop03.*.*(..))", returning = "res")
	public void afterReturning(Object res) {
		System.out.println(res);
		System.out.println("퇴근을 한다.");
	}
	
	@AfterThrowing(pointcut = "execution(* aop03.*.*(..))", throwing = "ex")
	public void afterThrowing(Exception ex) {
		System.out.println("쉬는 날이었다.");
	}
	
	

}
