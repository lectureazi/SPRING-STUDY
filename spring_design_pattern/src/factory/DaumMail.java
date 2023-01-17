package factory;

public class DaumMail extends SMTPConnector{
	
	private EmailConf conf;
	
	protected DaumMail(EmailConf conf) {
		super();
		this.conf = conf;
	}

	public EmailConf getConf() {
		return conf;
	}

	@Override
	public void connect() {
		System.out.println("다음 SMTP서버에 연결되었습니다.");
	}
}
