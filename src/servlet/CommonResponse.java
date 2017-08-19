package servlet;


public class CommonResponse {
	private String resMsg;
	
	public CommonResponse() {
		super();
		resMsg=null;
	}
	
	public void setResMsg(String resMsg) {
		this.resMsg=resMsg;
	}
	
	public String getResMsg() {
		return resMsg;
	}
	
}
