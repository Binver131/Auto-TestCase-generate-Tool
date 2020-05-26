/**  
* @Title: Model.java  
* @Package entity  
* @Description: TODO(��һ�仰�������ļ���ʲô)  
* @author Binver131  
* @date 2020��5��12��  
* @version V1.0  
*/  
package entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**  
 * @ClassName: Model  
 * @Description: TODO(��������ģ��������Ϣ)  
 * @author Binver131  
 * @date 2020��5��12��    
 */
public class Model {
	private DataBase parent;
	private String dbId;				//���ݿ��е�����
	private String ID;
	private String name;
	private String modelClass;			//����ģ�͵ȼ�
	private String text;
	private List<RowRequirement> children;//�����
	private Map<String,Type> typeMap;
	private Map<String,Variables> variablesMap;
	
	public void addType(Type type) {
		typeMap.put(type.getTypeID(),type);
	}
	
	public Type getType(String id) {
		return typeMap.get(id);
	}
	
	
	public Map<String, Type> getTypeMap() {
		return typeMap;
	}

	public Map<String, Variables> getVariablesMap() {
		return variablesMap;
	}

	public void addVariable(Variables variable) {
		variablesMap.put(variable.getVariablesID(),variable);
	}
	
	public Variables getVariable(String id) {
		return variablesMap.get(id);
	}
	
	
	public Model(String ID) {
		this.ID = ID;
		children = new ArrayList<>();
		typeMap = new HashMap<String, Type>();
		variablesMap = new HashMap<String,Variables>();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addChild(RowRequirement child) {
		children.add(child);
		child.setParent(this);
	}
	public void removeChild(RowRequirement child) {
		children.remove(child);
		child.setParent(null);
	}
	public RowRequirement[] getChildren() {
		return (RowRequirement[]) children.toArray(new RowRequirement[children.size()]);
	}
	public boolean hasChildren() {
		return children.size()>0;
	}
	public void setParent(DataBase database) {
		this.parent = database;
	}
	public DataBase getParent() {
		return parent;
	}
	public String toString() {
		return getName();
	}
	/**
	 * @return the modelClass
	 */
	public String getModelClass() {
		return modelClass;
	}
	/**
	 * @param modelClass the modelClass to set
	 */
	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
	
}

