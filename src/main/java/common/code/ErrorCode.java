package common.code;

public enum ErrorCode {
	
	DATABASE_ACCESS_ERROR("�뜲�씠�꽣踰좎씠�뒪�� �넻�떊 以� �뿉�윭媛� 諛쒖깮�븯���뒿�땲�떎"),
	FAILED_VALIDATED_ERROR("�뜲�씠�꽣�쓽 �뼇�떇�씠 �쟻�빀�븯吏� �븡�뒿�땲�떎"),
	MAIL_SEND_FAIL_ERROR("�씠硫붿씪 諛쒖넚 以� �뿉�윭媛� 諛쒖깮�븯���뒿�땲�떎"),
	HTTP_CONNECT_ERROR("HTTP �넻�떊 以� �뿉�윭媛� 諛쒖깮�븯���뒿�땲�떎"),
	AUTHENTICATION_FAILED_ERROR("�쑀�슚�븯吏� �븡�� �씤利앹엯�땲�떎"),
	UNAUTHORIZED_PAGE("�젒洹� 沅뚰븳�씠 �뾾�뒗 �럹�씠吏� �엯�땲�떎"),
	REDIRECT_LOGIN_PAGE_NO_MESSAGE("","/member/login-form"),
	FAILED_FILE_UPLOAD_ERROR("�뙆�씪�뾽濡쒕뱶�뿉 �떎�뙣�뻽�뒿�땲�떎");
	
	public final String MSG;
	public final String URL;
	
	ErrorCode(String msg){
		this.MSG = msg;
		this.URL = "/index";
	}
	
	ErrorCode(String msg, String url){
		this.MSG = msg;
		this.URL = url;
	}
	
}
