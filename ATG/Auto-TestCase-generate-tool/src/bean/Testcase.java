package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Testcase {
	private int testcaseID;
	private int requirementID;
	private String testcaseCondition;//���Ҳ�ò��� ����mybatisҪ��һ��
	private String testcaseInput;
	private String testcaseOutput;
	private HashMap<Variable,String>testcaseConditionMap; //<�������ƣ�����ȡֵ>
	private HashMap<Variable,String>testcaseInputMap;
	private HashMap<Variable,String>testcaseOutputMap;
	private String testcaseType;
	private String testcaseEvaluate;
	
	public Testcase() {
		testcaseConditionMap = new LinkedHashMap<Variable, String>();
		testcaseInputMap = new LinkedHashMap<Variable, String>();
		testcaseOutputMap = new LinkedHashMap<Variable, String>();
	}
	
	
	public int getTestcaseID() {
		return testcaseID;
	}
	public void setTestcaseID(int testcaseID) {
		this.testcaseID = testcaseID;
	}
	public int getRequirementID() {
		return requirementID;
	}
	public void setRequirementID(int requirementID) {
		this.requirementID = requirementID;
	}
	public String getTestcaseCondition() {
		return testcaseCondition;
	}
	public void setTestcaseCondition(String testcaseCondition) {
		this.testcaseCondition = testcaseCondition;
	}
	public String getTestcaseInput() {
		return testcaseInput;
	}
	public void setTestcaseInput(String testcaseInput) {
		this.testcaseInput = testcaseInput;
	}
	public String getTestcaseOutput() {
		return testcaseOutput;
	}
	public void setTestcaseOutput(String testcaseOutput) {
		this.testcaseOutput = testcaseOutput;
	}
	public HashMap<Variable, String> getTestcaseConditionMap() {
		return testcaseConditionMap;
	}
	public void setTestcaseConditionMap(HashMap<Variable, String> testcaseConditionMap) {
		this.testcaseConditionMap = testcaseConditionMap;
	}
	public HashMap<Variable, String> getTestcaseInputMap() {
		return testcaseInputMap;
	}
	public void setTestcaseInputMap(HashMap<Variable, String> testcaseInputMap) {
		this.testcaseInputMap = testcaseInputMap;
	}
	public HashMap<Variable, String> getTestcaseOutputMap() {
		return testcaseOutputMap;
	}
	public void setTestcaseOutputMap(HashMap<Variable, String> testcaseOutputMap) {
		this.testcaseOutputMap = testcaseOutputMap;
	}
	public String getTestcaseType() {
		return testcaseType;
	}
	public void setTestcaseType(String testcaseType) {
		this.testcaseType = testcaseType;
	}
	public String getTestcaseEvaluate() {
		return testcaseEvaluate;
	}
	public void setTestcaseEvaluate(String testcaseEvaluate) {
		this.testcaseEvaluate = testcaseEvaluate;
	}
//	@Override
//	public String toString() {
//		return "Testcase [testcaseID=" + testcaseID + ", requirementID=" + requirementID + ", testcaseCondition="
//				+ testcaseCondition + ", testcaseInput=" + testcaseInput + ", testcaseOutput=" + testcaseOutput
//				+ ", testcaseConditionMap=" + testcaseConditionMap + ", testcaseInputMap=" + testcaseInputMap
//				+ ", testcaseOutputMap=" + testcaseOutputMap + ", testcaseType=" + testcaseType + ", testcaseEvaluate="
//				+ testcaseEvaluate + "]";
//	}
	
	//////////////////////
	//�����д�˼�� ������ֵ��ת����String��֪���Բ���
	public String[] conditionVal() {
		return this.testcaseCondition.split(",");
	}
	public String[] inputVal() {
		return this.testcaseInput.split(",");
	}
	public String[] outputVal() {
		return this.testcaseOutput.split(",");
	}
	/////////
	//������ʱ�Ѳ�����������������<����id������ֵ>�Ľṹ����������<������������ֵ>�Ľṹ��<����������ֵ>�Ľṹ��������ô���Լ�����
	public void addTestcaseConditionMap(List<Variable>key,String []value) {
		for(int i=0,k=value.length;i<k;i++) {
			this.testcaseConditionMap.put(key.get(i), value[i]);
			//System.out.println(key.get(i).toString()+value[i]);
		}
	}
	public void addTestcaseInputMap(List<Variable>key,String []value) {
		for(int i=0,k=value.length;i<k;i++) {
			this.testcaseInputMap.put(key.get(i), value[i]);
			//System.out.println(key.get(i).toString()+value[i]);
		}
	}
	public void addTestcaseOutputMap(List<Variable>key,String []value) {
		for(int i=0,k=value.length;i<k;i++) {
			this.testcaseOutputMap.put(key.get(i), value[i]);
			//System.out.println(key.get(i).toString()+value[i]);
		}
	}
	
}
