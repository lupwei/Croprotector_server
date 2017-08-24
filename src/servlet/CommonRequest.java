package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

//网络请求的输入就是一个请求CommonRequest对象
public class CommonRequest {
	
	private String reqstr;
	
	public CommonRequest() {
		reqstr=null;
	}
	
	public void setReqstr(HttpServletRequest request) throws IOException {
		BufferedReader read=request.getReader();
		StringBuilder sb=new StringBuilder();
		String line=null;
		
		while((line=read.readLine())!=null) {
			sb.append(line);
		}
		
		reqstr=sb.toString();
		System.out.println(reqstr);
		read.close();
	}
	
	
	public String getReq() {
		return reqstr;
	}
	
	
}
