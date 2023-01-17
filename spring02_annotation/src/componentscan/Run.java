package componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("componentscan/config.xml");
		
		Logger logger = context.getBean("warn", Logger.class);
		ExceptionHandler ex = context.getBean("ex", ExceptionHandler.class);
		
		logger.warn("테스트 워닝입니다.");
		ex.handle(new Exception("테스트 예외입니다."));
		
	}
}
