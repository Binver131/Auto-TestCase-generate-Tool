package dao;

import java.util.List;

import bean.Variable;

public interface VariableDao {
	public List<Variable> findVaribleByModelID(int modelID) throws Exception;
	public void insertVariable(Variable variable)throws Exception;
	public void deleteVariableByModelID(int modelID)throws Exception;
}
