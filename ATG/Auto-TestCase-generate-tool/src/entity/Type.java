package entity;

/**
 * 
* @ClassName: Type  
* @Description: TODO(���ڱ�����������)  
* @author Binver131  
* @date 2020��5��14��
 */
public class Type {
	private int typeID;			//����ID��
	private String typename;	//��������
	private String typerange;	//������
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
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
		return "Type [typeID=" + typeID + ", typename=" + typename + ", typerange=" + typerange + "]";
	}
}
