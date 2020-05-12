package entity;

public class Requirement {
	private String requirementId;
	private String requirementName;
	private String requirementText;
	private String requirementCondition;
	private String requirementInput;
	private String requirementOutput;
	public String getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}
	public String getRequirementName() {
		return requirementName;
	}
	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}
	public String getRequirementText() {
		return requirementText;
	}
	public void setRequirementText(String requirementText) {
		this.requirementText = requirementText;
	}
	public String getRequirementCondition() {
		return requirementCondition;
	}
	public void setRequirementCondition(String requirementCondition) {
		this.requirementCondition = requirementCondition;
	}
	public String getRequirementInput() {
		return requirementInput;
	}
	public void setRequirementInput(String requirementInput) {
		this.requirementInput = requirementInput;
	}
	public String getRequirementOutput() {
		return requirementOutput;
	}
	public void setRequirementOutput(String requirementOutput) {
		this.requirementOutput = requirementOutput;
	}
	@Override
	public String toString() {
		return "Requirement [requirementId=" + requirementId + ", requirementName=" + requirementName
				+ ", requirementText=" + requirementText + ", requirementCondition=" + requirementCondition
				+ ", requirementInput=" + requirementInput + ", requirementOutput=" + requirementOutput + "]";
	}
}
