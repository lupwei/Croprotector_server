package entity;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DiseaseKind {
	private String diseaseNo;
	private String diseaseName;
	
	public DiseaseKind() {
		
	}
	
	public DiseaseKind(String diseaseNo,String diseaseName) {
		this.diseaseNo=diseaseNo;
		this.diseaseName=diseaseName;
	}
	
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
	
	
}
