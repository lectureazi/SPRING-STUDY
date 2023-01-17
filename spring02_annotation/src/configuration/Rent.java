package configuration;

import org.springframework.beans.factory.annotation.Autowired;

public class Rent {
	
	@Autowired
	private User user;
	
	@Autowired
	private Book book;

	public void info() {
		System.out.println(user.getName() + "이 " + book.getTitle() + "을 대여하였습니다.");
	}
	

}
