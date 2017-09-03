package entity;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

public class DiseaseInfo {
	private String infoNo;
	private String diseaseNo;
	private DiseaseKind diseasekind;
	private String picture;                     //图片的url
	private Date time;
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
		this.diseasekind=diseasekind;
	}
	
	public void setPicture(String picture) {
		this.picture=picture;
	}
	
	public void setTime(Date time) {
		this.time=time;
	}
	
	public void setLongitude(double longitude) {
		this.longitude=longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude=latitude;
	}
	
	public String getInfoNo() {
		return infoNo;
	}
	
	public String getDiseaseNo() {
		return diseaseNo;
	}
	
	public DiseaseKind getDiseaseKind() {
		return diseasekind;
	}
	
	public String getPicture() {
		return picture;
	}
	
	public Date getTime() {
		return time;
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
