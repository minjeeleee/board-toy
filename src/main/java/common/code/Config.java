package common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("futsalon2021@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("cxjguuxgisrswugo"),
	COMPANY_EMAIL("futsalon2021@gmail.com"),
	//UPLOAD_PATH("C:\\CODE\\upload") �슫�쁺�꽌踰�
	//UPLOAD_PATH("C:\\CODE\\upload\\");//媛쒕컻�꽌踰�
	UPLOAD_PATH("/resources/img/team/");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
