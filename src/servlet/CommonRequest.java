package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class CommonRequest {
	
	private String reqstr;
	private String requestCode;        //接口号
	private JSONObject requestParam;
	
	public CommonRequest() {
		reqstr=null;
		requestCode=null;
		requestParam=null;
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
	
	//"requestCode","requestParam"是和客户端约好的请求体字段名称
	public void setRequestCode() {
		JSONObject object=JSONObject.fromObject(reqstr);
		requestCode=object.getString("requestCode");
	}
	
	public void setRequestParam() {
		JSONObject object=JSONObject.fromObject(reqstr);
		requestParam=object.getJSONObject("requestParam");
	}
	
	public String getReq() {
		return reqstr;
	}
	
	public String getRequestCode() {
		return requestCode;
	}
	
	public JSONObject getRequestParam() {
		return requestParam;
	}
}
