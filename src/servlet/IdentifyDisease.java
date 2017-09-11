package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.DiseaseKind;

//调用文件，识别疾病
public class IdentifyDisease {
	
	private List<DiseaseKind> diseaseList;
	
	public IdentifyDisease(){
		diseaseList=new ArrayList<DiseaseKind>();
		File file=new File("C:\\Users\\ZTH\\work","diseaseIdentify.py");
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String tempString=null;
			int line=1;
			while((tempString=reader.readLine())!=null) {
				DiseaseKind diseaseKind=new DiseaseKind("未检测","未检测");
				diseaseKind.setDiseaseNo(String.valueOf(line));
				diseaseKind.setDiseaseName(tempString);
				diseaseList.add(diseaseKind);
				line++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DiseaseKind identifyDiseaseKind() {
		Random rand=new Random();
		int i=rand.nextInt(38);
		DiseaseKind diseasekind=diseaseList.get(i);
		return diseasekind;
	}
}
