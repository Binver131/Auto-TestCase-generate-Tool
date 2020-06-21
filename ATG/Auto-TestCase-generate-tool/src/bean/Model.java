package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;

public class Model {
	private DataBase database;
	private int modelID;
	private String modelIdentifier;
	private String modelName;
	private String modelContent;
	private String modelClass;
	private HashMap<String,RowRequirement> rowRequirementMap;
	private HashMap<String,Type> typeMap;
	private HashMap<String,Variable> variableMap;
	
	public Model() {
		rowRequirementMap = new LinkedHashMap<>();
		typeMap = new LinkedHashMap<String, Type>();
		variableMap = new LinkedHashMap<String,Variable>();
	}
	
	public DataBase getParent() {
		return database;
	}
	public void setParent(DataBase parent) {
		this.database = parent;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
	public String getModelIdentifier() {
		return modelIdentifier;
	}
	public void setModelIdentifier(String modelIdentifier) {
		this.modelIdentifier = modelIdentifier;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelContent() {
		return modelContent;
	}
	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}
	public String getModelClass() {
		return modelClass;
	}
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	public HashMap<String, RowRequirement> getChildren() {
		return rowRequirementMap;
	}
	public void setChildren(HashMap<String, RowRequirement> rowRequirementMap) {
		this.rowRequirementMap = rowRequirementMap;
	}
	public HashMap<String, Type> getTypeMap() {
		return typeMap;
	}
	public void setTypeMap(HashMap<String, Type> typeMap) {
		this.typeMap = typeMap;
	}
	public HashMap<String, Variable> getVariableMap() {
		return variableMap;
	}
	public void setVariableMap(HashMap<String, Variable> variableMap) {
		this.variableMap = variableMap;
	}
	public String toStringTest() {
		return "Model [parent=" + database + ", modelID=" + modelID + ", modelIdentifier=" + modelIdentifier
				+ ", modelName=" + modelName + ", modelContent=" + modelContent + ", modelClass=" + modelClass
				+ ", children=" + rowRequirementMap + ", typeMap=" + typeMap + ", variableMap=" + variableMap + "]";
	}
	
	
	///
	public void addTypeMap(Type type) {
		this.typeMap.put(type.getTypeName(), type);
	}
	public void addVariableMap(Variable variable) {
		this.variableMap.put(variable.getVariableName(),variable);
	}
	public void addRowRequirementMap(RowRequirement rowRequirement) {
		this.rowRequirementMap.put(rowRequirement.getRowRequirementIdentifier(),rowRequirement);
		//rowRequirement.setParent(this);
	}
	public RowRequirement[] getRowRequirement() {
		return (RowRequirement[]) rowRequirementMap.values().toArray(new RowRequirement[rowRequirementMap.size()]);
	}
	public boolean hasChildren() {
		return rowRequirementMap.size()>0;
	}
	public String toString() {
		return getModelName();
	}
}
