package dao;

import java.util.List;

import bean.Testcase;

public interface TestcaseDao {
	public List<Testcase> findTestcaseByRequirementID(int requirementID) throws Exception;
	public void insertTestcase(Testcase testcase) throws Exception;
}
