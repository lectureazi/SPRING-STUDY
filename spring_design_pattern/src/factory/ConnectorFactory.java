package factory;

public class ConnectorFactory {
	
	// 인스턴스를 생성하는 코드가 한 곳에 모여있다.
	// 인스턴스를 생성하는 코드가 변경되었을 때 변경이 Factory 클래스로 한정된다.
	public static SMTPConnector createSMTPConnector(String mailName) {
		
		switch (mailName.toUpperCase()) {
		case "NAVER":
			return new NaverMail(EmailConf.NAVER_CONF);
		case "DAUM":
			return new DaumMail(EmailConf.DAUM_CONF);
		case "GOOGLE":
			return new GoogleMail(EmailConf.GOOGLE_CONF);
		default:
			throw new RuntimeException("존재하지 않는 SMTP서버 이름입니다.");
		}
	}
}
