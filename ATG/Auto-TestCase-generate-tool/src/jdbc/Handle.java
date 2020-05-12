package jdbc;

import java.util.ArrayList;
import java.util.List;

import entity.DataForm;
import entity.NewTestCase;
import entity.Requirement;
import entity.TestCase;
import entity.Variables;

public class Handle {
	public Handle(String requirementID){
		ConnectHelper.connectHelper(requirementID);
	}
	
	public static DataForm dataHandle(String requirementID){
		
		DataForm dataForm = new DataForm();
		for(Requirement temp:ConnectHelper.requirementList) {
			if(temp.getRequirementId().equals(requirementID)) {
				dataForm.setRequirementID(requirementID);
				List<String> condition = new ArrayList<>();
				List<String> input = new ArrayList<>();
				List<String> output = new ArrayList<>();
				for(String str:temp.getRequirementCondition().split(",")) {
					for(Variables variable:ConnectHelper.variableList) {
						if(Integer.valueOf(str)== variable.getVariablesID()) {
							condition.add(variable.getVariablesName());
						}
					}
				}
				for(String str:temp.getRequirementInput().split(",")) {
					for(Variables variable:ConnectHelper.variableList) {
						if(Integer.valueOf(str)== variable.getVariablesID()) {
							input.add(variable.getVariablesName());
						}
					}
				}
				for(String str:temp.getRequirementOutput().split(",")) {
					for(Variables variable:ConnectHelper.variableList) {
						if(Integer.valueOf(str)== variable.getVariablesID()) {
							output.add(variable.getVariablesName());
						}
					}
				}
				dataForm.setCondition(condition);
				dataForm.setInput(input);
				dataForm.setOutput(output);
				//dataForm.setCondition(temp.getRequirementCondition().split(","));
				//dataForm.setInput(temp.getRequirementInput().split(","));
				//dataForm.setOutput(temp.getRequirementOutput().split(","));
				break;
			}
			
		}
		return dataForm;	
	}
	public static NewTestCase testcaseHandle(TestCase testcase) {
		
		NewTestCase newtestcase = new NewTestCase();
		newtestcase.setTestcaseID(testcase.getTestcaseID());
		newtestcase.setTestcaseRequirementID(testcase.getTestcaseRequirementID());
		List<String> mytestcase = new ArrayList<>();

		
		
		for(String temp:testcase.getTestcaseCondition().split(",")) {
			mytestcase.add(temp);
		}
		for(String temp:testcase.getTestcaseInput().split(",")) {
			mytestcase.add(temp);
		}
		for(String temp:testcase.getTestcaseOutput().split(",")) {
			mytestcase.add(temp);
		}
		newtestcase.setTestcase(mytestcase);
		newtestcase.setTestcaseEvaluate(testcase.getTestcaseEvaluate());
		newtestcase.setTestcaseType(testcase.getTestcaseType());
		return newtestcase;
	}
	
	public static void main(String[] args) {
		Handle handle = new Handle("R1.1");
//		ConnectHelper.connectHelper();
//		System.out.println(handle.testcaseHandle(ConnectHelper.testcaseList.get(0)));
//		Handle handle = new Handle();
//		DataForm dataForm = handle.dataHandle("R1.2");
//		System.out.println(dataForm);
		String testVectors[][] = new String[1][9];
		//int testVectorsrow =0;
		for(int testVectorsrow =0,k=ConnectHelper.testcaseList.size();testVectorsrow<k;testVectorsrow++ ) {
		//for(TestCase temp:ConnectHelper.testcaseList) {
			NewTestCase newtestcase = handle.testcaseHandle(ConnectHelper.testcaseList.get(testVectorsrow));
			for(int testVectorscol=0,s=newtestcase.getTestcase().size();testVectorscol<s;testVectorscol++ ) {
				System.out.println(newtestcase);
				testVectors[testVectorsrow][testVectorscol]=newtestcase.getTestcase().get(testVectorscol);
				System.out.println(testVectors[testVectorsrow][testVectorscol]);
			}
			testVectorsrow++;
		}
	}
}
