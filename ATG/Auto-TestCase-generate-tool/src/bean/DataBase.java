package bean;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class DataBase {
	private HashMap<String,Model> models;
	
	public DataBase() {
		models = new LinkedHashMap<String, Model>();
	}
	
	public void addModelMap(Model model) {
		this.models.put(model.getModelName(),model);
	}
	
	public Model[] getModels() {
		return (Model[]) models.values().toArray(new Model[models.size()]);
	}
	public boolean hasChildren() {
		return models.size()>0;
	}
	public void removeModel(String modelName) {
		models.remove(modelName);
	}
}
