package common.code;

public enum Config {
	
	DOMAIN("http://localhost:7070"),
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
