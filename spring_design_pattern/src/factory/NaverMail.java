package factory;

public class NaverMail extends SMTPConnector{
	
	private EmailConf conf;
	
	protected NaverMail(EmailConf conf) {
		super();
		this.conf = conf;
	}

	public EmailConf getConf() {
		return conf;
	}

	@Override
	public void connect() {
		System.out.println("네이버 SMTP서버에 연결되었습니다.");
	}
}
