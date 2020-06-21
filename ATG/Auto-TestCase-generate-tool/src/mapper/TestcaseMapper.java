package mapper;

import java.util.List;

import bean.Testcase;


public interface TestcaseMapper {
	public List<Testcase> findTestcaseByRequirementID(int requirementID) throws Exception;
	public void insertTestcase(Testcase testcase) throws Exception;
}
