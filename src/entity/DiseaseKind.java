package entity;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DiseaseKind {
	private String diseaseNo;
	private String diseaseName;
	
	
	public String getDiseaseNo() {
		return diseaseNo;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	
	public void setDiseaseNo(String diseaseNo) {
		this.diseaseNo=diseaseNo;
	}
	
	public void setDiseaseName(String diseaseName) {
		this.diseaseName=diseaseName;
	}
	
	@Override
	public String toString() {
		return "User [diseaseNo=" + diseaseNo + ", diseaseName=" + diseaseName + "]";
	}
	
	public DiseaseKind getByDiseaseNo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.getByDiseaseNo";
		//执行获取diseasekind对象的操作
		DiseaseKind diseasekind=sqlSession.selectOne(statement,diseaseNo);
		sqlSession.close();
		System.out.println("获取一个特定病虫害种类的操作结果为："+diseasekind);
		return diseasekind;
	}
	
	public DiseaseKind getByDiseaseName() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.getByDiseaseName";
		//执行获取diseasekind对象的操作
		DiseaseKind diseasekind=sqlSession.selectOne(statement, diseaseName);
		sqlSession.close();
		System.out.println("获取一个特定病虫害种类的操作结果为："+diseasekind);
		return diseasekind;
	}
	
	public int addDiseaseKind() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.addDiseaseKind";
		//执行添加diseasekind对象的操作
		int retResult=sqlSession.insert(statement, this);
		sqlSession.close();
		System.out.println("创建新病虫害种类的操作结果为："+retResult);
		return retResult;
	}
	
	public int deleteByDiseaseNo() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.deleteByDiseaseNo";
		//执行删除diseasekind对象的操作
		int retResult=sqlSession.delete(statement,diseaseNo);
		sqlSession.close();
		System.out.println("删除病虫害种类的操作结果为："+retResult);
		return retResult;
	}
	
	public int deleteByDiseaseName() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.deleteByDiseaseName";
		//执行删除diseasekind对象的操作
		int retResult=sqlSession.delete(statement,diseaseName);
		sqlSession.close();
		System.out.println("删除病虫害种类的操作结果为："+retResult);
		return retResult;
	}
	
	public int changeDiseaseName() {
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.changeDiseaseName";
		//执行修改病虫害名称的操作
		int retResult=sqlSession.update(statement,this);
		sqlSession.close();
		System.out.println("修改病虫害名称的操作结果为："+retResult);
		return retResult;
	}
	
	public List<DiseaseKind> getAllDiseaseKind(){
		SqlSession sqlSession=MyBatisUtil.getSqlSession(true);
		//映射sql的标识字符串
		String statement="mapping.diseasekindMapper.getAllDiseaseKind";
		//执行获取全部病虫害种类的操作
		List<DiseaseKind> lstDiseaseKind=sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println("获取全部病虫害种类的操作结果为："+lstDiseaseKind);
		return lstDiseaseKind;
	}
	
	
}
