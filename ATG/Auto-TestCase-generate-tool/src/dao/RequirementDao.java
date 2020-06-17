package dao;

import java.util.List;

import bean.Requirement;


public interface RequirementDao {
	public List<Requirement> findRequirementByRowRequirementID(int rowRequirementID) throws Exception;
	public void insertRequirement(Requirement requirement)throws Exception;
	public void deleteRequirementByModelID(int modelID) throws Exception;
}
