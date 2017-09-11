package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.DiseaseInfo;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String method=request.getMethod();
    	if(method.equals("GET")) {
    		System.out.println("请求方法：GET");
    		doGet(request,response);
    	}
    	else if(method.equals("POST")) {
    		System.out.println("请求方法：POST");
    		doPost(request,response);
    	}
    	else {
    		System.out.println("请求方法无法识别！");
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("不支持GET方式");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//接收一个手机号代表用户信息，说明查找哪个用户的历史记录
		CommonRequest req=new CommonRequest();
		req.setReqstr(request);
		String jsonStr=req.getReq();
		Gson gson=new Gson();
		String phonenumber=gson.fromJson(jsonStr, String.class);
		DiseaseInfo info=new DiseaseInfo();
		info.setPhoneNumber(phonenumber);
		
		CommonResponse<List<DiseaseInfo>> res=new CommonResponse<List<DiseaseInfo>>(0,"success",null);
		
		//获取该用户的历史记录
		List<DiseaseInfo> lstInfo=info.getByPhoneNumber();
		for(int i=0;i<lstInfo.size();i++) {
			DiseaseInfo diseaseinfo=new DiseaseInfo();
			diseaseinfo=lstInfo.get(i);
			String url=diseaseinfo.getPicture();
			String base64Str=PictureClass.ImageToBase64(url);
			diseaseinfo.setPicture(base64Str);
			lstInfo.set(i, diseaseinfo);
		}
		res.setCode(0);
		res.setData(lstInfo);
		
		//将结果封装成json格式返回给客户端,但实际网络传输时还是传输json的字符串
		//json只是提供了特定的字符串拼接格式
		String resStr=gson.toJson(res);
		System.out.println("返回报文的json字符串为："+resStr);
		response.setContentType("text/html;charset=utf-8");          //记着设置字符编码格式，不然汉字是乱码
		PrintWriter pw=response.getWriter();
		pw.println(resStr);
		pw.flush();
	}

}
