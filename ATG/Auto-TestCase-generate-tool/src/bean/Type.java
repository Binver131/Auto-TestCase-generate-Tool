package bean;

import java.util.HashMap;

public class Type {
	private int typeID;   //数字自增主键NO
	private String typeName; //类型名称
	private String typeRowName;//类型原始名称
	private int typeSize;  //类型长度
	private String typeRange; //类型范围
	private int modelID; //属于的模型ID
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeRowName() {
		return typeRowName;
	}
	public void setTypeRowName(String typeRowName) {
		this.typeRowName = typeRowName;
	}
	public int getTypeSize() {
		return typeSize;
	}
	public void setTypeSize(int typeSize) {
		this.typeSize = typeSize;
	}
	public String getTypeRange() {
		return typeRange;
	}
	public void setTypeRange(String typeRange) {
		this.typeRange = typeRange;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
//	@Override
//	public String toString() {
//		return "Type [typeID=" + typeID + ", typeName=" + typeName + ", typeRowName=" + typeRowName + ", typeSize="
//				+ typeSize + ", typeRange=" + typeRange + ", modelID=" + modelID + "]";
//	}
	
}
