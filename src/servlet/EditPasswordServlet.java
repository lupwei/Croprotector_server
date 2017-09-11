package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.User;

/**
 * Servlet implementation class EditPasswordServlet
 */
@WebServlet("/EditPasswordServlet")
public class EditPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPasswordServlet() {
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
		
		//用Password对象接收新密码，和用户
		CommonRequest req=new CommonRequest();
		req.setReqstr(request);
		String jsonStr=req.getReq();
		Gson gson=new Gson();
		Password password=new Password();
		password=gson.fromJson(jsonStr, Password.class);
		
		CommonResponse<String> res=new CommonResponse<String>(0,"success","");
		
		//在数据库中更改密码
		User user=new User();
		user.setPhoneNumber(password.getPhoneNumber());
		user.setPassword(password.getNewPassword());
		int retResult=user.changePassword();                        //数据库操作
		
		if(retResult>0) {
			res.setData("修改密码成功");
		}
		else {
			res.setCode(1);
			res.setData("服务器修改密码失败");
		}
		
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
