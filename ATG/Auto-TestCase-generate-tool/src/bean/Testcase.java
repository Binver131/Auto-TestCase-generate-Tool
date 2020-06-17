package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Testcase {
	private int testcaseID;
	private int requirementID;
	private String testcaseCondition;//这个也用不上 但是mybatis要用一下
	private String testcaseInput;
	private String testcaseOutput;
	private HashMap<Variable,String>testcaseConditionMap; //<变量名称，变量取值>
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
	//这里有待思考 把所有值都转换成String不知道对不对
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
	//这里暂时把测试用例向量调整成<变量id，变量值>的结构，还可以是<变量名，变量值>的结构和<变量，变量值>的结构，后面怎么用自己调整
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
