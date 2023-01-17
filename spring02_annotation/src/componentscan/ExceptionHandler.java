package componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "ex")
public class ExceptionHandler {
	
	// @Autowried : @Autowired 어노테이션이 지정된 레퍼런스의 타입에 맞는 빈을 찾아서 자동으로 주입
	// Autowired를 생성자의 선언부에 선언하면 생성자의 매개변수를 통해 의존성을 주입받게 된다.
	// Spring이 추천하는 방식은 생성자를 통해 필드를 주입받는 것을 권장
	
	// @Qualifier : @Autowired로 의존성 주입을 받을 때, 해당 타입의 빈이 여러개 있을 경우, 빈의 아이디를 사용해 특정 빈을 주입

//	@Autowired
//	@Qualifier("warn")
	private Logger logger;
	
	@Autowired
	public ExceptionHandler(@Qualifier("warn") Logger logger) {
		super();
		this.logger = logger;
		System.out.println("ExceptionHandler의 매개변수가 있는 생성자를 통해 빈 생성");
	}

	public void handle(Exception e) {
		logger.warn(e.getMessage());
		System.out.println(e.getMessage() + "를 개발자에게 전송합니다.");
	}
}
