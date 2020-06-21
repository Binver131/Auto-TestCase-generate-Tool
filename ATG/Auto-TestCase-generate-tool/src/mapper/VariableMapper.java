package mapper;

import java.util.List;

import bean.Variable;

public interface VariableMapper {
	 public List<Variable> findVariableByModelID(int modelID) throws Exception;
	 public void insertVariable(Variable variable)throws Exception;
	 public void deleteVariableByModelID(int modelID)throws Exception;
}
