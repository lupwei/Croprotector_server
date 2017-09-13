package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.DiseaseInfo;
import entity.DiseaseKind;

/**
 * Servlet implementation class TestPictureServlet
 * 接收未检测的图片和数据，返回检测结果
 */
@WebServlet("/TestPictureServlet")
public class TestPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPictureServlet() {
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
		CommonRequest req=new CommonRequest();
		req.setReqstr(request);
		String jsonStr=req.getReq();
		DiseaseInfo diseaseinfo1=new DiseaseInfo();
		DiseaseKind diseaseKind1=new DiseaseKind("未检测","未检测");
		Gson gson=new Gson();
		diseaseinfo1=gson.fromJson(jsonStr, DiseaseInfo.class);
		
		/*此处应有发送数据给训练模型进程
		 *训练模型进程返回检测结果到TestPictureServlet
		 *然后再存进数据库中，但是此处因为还没有训练模型进程所以就先直接存入数据库中了
		 * */
		
		CommonResponse<DiseaseKind> res=new CommonResponse<DiseaseKind>(0,"success",null);
		
		//处理图片，发给模型，模型返回结果,但是此处没有模型
		String base64Str=diseaseinfo1.getPicture();
		//指定图片存储路径       images\手机号\日期加时间.jpg
		String sinfoTime=diseaseinfo1.getInfoTime();
		String fileTime=sinfoTime.replace(':', '-');
		String phonenumber=diseaseinfo1.getPhoneNumber();
		String url="C:\\Users\\ZTH\\work\\images\\"+phonenumber+"\\"+fileTime+".jpg";
		File dir=new File("C:\\Users\\ZTH\\work\\images\\"+phonenumber);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//更改数据项，存入数据库
		PictureClass.Base64ToImage(base64Str, url);
		IdentifyDisease id=new IdentifyDisease();
		diseaseKind1=id.identifyDiseaseKind();
		diseaseinfo1.setDiseaseKind(diseaseKind1);
		diseaseinfo1.setDiseaseNo(diseaseKind1.getDiseaseNo());
		diseaseinfo1.setPicture(url);
		int result=diseaseinfo1.addDiseaseInfo();          //数据库操作
		
		//应该还有一个没有识别出结果的处理情况，但是此处目前并没有模型
		res.setCode(0);
		res.setData(diseaseKind1);
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
