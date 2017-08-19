package dbutil;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class DBUtil {
	public static final String TABLE_USER="user";
	public static final String TABLE_DISEASEKIND = "diseasekind";
	public static final String TABLE_DISEASEINFO="diseaseinfo";
	
	public static Connection myConnection;
	
	public static Connection getConnect() {
		if(myConnection==null) {
			String url="jdbc:mysql://localhost:3306/croprotector";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				myConnection = (Connection) DriverManager.getConnection(url, "root", "961030");
				System.out.println("创建数据库连接");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				 System.out.println("SQLException: " + e.getMessage());  
		         System.out.println("SQLState: " + e.getSQLState());  
		         System.out.println("VendorError: " + e.getErrorCode());  
			}
		}
		return myConnection;
	}
	
	
	//查询操作
	public static ResultSet query(String querySql) throws SQLException {
		Statement statement=(Statement)getConnect().createStatement();
		return statement.executeQuery(querySql);
	}
	
	//插入、更新、删除操作
	public static int update(String updateSql) throws SQLException {
		Statement statement=(Statement)getConnect().createStatement();
		return statement.executeUpdate(updateSql);
	}
	
	//断开数据库连接
	public static void disConnect() {
		if(myConnection!=null) {
			try {
				myConnection.close();
				myConnection=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

