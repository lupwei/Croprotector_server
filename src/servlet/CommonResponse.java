package servlet;

//网络返回应答就是一个应答CommonResponse对象
public class CommonResponse<T> {                    //因为data可以是数组或者对象，所以定义为泛型类
	
	public int code;
	public String resMsg;
	public T data;
	
	public CommonResponse() {
		code=0;
		resMsg=null;
		data=null;
	}
	
	public CommonResponse(int code,String resMsg,T data) {
		this.code=code;
		this.resMsg=resMsg;
		this.data=data;
	}
	
	public void setResMsg(String resMsg) {
		this.resMsg=resMsg;
	}
	
	public void setCode(int code) {
		this.code=code;
	}
	
	public void setData(T data) {
		this.data=data;
	}
	
	public String getResMsg() {
		return resMsg;
	}
	
	public int getCode() {
		return code;
	}
	
	public T getData() {
		return data;
	}
	
}
