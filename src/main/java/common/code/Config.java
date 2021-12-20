package common.code;

public enum Config {
	
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("futsalon2021@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("cxjguuxgisrswugo"),
	COMPANY_EMAIL("futsalon2021@gmail.com"),
	UPLOAD_PATH("/resources/img/team/");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
