package bean;

import java.util.HashMap;

public class Type {
	private int typeID;   //������������NO
	private String typeName; //��������
	private String typeRowName;//����ԭʼ����
	private int typeSize;  //���ͳ���
	private String typeRange; //���ͷ�Χ
	private int modelID; //���ڵ�ģ��ID
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
