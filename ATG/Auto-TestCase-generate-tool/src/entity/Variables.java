package entity;

import java.util.List;

public class Variables {
	private int variablesID;
	private String variablesName;
	private int variablesTypeID;
	private String Type;
	public int getVariablesID() {
		return variablesID;
	}
	public void setVariablesID(int variablesID) {
		this.variablesID = variablesID;
	}
	public String getVariablesName() {
		return variablesName;
	}
	public void setVariablesName(String variablesName) {
		this.variablesName = variablesName;
	}
	public int getVariablesTypeID() {
		return variablesTypeID;
	}
	public void setVariablesTypeID(int variablesTypeID) {
		this.variablesTypeID = variablesTypeID;
	}
	@Override
	public String toString() {
		return "Variables [variablesID=" + variablesID + ", variablesName=" + variablesName + ", variablesTypeID="
				+ variablesTypeID + "]";
	}
	/**  
	* @Title: getParent  
	* @Description: TODO(���������ĸ������ʲô���)  
	* @param @return    ����  
	* @return Object    ��������  
	* @throws  
	*/  
	public String getParent() {
		return Type;
	}
	
}
