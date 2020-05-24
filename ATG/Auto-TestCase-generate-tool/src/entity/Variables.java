package entity;

/**
 * 
* @ClassName: Variables  
* @Description: TODO(用于保存变量信息)  
* @author 张漾  
* @date 2020年5月14日
 */
public class Variables {
	private String variablesID; 		//变量ID
	private String variablesName;	//变量名称
	private String variablesTypeID;	//变量类型ID
	private String Type;			//变量在需求在位于什么位置{前置条件，输入变量，输出变量}
	public String getVariablesID() {
		return variablesID;
	}
	public void setVariablesID(String variablesID) {
		this.variablesID = variablesID;
	}
	public String getVariablesName() {
		return variablesName;
	}
	public void setVariablesName(String variablesName) {
		this.variablesName = variablesName;
	}
	public String getVariablesTypeID() {
		return variablesTypeID;
	}
	public void setVariablesTypeID(String variablesTypeID) {
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
