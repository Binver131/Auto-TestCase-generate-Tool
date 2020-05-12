package entity;

import java.util.List;

public class NewTestCase {
	private int testcaseID;
	private String testcaseRequirementID;
//	private List<String> testcaseCondition;
//	private List<String> testcaseInput;
//	private List<String> testcaseOutput;
	private List<String> testcase;
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
	
	public List<String> getTestcase() {
		return testcase;
	}
	public void setTestcase(List<String> testcase) {
		this.testcase = testcase;
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
		return "NewTestCase [testcaseID=" + testcaseID + ", testcaseRequirementID=" + testcaseRequirementID
				+ ", testcase=" + testcase + ", testcaseEvaluate=" + testcaseEvaluate + ", testcaseType=" + testcaseType
				+ "]";
	}

	
}
