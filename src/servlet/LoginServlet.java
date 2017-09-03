package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("不支持GET方式");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//需要返回用户的用户名及是否登陆成功的信息,所以要返回的data要是一个List<string>
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		CommonRequest req=new CommonRequest();
		req.setReqstr(request);
		String jsonStr=req.getReq();
        User user1=new User();
        Gson gson=new Gson();
        user1=gson.fromJson(jsonStr,User.class);
		
		
		CommonResponse<List<String>> res=new CommonResponse<List<String>>(0,"success",null);
		String loginMsg;
		List<String> list=new ArrayList<String>(); 
		User user2=user1.get();
		String phonenumber=user1.getPhoneNumber();
		
		System.out.println(phonenumber);
		
		if(phonenumber.length()==11) {
			if(user2 != null) {    
				//用户名与数据库匹配，判断密码是否相符
				String password1=user1.getPassword();
				String password2=user2.getPassword();
				if(password1.equals(password2)) {   //登陆成功后服务器要返回用户名
					res.setCode(0);
					String firstname=user2.getFirstName();
					String lastname=user2.getLastName();
					loginMsg="登录成功";
					list.add(loginMsg);
					list.add(firstname);
					list.add(lastname);
					res.setData(list);
				}
				else {
					res.setCode(2);
					loginMsg="登录失败，密码错误";
					list.add(loginMsg);
					res.setData(list);
				}
			}
			else {
				res.setCode(3);
				loginMsg="该手机号未注册";
				list.add(loginMsg);
				res.setData(list);
			}
		}
		else {
			res.setCode(1);
			loginMsg="手机号不符合要求";
			list.add(loginMsg);
			res.setData(list);
		}
		
		//第三步：将结果封装成json格式返回给客户端,但实际网络传输时还是传输json的字符串
		//json只是提供了特定的字符串拼接格式
		String resStr=gson.toJson(res);
		System.out.println("返回报文的json字符串为："+resStr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(resStr);
		pw.flush();
	}

}
