package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration : 클래스를 빈 설정용 클래스로 만들어주는 어노테이션
//				@Configuration이 선언된 클래스에서는 @Bean 어노테이션 사용 가능

@Configuration
public class BeanConfig {
	
	// @Bean : @Bean 어노테이션이 선언된 메서드가 반환하는 객체는 Application Context에 bean으로 등록된다.
	//		   name 속성을 지정하지 않으면 빈의 id가 메서드의 이름으로 지정
	@Bean(name="lon")
	public Book harryPorter() {
		return new Book("해리포터와 마법사의 돌", "조앤 K 롤링", 500);
	}
	
	@Bean
	public User admin() {
		return new User("하명도", "서울시", "010-9234-8248");
	}
	
	@Bean
	public Rent rent() {
		return new Rent();
	}
	
	
	
	
	
	
	
	
	
	

}
