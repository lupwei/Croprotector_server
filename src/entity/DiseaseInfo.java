package entity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

public class DiseaseInfo {
	//infoNO，时间，手机号，经纬度都是在客户端已经赋好值不会在服务器端改动的东西
	
	private String infoNo;
	private String diseaseNo;
	private DiseaseKind diseaseKind;
	private String picture;                     //与客户端通信的的时候是Base64格式的字符串，存入、取出数据库的时候是图片的url
	private String infoTime;
	private double longitude;                   //经度
	private double latitude;                    //纬度
	private String phonenumber;
	
	
	
	public void setInfoNo(String infoNo) {
		this.infoNo=infoNo;
	}
	
	public void setDiseaseNo(String diseaseNo) {
		this.diseaseNo=diseaseNo;
	}
	
	public void setDiseaseKind(DiseaseKind diseasekind) {
		this.diseaseKind=diseasekind;
	}
	
	public void setPicture(String picture) {
		this.picture=picture;
	}
	
	public void setInfoTime(String time) {
		this.infoTime=time;
	}
	
	public void setLongitude(double longitude) {
		this.longitude=longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	
	public void setPhoneNumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}
	
	public String getInfoNo() {
		return infoNo;
	}
	
	public String getDiseaseNo() {
		return diseaseNo;
	}
	
	public DiseaseKind getDiseaseKind() {
		return diseaseKind;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public String getInfoTime() {
		return infoTime;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
	
	@Override
	public String toString() {
		return "DiseaseInfo [infoNo="+infoNo+",diseaseNO="+diseaseNo+",phonenumber="+phonenumber;
	}
	
	public DiseaseInfo getByInfoNo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.getByInfoNo";
		//执行获取diseaseinfo的操作
		DiseaseInfo diseaseinfo=sqlSession.selectOne(statement,infoNo);
		sqlSession.close();
		System.out.println("获取一个特定diseaseinfo的操作结果为："+diseaseinfo);
		return diseaseinfo;
	}
	
	public List<DiseaseInfo> getByTime(@Param("time1")Date time1,@Param("time2")Date time2,@Param("phonenumber")String phonenumber){
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.getByTime";
		//执行获取diseaseinfo的操作
		List<DiseaseInfo> lstDiseaseInfo=sqlSession.selectList(statement);
	    sqlSession.close();
	    return lstDiseaseInfo;
	}
	
	public List<DiseaseInfo> getByPhoneNumber(){
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.getByPhonenumber";
		//执行获取diseaseinfo的操作
		List<DiseaseInfo> lstDiseaseInfo=sqlSession.selectList(statement,phonenumber);
		sqlSession.close();
		return lstDiseaseInfo;
	}
	
	public int addDiseaseInfo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.addDiseaseInfo";
		//执行添加diseaseinfo的操作
		int retResult=sqlSession.insert(statement,this);
		sqlSession.close();
		return retResult;
	}
	
	public int deleteByPhonenumber() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.deleteByPhonenumber";
		//执行删除diseaseinfo的操作
		int retResult=sqlSession.delete(statement,phonenumber);
		sqlSession.close();
		return retResult;
	}
	
	public int deleteByInfoNo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseaseinfoMapper.deleteByInfoNo";
		//执行删除diseaseinfo的操作
		int retResult=sqlSession.delete(statement, infoNo);
		sqlSession.close();
		return retResult;
	}
}
