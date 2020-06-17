package mapper;

import java.util.List;

import bean.Type;

public interface TypeMapper {
	 public List<Type> findTypeByModelID(int modelID) throws Exception;
	 public void insertType(Type type)throws Exception;
	 public void deleteTypeByModelID(int modelID)throws Exception;
}
