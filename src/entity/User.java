package entity;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class User {
	private String phonenumber;
	private String password;
	private String firstname;         //中文的名
	private String lastname;         //中文的姓
	
	
	public User(String phonenumber,String password,String firstname,String lastname) {
		this.phonenumber=phonenumber;
		this.password=password;
		this.firstname=firstname;
		this.lastname=lastname;
	}
	
	public User() {
		phonenumber=null;
		password=null;
		lastname=null;
		firstname=null;
		lastname=null;
	}

	public String getPhoneNumber() {
		return phonenumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public void setPhoneNumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setFirstName(String firstname) {
		this.firstname=firstname;
	}
	
	public void setLastName(String lastname) {
		this.lastname=lastname;
	}
	
	@Override
	public String toString() {
		return "User [phonenumber=" + phonenumber + ", password=" + password + "]";
	}
	
	public int add() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.createUser";
		//执行插入操作
		int retResult=sqlSession.insert(statement,this);
		sqlSession.close();
		System.out.println("创建用户的操作结果为："+retResult);
		return retResult;
	}
	
	public int changePassword() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.changePassword";
		//执行修改密码操作
		int retResult=sqlSession.update(statement,this);
		sqlSession.close();
		System.out.println("修改密码的操作结果为："+retResult);
		return retResult;
	}
	
	public int delete() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.deleteUser";
		//执行删除用户操作
		int retResult=sqlSession.delete(statement, phonenumber);
		sqlSession.close();
		System.out.println("删除用户的操作结果为："+retResult);
		return retResult;
	}
	
	public List<User> getAll() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.getAllUser";
		//执行获取全部用户操作
		List<User> lstUsers=sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println("获取全部用户的操作结果为："+lstUsers);
		return lstUsers;
	}
	
	public User get() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.getUser";
		//执行获取用户操作
		User user=sqlSession.selectOne(statement, phonenumber);
		sqlSession.close();
		System.out.println("获取一个特定用户的操作结果为："+user);
		return user;
	}
	
	public int changeFirstName() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.changeFirstName";
		//执行修改firstname操作
		int retResult=sqlSession.update(statement,this);
		sqlSession.close();
		System.out.println("修改firstname的操作结果为："+retResult);
		return retResult;
	}
	
	public int changeLastName() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.userMapper.changeLastName";
		//执行修改lastname操作
		int retResult=sqlSession.update(statement, this);
		sqlSession.close();
		System.out.println("修改lastname的操作结果为："+retResult);
		return retResult;
	}
}
