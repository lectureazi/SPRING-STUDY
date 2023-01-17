package factory;

import java.util.Calendar;

public class Run {

	public static void main(String[] args) {
		
		//다음 SMTP서버와 연결 해보자!
		ConnectorFactory.createSMTPConnector("daum").connect();
		Calendar.getInstance();
	}

}
