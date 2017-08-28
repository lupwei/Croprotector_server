package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		CommonRequest req = new CommonRequest();
		req.setReqstr(request);
		String jsonStr = req.getReq();
        User user_req = new User();
        Gson gson = new Gson();
        user_req = gson.fromJson(jsonStr,User.class);
		
		
        CommonResponse res = new CommonResponse();
        
        User user_db = user_req.get();

        // Find user in database, check if the new password is valid
		if(user_db != null) {
            String newPassword = user_req.getPassword();
            // password is supposed to check both at server and at client
            // if(isValid(newPassword));
			user_db.setPassword(newPassword);
            int res = user_db.update();
            if(res > 0) {
                res.setCode(0)
                res.setResMsg("重置密码成功");
            }
            else {
                res.setCode(-1)
                res.setResMsg("重置密码失败");
            }
			
        }
        // User doesn't exist
		else {
			res.setResMsg("该手机号未注册");
        }
        
		
		//第三步：将结果封装成json格式返回给客户端,但实际网络传输时还是传输json的字符串
		//json只是提供了特定的字符串拼接格式
		String resStr = gson.toJson(res);
		System.out.println("返回报文的json字符串为：" + resStr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(resStr);
		pw.flush();
	}
}
