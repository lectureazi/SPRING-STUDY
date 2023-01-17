package bean.cheatsheet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("bean/cheatsheet/config.xml");
		Bean bean = context.getBean("bean", Bean.class);
		System.out.println(bean);
		

	}

}
