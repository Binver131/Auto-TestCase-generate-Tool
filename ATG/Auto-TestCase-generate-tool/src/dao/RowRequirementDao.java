package dao;

import java.util.List;

import bean.RowRequirement;


public interface RowRequirementDao {
	public List<RowRequirement> findRowRequirementByModelID(int modelID) throws Exception;
	public void insertRowRequirement(RowRequirement rowRequirement) throws Exception;
	public void deleteRowRequirementByModelID(int modelID)throws Exception;
}
