package aop01;

import org.springframework.stereotype.Component;

@Component
public class Child implements Developer{

	@Override
	public void develop() {
		System.out.println("스크래치로 개발한다.");
	}

	@Override
	public void play() {
		System.out.println("마인크래프트를 하고 논다.");
	}
	
	

}
