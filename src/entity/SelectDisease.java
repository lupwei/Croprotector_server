package entity;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class SelectDisease {                    //这个功能暂时删掉了
	private String phonenumber;
	private String diseaseNo;
	
	public SelectDisease() {
		phonenumber=null;
		diseaseNo=null;
	}
	
	public SelectDisease(String phonenumber,String diseaseNO) {
		this.phonenumber=phonenumber;
		this.diseaseNo=diseaseNO;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
	
	public String getDiseaseNo() {
		return diseaseNo;
	}
	
	public void setPhoneNumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}
	
	public void setDiseaseNo(String diseaseNo) {
		this.diseaseNo=diseaseNo;
	}
	
	@Override
	public String toString() {
		return "User [phonenumber=" + phonenumber + ", diseaseNo" + diseaseNo + "]";
	}
	
	public List<SelectDisease> getByPhoneNumber() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.getByPhoneNumber";
		//执行获取对象操作
		List<SelectDisease> lstSelectDisease=sqlSession.selectList(statement, phonenumber);
		sqlSession.close();
		System.out.println(lstSelectDisease);
		return lstSelectDisease;
	}
	
	public List<SelectDisease> getByDiseaseNo(){
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.getByDiseaseNo";
		//执行获取对象操作
		List<SelectDisease> lstSelectDisease=sqlSession.selectList(statement, diseaseNo);
		sqlSession.close();
		System.out.println(lstSelectDisease);
		return lstSelectDisease;
	}
	
	public List<SelectDisease> getAll(){
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.getAllSelectDisease";
		//执行获取对象操作
		List<SelectDisease> lstSelectDisease=sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println(lstSelectDisease);
		return lstSelectDisease;
	}
	
	public void add() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.addSelectDisease";
		//执行添加selectdisease操作
		int retResult=sqlSession.insert(statement, this);
		sqlSession.close();
		System.out.println(retResult);
	}
	
	public void deleteByPhoneNumber() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.deleteByPhoneNumber";
		//执行删除selectdisease操作
		int retResult=sqlSession.delete(statement,phonenumber);
		sqlSession.close();
		System.out.println(retResult);
	}
	
	public void deleteByDiseaseNo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.selectdiseaseMapper.deleteByDiseaseNo";
		//执行删除selectdisease操作
		int retResult=sqlSession.delete(statement,diseaseNo);
		sqlSession.close();
		System.out.println(retResult);
	}
	
	
	//update操作是用add和delete操作完成的，而且要涉及到多个selectdisease对象
	//所以无法封装在SelectDisease类里,请见谅
	//会有专门的servlet用以修改用户对疾病的选择
}
