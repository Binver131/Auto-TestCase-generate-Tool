package entity;

/**
 * 
* @ClassName: Type  
* @Description: TODO(用于保存类型数据)  
* @author Binver131  
* @date 2020年5月14日
 */
public class Type {
	private String typeID;			//类型ID号
	private String typename;	//类型名称
	private String typerange;	//类型名
	private String modelID;
	private String sizeString;
	private String baseTypeName;   //类型基本类型的名称 
	
	
	public String getBasetypename() {
		return baseTypeName;
	}
	public void setBasetypename(String basetypename) {
		this.baseTypeName = basetypename;
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
