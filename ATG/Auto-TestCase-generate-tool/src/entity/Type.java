package entity;

/**
 * 
* @ClassName: Type  
* @Description: TODO(���ڱ�����������)  
* @author Binver131  
* @date 2020��5��14��
 */
public class Type {
	private String typeID;			//����ID��
	private String typename;	//��������
	private String typerange;	//������
	private String modelID;
	private String sizeString;
	private String basetypename;   //���ͻ������͵����� 
	
	
	public String getBasetypename() {
		return basetypename;
	}
	public void setBasetypename(String basetypename) {
		this.basetypename = basetypename;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTyperange() {
		return typerange;
	}
	public void setTyperange(String typerange) {
		this.typerange = typerange;
	}
	@Override
	public String toString() {
		return "Type [typeID="  + ", typename=" + typename + ", typerange=" + typerange + "]";
	}
	
	public String getSizeString() {
		return sizeString;
	}
	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}
	/**
	 * @return the typeID
	 */
	public String getTypeID() {
		return typeID;
	}
	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	/**
	 * @return the modelID
	 */
	public String getModelID() {
		return modelID;
	}
	/**
	 * @param modelID the modelID to set
	 */
	public void setModelID(String modelID) {
		this.modelID = modelID;
	}
}