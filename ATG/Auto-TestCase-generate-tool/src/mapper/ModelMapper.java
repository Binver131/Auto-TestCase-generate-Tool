package mapper;

import java.util.List;

import bean.Model;

public interface ModelMapper {
	public List<Model> findModelAll() throws Exception;
	public void insertModel(Model model) throws Exception;
	public void deleteModelByModelID(int modelID) throws Exception;
}
