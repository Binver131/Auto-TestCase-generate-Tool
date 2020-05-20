package entity;
import java.util.HashMap;

public class DataBase {
	private HashMap<String,Model> models;
	public DataBase() {
		models = new HashMap<String,Model>();
	}
	public void addChild(String key,Model child) {
		models.put(key,child);
		child.setParent(this);
	}
	public void removeChild(String key) {
		models.get(key).setParent(null);
		models.remove(key);
	}
	public Model[] getChildren() {
		
		return (Model[]) models.values().toArray(new Model[models.size()]);
	}
	
	public boolean hasModel(Model model) {
		return models.containsValue(model);
	}
	
	public boolean hasChildren() {
		return models.size()>0;
	}
}

