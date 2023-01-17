package aop01;

import org.springframework.stereotype.Component;

@Component
public class Woman implements Developer{

	@Override
	public void develop() {
		System.out.println("자바스크립트로 개발한다.");
	}

	@Override
	public void play() {
		System.out.println("로스트아크를 하고 논다.");
	}

}
