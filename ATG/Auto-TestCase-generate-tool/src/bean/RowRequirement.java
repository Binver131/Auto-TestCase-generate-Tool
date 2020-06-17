package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RowRequirement {
	private int rowRequirementID;
	private String rowRequirementIdentifier;
	private String rowRequirementName;
	private String rowRequirementContent;
	private int modelID;
	private Model model;
	private HashMap<String,Requirement> requirementMap;
	
	public RowRequirement() {
		requirementMap = new LinkedHashMap<>();
	}
	
	public int getRowRequirementID() {
		return rowRequirementID;
	}
	public void setRowRequirementID(int rowRequirementID) {
		this.rowRequirementID = rowRequirementID;
	}
	public String getRowRequirementIdentifier() {
		return rowRequirementIdentifier;
	}
	public void setRowRequirementIdentifier(String rowRequirementIdentifier) {
		this.rowRequirementIdentifier = rowRequirementIdentifier;
	}
	public String getRowRequirementName() {
		return rowRequirementName;
	}
	public void setRowRequirementName(String rowRequirementName) {
		this.rowRequirementName = rowRequirementName;
	}
	public String getRowRequirementContent() {
		return rowRequirementContent;
	}
	public void setRowRequirementContent(String rowRequirementContent) {
		this.rowRequirementContent = rowRequirementContent;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
	public Model getParent() {
		return model;
	}
	public void setParent(Model model) {
		this.model = model;
		//model.addRowRequirementMap(this);
	}
	public HashMap<String, Requirement> getChildren() {
		return requirementMap;
	}
	public void setChildren(HashMap<String, Requirement> requirementMap) {
		this.requirementMap = requirementMap;
	}
	public String toStringTest() {
		return "RowRequirement [rowRequirementID=" + rowRequirementID + ", rowRequirementIdentifier="
				+ rowRequirementIdentifier + ", rowRequirementName=" + rowRequirementName + ", rowRequirementContent="
				+ rowRequirementContent + ", modelID=" + modelID + ", parent=" + model + ", children=" + requirementMap
				+ "]";
	}
	
	//////
	public void addRequirementMap(Requirement requirement) {
		this.requirementMap.put(requirement.getRequirementIdentifier(),requirement);
	}
	
	public Requirement[] getRequirement() {
		return (Requirement[]) requirementMap.values().toArray(new Requirement[requirementMap.size()]);
	}
	public boolean hasChildren() {
		return requirementMap.size()>0;
	}
	public String toString() {
		return getRowRequirementIdentifier();
	}
}
