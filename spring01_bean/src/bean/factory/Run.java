package bean.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("bean/factory/config.xml");
		Week day = context.getBean("dayFactory", Week.class);
		System.out.println(day.dayInfo());

	}

}
