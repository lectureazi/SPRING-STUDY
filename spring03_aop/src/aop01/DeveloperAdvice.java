package aop01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

//공통관심사를 구현할 클래스
@Component
public class DeveloperAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println("출근 카드를 찍는다.");
		
		try {
			// target 객체의 메서드를 호출
			invocation.proceed();
		} catch (Exception e) {
			System.out.println("쉬는 날이었다.");
		}finally {
			System.out.println("집에 간다.");
		}
		
		return null;
	}

}
