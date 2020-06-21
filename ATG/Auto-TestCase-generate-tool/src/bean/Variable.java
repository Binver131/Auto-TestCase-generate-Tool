package bean;

public class Variable {
	private int variableID;
	private String variableName;
	private int typeID;//这个不需要但是mybatis要用一下
	private Type variableType;
	private int modelID;
	
	
	public int getVariableID() {
		return variableID;
	}
	public void setVariableID(int variableID) {
		this.variableID = variableID;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public Type getVariableType() {
		return variableType;
	}
	public void setVariableType(Type variableType) {
		this.variableType = variableType;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
//	@Override
//	public String toString() {
//		return "Variable [variableID=" + variableID + ", variableName=" + variableName + ", typeID=" + typeID
//				+ ", variableType=" + variableType + ", modelID=" + modelID + "]";
//	}
	@Override
	public String toString() {
		return "Variable [variableName=" + variableName + "]";
	}
}
