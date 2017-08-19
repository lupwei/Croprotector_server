package servlet;

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


import dbutil.DBUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		//第一步：获取客户端发来的请求，恢复json格式
		CommonRequest req=new CommonRequest();
		req.setReqstr(request);
		req.setRequestCode();                     //接口号，暂时好像用不上
		req.setRequestParam();
		JSONObject Param=req.getRequestParam();
				
		//第二步：将json化为别的数据结构使用，进行业务处理，生成结果
		String sql=String.format("SELECT * FROM %s WHERE phonenumber='%s'",
				DBUtil.TABLE_USER,Param.getString("phonenumber"));
		System.out.println("查询sql语句为："+sql);
				
		CommonResponse res=new CommonResponse();
		try {
			ResultSet result=DBUtil.query(sql);
			if(result.next()) {
				res.setResMsg("该账号已存在");
			}
			else {
				String sqlInsert =String.format("insert into %s (phonenumber,password) values('%s','%s')",
						DBUtil.TABLE_USER,Param.getString("phonenumber"),Param.getString("password"));
				System.out.println("注册新用户的sql语句："+sqlInsert);
				if(DBUtil.update(sqlInsert)>0) {
					res.setResMsg("注册成功");
				}
				else {
					res.setResMsg("注册失败");
				}
			}
		} catch (SQLException e) {
			res.setResMsg("数据库查询错误");
			e.printStackTrace();
		}
		
		//将结果封装成json格式返回给客户端,但实际网络传输时还是传输json的字符串
		//json只是提供了特定的字符串拼接格式
		String resStr=JSONObject.fromObject(res).toString();
		System.out.println("返回报文的json字符串为："+resStr);
		PrintWriter pw=response.getWriter();
		pw.println(resStr);
		pw.flush();
	}

}
