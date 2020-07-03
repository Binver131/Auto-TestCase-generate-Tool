package bean;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jdbc.ConnectHelp;

public class Requirement {
	private int requirementID;
	private String requirementIdentifier;
	private String requirementContent;
	private String requirementName;
	private String requirementCondition;  
	private String requirementInput;
	private String requirementOutput;
	private HashMap<Integer,Testcase> testcaseMap;//<测试向量id，测试向量>  
	private int modelID;
	private int rowRequirementID;
	private RowRequirement rowRequirement;
	
	public RowRequirement getRowRequirement() {
		return rowRequirement;
	}

	public void setRowRequirement(RowRequirement rowRequirement) {
		this.rowRequirement = rowRequirement;
	}

	public Requirement() {
		testcaseMap = new LinkedHashMap<>();
	}
	
	public int getRequirementID() {
		return requirementID;
	}
	public void setRequirementID(int requirementID) {
		this.requirementID = requirementID;
	}
	public String getRequirementIdentifier() {
		return requirementIdentifier;
	}
	public void setRequirementIdentifier(String requirementIdentifier) {
		this.requirementIdentifier = requirementIdentifier;
	}
	public String getRequirementContent() {
		return requirementContent;
	}
	public void setRequirementContent(String requirementContent) {
		this.requirementContent = requirementContent;
	}
	public String getRequirementName() {
		return requirementName;
	}
	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
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
	public HashMap<Integer, Testcase> getTestcaseMap() {
		return testcaseMap;
	}
	public void setTestcaseMap(HashMap<Integer, Testcase> testcaseMap) {
		this.testcaseMap = testcaseMap;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
	public int getRowRequirementID() {
		return rowRequirementID;
	}
	public void setRowRequirementID(int rowRequirementID) {
		this.rowRequirementID = rowRequirementID;
	}
	public RowRequirement getParent() {
		return rowRequirement;
	}
	public void setParent(RowRequirement rowRequirement) {
		this.rowRequirement = rowRequirement;
	}
	public String toStringTest() {
		return "Requirement [requirementID=" + requirementID + ", requirementIdentifier=" + requirementIdentifier
				+ ", requirementContent=" + requirementContent + ", requirementName=" + requirementName
				+ ", requirementCondition=" + requirementCondition + ", requirementInput=" + requirementInput
				+ ", requirementOutput=" + requirementOutput + ", testcaseMap=" + testcaseMap + ", modelID=" + modelID
				+ ", rowRequirementID=" + rowRequirementID + ", parent=" + rowRequirement + "]";
	}	
	
	
	/////////////
	public void addTestcaseMap(Testcase testcase) {
		this.testcaseMap.put(testcase.getTestcaseID(),testcase);
	}
	public List<Variable> conditionVars(){
		 List<Variable> variables = new ArrayList<Variable>();
		 for(String varID:this.requirementCondition.split(",")) {
			 for(Variable var:this.getParent().getParent().getVariableMap().values()) {
				 if(Integer.parseInt(varID)==var.getVariableID()) {
					 variables.add(var);
				 }
			 }
		 }
		 return variables;
	}
	
	
	public List<Variable> inputVars(){
		 List<Variable> variables = new ArrayList<Variable>();
		 for(String varID:this.requirementInput.split(",")) {
			 for(Variable var:this.getParent().getParent().getVariableMap().values()) {
				 if(Integer.parseInt(varID)==var.getVariableID()) {
					 variables.add(var);
				 }
			 }
		 }
		 return variables;
	}
	public List<Variable> outputVars(){
		 List<Variable> variables = new ArrayList<Variable>();
		 for(String varID:this.requirementOutput.split(",")) {
			 for(Variable var:this.getParent().getParent().getVariableMap().values()) {
				 if(Integer.parseInt(varID)==var.getVariableID()) {
					 variables.add(var);
				 }
			 }
		 }
		 return variables;
	}
	public String toString() {
		return getRequirementIdentifier();
	}
	
	public Boolean hasVars(int type) {
		Boolean result = false;
		switch (type) {
		case 1:
			result = conditionVars().isEmpty();
			break;
		case 2:
			result = inputVars().isEmpty();
			break;
		case 3:
			result = outputVars().isEmpty();
			break;
		default:
			break;
		}
		
		return result;
	}
}
