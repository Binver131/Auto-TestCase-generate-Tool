package entity;
/**
 * 
* @ClassName: TestCase  
* @Description: TODO(用于保存测试用例数据)  
* @author 张漾  
* @date 2020年5月14日
 */
public class TestCase {
	private int testcaseID;					//测试用例ID
	private String testcaseRequirementID;	//测试用例所对应的需求ID	
	private String testcaseCondition;		//测试用例前置条件的值
	private String testcaseInput;			//测试用例输入变量的值
	private String testcaseOutput;			//测试用例输出变量的值
	private String testcaseEvaluate;		//测试用例所适用的评价准则
	private String testcaseType;			//测试用例类型
	public int getTestcaseID() {
		return testcaseID;
	}
	public void setTestcaseID(int testcaseID) {
		this.testcaseID = testcaseID;
	}
	public String getTestcaseRequirementID() {
		return testcaseRequirementID;
	}
	public void setTestcaseRequirementID(String testcaseRequirementID) {
		this.testcaseRequirementID = testcaseRequirementID;
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
	public String getTestcaseEvaluate() {
		return testcaseEvaluate;
	}
	public void setTestcaseEvaluate(String testcaseEvaluate) {
		this.testcaseEvaluate = testcaseEvaluate;
	}
	public String getTestcaseType() {
		return testcaseType;
	}
	public void setTestcaseType(String testcaseType) {
		this.testcaseType = testcaseType;
	}
	@Override
	public String toString() {
		return "TestCase [testcaseID=" + testcaseID + ", testcaseRequirementID=" + testcaseRequirementID
				+ ", testcaseCondition=" + testcaseCondition + ", testcaseInput=" + testcaseInput + ", testcaseOutput="
				+ testcaseOutput + ", testcaseEvaluate=" + testcaseEvaluate + ", testcaseType=" + testcaseType + "]";
	}
}
