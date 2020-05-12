package entity;

public class TestCase {
	private int testcaseID;
	private String testcaseRequirementID;
	private String testcaseCondition;
	private String testcaseInput;
	private String testcaseOutput;
	private String testcaseEvaluate;
	private String testcaseType;
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
