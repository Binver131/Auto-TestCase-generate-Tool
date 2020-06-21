/**  
* @Title: Model.java  
* @Package entity  
* @Description: TODO(ԭʼ����)  
* @author Binver131  
* @date 2020��5��12��  
* @version V1.0  
*/  
package entity;


import java.util.ArrayList;
import java.util.List;


/**  
 * @ClassName: Model  
 * @Description: TODO(��������ģ��������Ϣ)  
 * @author Binver131  
 * @date 2020��5��12��    
 */
public class RowRequirement {
	private Model parent;
	private String dbId;				//���ݿ��е�����
	private String name;
	private String content;				//ԭʼ��������
	private List<Requirement> children;//�����
	public RowRequirement() {
		
		children = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addChild(Requirement child) {
		children.add(child);
		child.setParent(this);
	}
	public void removeChild(Requirement child) {
		children.remove(child);
		child.setParent(null);
	}
	public Requirement[] getChildren() {
		return (Requirement[]) children.toArray(new Requirement[children.size()]);
	}
	public boolean hasChildren() {
		return children.size()>0;
	}
	public void setParent(Model model) {
		this.parent = model;
	}
	public Model getParent() {
		return parent;
	}
	public String toString() {
		return getName();
	}
	
	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}
	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}
