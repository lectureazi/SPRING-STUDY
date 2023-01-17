package aop03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {

		ApplicationContext context =
				new ClassPathXmlApplicationContext("aop03/config.xml");
		
		Developer man = context.getBean("man", Developer.class);
		Developer woman = context.getBean("woman", Developer.class);
		
		System.out.println(man instanceof Developer);
		System.out.println(man instanceof Man);
		
		System.out.println("-----------------------------------------");
		man.develop();
		System.out.println("-----------------------------------------");
		woman.develop();
	}

}
