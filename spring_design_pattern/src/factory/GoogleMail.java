package factory;

public class GoogleMail extends SMTPConnector{
	
	private EmailConf conf;
	
	protected GoogleMail(EmailConf conf) {
		super();
		this.conf = conf;
	}

	public EmailConf getConf() {
		return conf;
	}

	@Override
	public void connect() {
		System.out.println("구글 SMTP서버에 연결되었습니다.");
	}
}
