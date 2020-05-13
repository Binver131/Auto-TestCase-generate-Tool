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
	* @Description: TODO(变量处于哪个需求的什么类别)  
	* @param @return    参数  
	* @return Object    返回类型  
	* @throws  
	*/  
	public String getParent() {
		return Type;
	}
	
}
