package entity;
/**
 * 
* @ClassName: TestCase  
* @Description: TODO(���ڱ��������������)  
* @author ����  
* @date 2020��5��14��
 */
public class TestCase {
	private int testcaseID;					//��������ID
	private String testcaseRequirementID;	//������������Ӧ������ID	
	private String testcaseCondition;		//��������ǰ��������ֵ
	private String testcaseInput;			//�����������������ֵ
	private String testcaseOutput;			//�����������������ֵ
	private String testcaseEvaluate;		//�������������õ�����׼��
	private String testcaseType;			//������������
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
