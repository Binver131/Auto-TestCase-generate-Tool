package jdbc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import bean.DataBase;
import bean.Model;
import bean.Requirement;
import bean.RowRequirement;
import bean.Testcase;
import bean.Type;
import bean.Variable;
import mapper.ModelMapper;
import mapper.RequirementMapper;
import mapper.RowRequirementMapper;
import mapper.TestcaseMapper;
import mapper.TypeMapper;
import mapper.VariableMapper;
import views.DataBaseDisplay;

public class ConnectHelp {
	public static DataBase database = null;
	private ConnectHelp() {}
	public static DataBase getDataBaseInstance() {
		if(database == null) {
			initDataBase();			 
		}
		return database;
	}
	private static void initDataBase() {
		// TODO Auto-generated method stub
		database = new DataBase();
		//DataBaseDisplay.chooseDatabase();
		SqlSession sqlSession=DBTools.getSession();
		ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
		TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
		VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
		RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
		RequirementMapper  requirementMapper = sqlSession.getMapper(RequirementMapper.class);
	    TestcaseMapper testcaseMapper =sqlSession.getMapper(TestcaseMapper.class);
		
		try{
            List<Model> models = modelMapper.findModelAll();
            for(int i=0,k=models.size();i<k;i++){
            	//合并的时候这边加入很多东西
            	int modelID=models.get(i).getModelID();
            	List<Type> types = typeMapper.findTypeByModelID(modelID);
            	List<Variable> variables = variableMapper.findVariableByModelID(modelID);
//                for(Type type:types) {
//                	models.get(i).addTypeMap(type);
//                }
                for(Variable variable:variables) {
                	for(Type type:types) {
                		models.get(i).addTypeMap(type);
                		if(variable.getTypeID()==type.getTypeID()) {
                			variable.setVariableType(type);
                		}
                	}
                	models.get(i).addVariableMap(variable);
                }
                List<RowRequirement> rowRequirements = rowRequirementMapper.findRowRequirementByModelID(modelID);
                for(RowRequirement rowRequirement:rowRequirements) {
                	//System.out.println(rowRequirement.toString());
                	rowRequirement.setParent(models.get(i));
                	List<Requirement> requirements = requirementMapper.findRequirementByRowRequirementID(rowRequirement.getRowRequirementID());
                	for(Requirement requirement:requirements) {
                		//System.out.println(requirement.toString());
                		requirement.setParent(rowRequirement);
                		List<Testcase> testcases = testcaseMapper.findTestcaseByRequirementID(requirement.getRequirementID());
                		for(Testcase testcase:testcases) {
                			testcase.addTestcaseConditionMap(requirement.conditionVars(), testcase.conditionVal());
                			testcase.addTestcaseInputMap(requirement.inputVars(), testcase.inputVal());
                			testcase.addTestcaseOutputMap(requirement.outputVars(), testcase.outputVal());
                			requirement.addTestcaseMap(testcase);
                		}
                		rowRequirement.addRequirementMap(requirement);
                	}
                	models.get(i).addRowRequirementMap(rowRequirement);
                }
                database.addModelMap(models.get(i));
                models.get(i).setParent(database);
                //System.out.println(models.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
    }
	public static void insertModel(Model model) throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
		try{
            modelMapper.insertModel(model);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void insertRowRequirement(RowRequirement rowRequirement) throws Exception{
			SqlSession sqlSession=DBTools.getSession();
			RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
			try{
	            rowRequirementMapper.insertRowRequirement(rowRequirement);;
	            sqlSession.commit();
	        }catch (Exception e){
	            e.printStackTrace();
	            sqlSession.rollback();
	        }
			sqlSession.close();
	}
	public static void insertRequirement(Requirement requirement) throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		RequirementMapper requirementMapper = sqlSession.getMapper(RequirementMapper.class);
		try{
            requirementMapper.insertRequirement(requirement);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void insertTestcase(Testcase testcase) throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		TestcaseMapper testcaseMapper = sqlSession.getMapper(TestcaseMapper.class);
		try{
            testcaseMapper.insertTestcase(testcase);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void insertType(Type type) throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
		try{
            typeMapper.insertType(type);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void insertVariable(Variable variable) throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
		try{
            variableMapper.insertVariable(variable);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	
	public static boolean existModel(Model model) {
		// TODO Auto-generated method stub
		for(Model temp:database.getModels()) {
			if(temp.getModelName().equals(model.getModelName())) {
				return false;
			}
		}
		return true;
	}
	public static RowRequirement existRowRequirement(Model model,RowRequirement rowRequirement) {
		if(model.getChildren().containsKey(rowRequirement.getRowRequirementIdentifier())) {
			return model.getChildren().get(rowRequirement.getRowRequirementIdentifier());
		}
		return null;
	}
	public static Type existType(Model model,Type type) {
		if(model.getTypeMap().containsKey(type.getTypeName())) {
			return model.getTypeMap().get(type.getTypeName());
		}
		return null;
	}
	public static Variable existVariable(Model model,Variable variable) {
		if(model.getVariableMap().containsKey(variable.getVariableName())) {
			return model.getVariableMap().get(variable.getVariableName());
		}
		return null;
	}
	
	public static void deleteModel(Model model) {
		SqlSession sqlSession=DBTools.getSession();
		ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
		try {
			modelMapper.deleteModelByModelID(model.getModelID());
			database.removeModel(model.getModelName());
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void deleteRowRequirement(int modelID) {
		SqlSession sqlSession=DBTools.getSession();
		RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
		try {
			rowRequirementMapper.deleteRowRequirementByModelID(modelID);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void deleteRequirement(int modelID) {
		SqlSession sqlSession=DBTools.getSession();
		RequirementMapper requirementMapper = sqlSession.getMapper(RequirementMapper.class);
		try {
			requirementMapper.deleteRequirementByModelID(modelID);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void deleteType(int modelID) {
		SqlSession sqlSession=DBTools.getSession();
		TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
		try {
			typeMapper.deleteTypeByModelID(modelID);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
	public static void deleteVariable(int modelID) {
		SqlSession sqlSession=DBTools.getSession();
		VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
		try {
			variableMapper.deleteVariableByModelID(modelID);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
		sqlSession.close();
	}
}
