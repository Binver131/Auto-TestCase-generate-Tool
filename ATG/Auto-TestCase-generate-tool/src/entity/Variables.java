package entity;

/**
 * 
* @ClassName: Variables  
* @Description: TODO(���ڱ��������Ϣ)  
* @author ����  
* @date 2020��5��14��
 */
public class Variables {
	private String variablesID; 		//����ID
	private String variablesName;	//��������
	private String variablesTypeID;	//��������ID
	private String Type;			//������������λ��ʲôλ��{ǰ������������������������}
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
	* @Description: TODO(���������ĸ������ʲô���)  
	* @param @return    ����  
	* @return Object    ��������  
	* @throws  
	*/  
	public String getParent() {
		return Type;
	}
	
}
