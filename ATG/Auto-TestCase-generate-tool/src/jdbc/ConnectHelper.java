package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import console.ConsoleHandler;
import entity.DataBase;
import entity.Model;
import entity.Requirement;
import entity.RowRequirement;
import entity.TestCase;
import entity.Type;
import entity.Variables;
/**
 * 
* @ClassName: ConnectHelper  
* @Description: TODO(����ģʽ����ȡ���޸�database)  
* @author Binver131  
* @date 2020��5��19��
 */
public class ConnectHelper {
	//ģ�Ϳ�
	private static DataBase database = null;
	

	//���ݿ�����
	private static Connection con;
	// ����������
	private final static String driver = "com.mysql.jdbc.Driver";
	// URLָ��Ҫ���ʵ����ݿ��� test
	private final static String url = "jdbc:mysql://localhost:3306/atg?useSSL=false&allowPublicKeyRetrieval=true";
	// MySQL����ʱ���û���
	private final static String user = "root";
	// MySQL����ʱ������
	private final static String password = "123456789";
	
	/**
	 * 
	* ˽�л����캯������������ʵ����
	 */
	
	
	
	private ConnectHelper() {}
	
	
	/**
	 * 
	* @Title: getDataBaseInstance  
	* @Description: TODO(������һ�仰�����������������)  
	* @return   ����  
	* @return DataBase    ��������  
	* @throws
	 */
	public static DataBase getDataBaseInstance() {
		if(database == null) {
			initCon();
			initDataBase();			 
		}
		return database;
	}
	
	
	private static void initCon() {
		if(con != null)
			return;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (!con.isClosed())
				System.out.println("�ɹ��� " + user + " ������ӵ����ݿ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void initDataBase() {
		database = new DataBase();
		// ������ѯ�����
		try {
			// ������������
			

			// 2.ResultSet�࣬������Ż�ȡ�Ľ��������
			Statement statement = con.createStatement();
			String getModelTable = "select * from models";
			ResultSet rs = statement.executeQuery(getModelTable);
			   
			// ����DataBase��
			while (rs.next()) {
				Model model = new Model(rs.getString("model_id"));
				String key = rs.getString("No");
				model.setDbId(key);
				model.setName(rs.getString("model_name"));
				model.setText(rs.getString("model_text"));
				model.setModelClass(rs.getString("Model_Class"));
				System.out.println(model.getName());

				//��ȡ���ͱ�
				String sql1 = "select * from typetable";
	            Statement st1=con.createStatement();
	            ResultSet rs1 = st1.executeQuery(sql1);
	            while(rs1.next()) {
	            	Type type = new Type();
	            	type.setModelID(rs1.getString("model"));
	            	type.setSizeString(rs1.getString("size"));
	            	type.setTypeID(rs1.getString("type_id"));
	            	type.setTypename(rs1.getString("type_name"));
	            	type.setTyperange(rs1.getString("type_range"));
	            	model.addType(type);
	            }
				
				
				// ��ȡԭʼ������
				String rowReqSql = "select * from rowrequirement where model=" + rs.getString("No");
				Statement rowRequireStatement = con.createStatement();
				ResultSet rowRequirementResult = rowRequireStatement.executeQuery(rowReqSql);
				while (rowRequirementResult.next()) {
					RowRequirement rowRequirement = new RowRequirement();
					rowRequirement.setDbId(rowRequirementResult.getString("No"));
					rowRequirement.setName(rowRequirementResult.getString("Name"));
					rowRequirement.setContent(rowRequirementResult.getString("content"));

					String reqSql = "select * from requirementtable where RowRequirement="
							+ rowRequirementResult.getString("No");
					Statement requireStatement = con.createStatement();
					ResultSet requirementResult = requireStatement.executeQuery(reqSql);
					while (requirementResult.next()) {
						Requirement requirement = new Requirement();
						requirement.setDbId(requirementResult.getString("No"));
						requirement.setParent(rowRequirement);
						requirement.setRequirementId(requirementResult.getString("requirement_id"));
						requirement.setRequirementName(requirementResult.getString("requirement_name"));
						requirement.setRequirementText(requirementResult.getString("requirement_text"));
						String[] preConditionVars = requirementResult.getString("requirement_condition").split(",");
						String[] inputVars = requirementResult.getString("requirement_input").split(",");
						String[] outputVars = requirementResult.getString("requirement_output").split(",");
						for (String ID : preConditionVars) {
							if (!ID.equals("")&& ID != null) {
								
								String preConStr = "select * from variablestable where variables_id=" + ID;
								Statement preConVarsStatement = con.createStatement();
								ResultSet result = preConVarsStatement.executeQuery(preConStr);
								Variables var = new Variables();
								while (result.next()) {
									
									var.setVariablesID(ID);
									var.setVariablesName(result.getString("variables_name"));
									var.setVariablesTypeID(result.getString("variables_type"));
								}
								
								requirement.addPreConVar(var);
								model.addVariable(var);
								preConVarsStatement.close();
							}
						}

						for (String ID : inputVars) {
							if (!ID.equals("")&& ID != null) {
								String inputStr = "select * from variablestable where variables_id=" + ID;
								Statement inputStatement = con.createStatement();
								ResultSet result = inputStatement.executeQuery(inputStr);
								Variables var = new Variables();
								while (result.next()) {
	
									var.setVariablesID(ID);
									var.setVariablesName(result.getString("variables_name"));
									var.setVariablesTypeID(result.getString("variables_type"));
								}
	
								requirement.addInputVar(var);
								model.addVariable(var);
								inputStatement.close();
							}
						}

						for (String ID : outputVars) {
							if (!ID.equals("")&& ID != null) {
								String outputStr = "select * from variablestable where variables_id=" + ID;
								Statement outputStatement = con.createStatement();
								ResultSet result = outputStatement.executeQuery(outputStr);
								Variables var = new Variables();
								while (result.next()) {
	
									var.setVariablesID(ID);
									var.setVariablesName(result.getString("variables_name"));
									var.setVariablesTypeID(result.getString("variables_type"));
								}
								requirement.addOutputVar(var);
								model.addVariable(var);
								outputStatement.close();
							}
						}

						String testcaseStr = "select * from testcasetable where requirementid=" + requirement.getDbId();
						Statement testCaseStatement = con.createStatement();
						ResultSet testCaseResult = testCaseStatement.executeQuery(testcaseStr);

						while (testCaseResult.next()) {
							TestCase testcase = new TestCase();
							testcase.setTestcaseID(testCaseResult.getInt("testcase_id"));
							testcase.setTestcaseInput(testCaseResult.getString("testcase_input"));
							testcase.setTestcaseOutput(testCaseResult.getString("testcase_output"));
							testcase.setTestcaseCondition(testCaseResult.getString("testcase_condition"));
							testcase.setTestcaseType(testCaseResult.getString("testcase_type"));
							testcase.setTestcaseEvaluate(testCaseResult.getString("testcase_evaluate"));

							requirement.addTestcases(testcase);

						}

						rowRequirement.addChild(requirement);
					}
					requireStatement.close();
					model.addChild(rowRequirement);

				}
				rowRequireStatement.close();
				database.addChild(key,model);
			}
			rs.close();
			

			rs.close();
			
		} catch (SQLException e) {
			// ���ݿ�����ʧ���쳣����
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("��ȡ���ݿ�������ϣ�");
		}
		
	}
	
	/**
	 * 
	* @Title: addModel  
	* @Description: TODO(��������ģ��)  
	* @param   ����  
	* @return void    ��������  
	* @throws
	 */
	public static String addModel(Model model) {
		String key = insertModel(model);
		if(key != null) {
			model.setDbId(key);
			database.addChild(key,model);
		}
			
		else
			ConsoleHandler.error("�������ݿ�ʧ��");
		return key;
	}
	
	
	/**  
	* @Title: insertModel  
	* @Description: TODO(����ģ�͵����ݿ⣬����֮ǰ��Ҫ�ж�ģ���Ƿ��Ѿ�����)  
	* @param  model �������ģ��
	* @return void    ��������  
	* @throws  
	*/  
	public static String insertModel(Model model) {
		initCon();
		// TODO Auto-generated method stub
		String sql="insert into models(model_ID,model_name,model_text,model_class)values('"+model.getID()+"','"+model.getName()+"','"+model.getText()+"','"+model.getModelClass()+"')";
		try {
			java.sql.PreparedStatement insertModelStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertModelStatement.executeUpdate();
			ResultSet key = insertModelStatement.getGeneratedKeys();
			if(key.next()) {
				return key.getString(1);
			}
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String addRowRequirement(RowRequirement rowRequirement,String modelID) {
		
		//���������Щ�пա�ȥ�ز���
		for(RowRequirement rowrequire:database.getChild(modelID).getChildren()) {
			if(rowrequire.getName().equals(rowRequirement.getName())) {
				return rowrequire.getDbId();
			}
		}
		String rowidString = insertRowRequirement(rowRequirement, modelID);
		rowRequirement.setDbId(rowidString);
		database.getChild(modelID).addChild(rowRequirement);	
		return rowidString;
	}
	
	
	

	private static String insertRowRequirement(RowRequirement rowRequirement,String modelID) {
		initCon();
		// TODO Auto-generated method stub
		String sql="insert into rowrequirement(Name,content,model)values('"+rowRequirement.getName()+"','"+rowRequirement.getContent()+"',"+modelID+")";
		try {
			java.sql.PreparedStatement insertRowRequirementStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertRowRequirementStatement.executeUpdate();
			ResultSet key = insertRowRequirementStatement.getGeneratedKeys();
			if(key.next()) {
				return key.getString(1);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**  
	* @Title: existModel  
	* @Description: TODO(������һ�仰�����������������)  
	* @param   ����  
	* @return boolean    ��������  
	* @throws  
	*/  
	public static boolean existModel(Model model) {
		// TODO Auto-generated method stub
		
		for(Map.Entry<String, Model> entry : database.getModels().entrySet()) {
			if(entry.getValue().getName().equals(model.getName())) {
				return false;
			}
		}
		return true;
	}
	
	
	/**  
	* @Title: insertVariables  
	* @Description: TODO(���ڵ���ģ��ʱ�������)  
	* @param  Variables variables,String modelID  
	* @return void    
	* @throws  
	*/  
	
	public static String addVariables(Variables variables,String modelID) {
		
		for(Variables var:database.getChild(modelID).getVariablesMap().values()) {
			if(var.getVariablesName().equals(variables.getVariablesName())) {
				return var.getVariablesID();
			}
		}
		String varidString = insertVariables(variables, modelID);
		variables.setVariablesID(varidString);
		database.getChild(modelID).addVariable(variables);		
		return varidString;
		
	}
	
	
	private static String insertVariables(Variables variables,String modelID) {
		initCon();
		
		String sql="INSERT INTO variablestable(variables_name,variables_type,model) VALUES ('"+variables.getVariablesName()+"',"+variables.getVariablesTypeID()+","+modelID+")";
		try {
			java.sql.PreparedStatement insertVariablesStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertVariablesStatement.executeUpdate();	
			
			ResultSet key = insertVariablesStatement.getGeneratedKeys();
			if(key.next()) {
				
				return key.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return null;
	}
	
	//�������ֵ��ʱû����
	public static String addRequirement(Requirement requirement,String modelID,String rowID) {
		for(RowRequirement rowrequire:database.getChild(modelID).getChildren()) {
			if(rowrequire.getDbId().equals(rowID)) {
				for(Requirement require : rowrequire.getChildren()) {
					if(require.getRequirementName().equals(requirement.getRequirementName())) {
						return require.getDbId();
					}
				}
				String requireidString = insertRequirement(requirement, modelID, rowID);
				requirement.setDbId(requireidString);
				rowrequire.addChild(requirement);
				return requireidString;
			}
			
		}
		return null;
	}
	
	
	
	/**  
	* @Title: insertRequirement  
	* @Description: TODO(���ڵ���ģ���еĲ�������)  
	* @param  Requirement requirement,String modelID  
	* @return void    
	* @throws  
	*/  
	
	private static String insertRequirement(Requirement requirement,String modelID,String rowID) {
		initCon();
		String preConVars = "";
		String inputVars = "";
		String outputVars = "";
		for(Variables variables:requirement.getInputVars()) {
			inputVars = inputVars +variables.getVariablesID()+",";
		}
		for(Variables variables:requirement.getOutputVars()) {
			outputVars = outputVars +variables.getVariablesID()+",";
		}
		for(Variables variables:requirement.getPreConVars()) {
			preConVars = preConVars +variables.getVariablesID()+",";
		}
		String sql = "INSERT INTO requirementtable(requirement_id,requirement_name,requirement_text,requirement_condition,requirement_input,requirement_output,model,RowRequirement)VALUES('"+requirement.getRequirementId()+"','"+requirement.getRequirementName()+"','"+requirement.getRequirementText()+"','"+preConVars+"','"+inputVars+"','"+outputVars+"',"+modelID+","+rowID+")";
		try {
			java.sql.PreparedStatement insertRequirementStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertRequirementStatement.executeUpdate();
			ResultSet key = insertRequirementStatement.getGeneratedKeys();
			if(key.next()) {	
				//requirement.setDbId(key.getInt(1)+"");
				return key.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String addType(Type type,String modelID) {
		for(Map.Entry<String, Model> entry : database.getModels().entrySet()) {
			if(entry.getKey().equals(modelID)) {
				for(Map.Entry<String, Type> dbType :entry.getValue().getTypeMap().entrySet()) {
					if(dbType.getValue().getTypename().equals(type.getTypename())) {
						return dbType.getValue().getTypeID();					
					}else {
							;
					}
				}
				String typeid = insertType(type, modelID);
				type.setTypeID(typeid);
				database.getChild(modelID).addType(type);
				return typeid;
			}
		}
		return null;
	}
	
	/**  
	* @Title: insertType  
	* @Description: TODO(���ڲ�������)  
	* @param   
	* @return void    
	* @throws  
	*/
	private static String insertType(Type type,String modelID) {
		initCon();
		String sql = "INSERT INTO typetable(type_name,type_range,model,size,typerowname) VALUES ('"+type.getTypename()+"','"+type.getTyperange()+"',"+modelID+","+type.getSizeString()+",'"+type.getBasetypename()+"')";
		try {
			java.sql.PreparedStatement insertTypeStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertTypeStatement.executeUpdate();
			ResultSet key = insertTypeStatement.getGeneratedKeys();
			if(key.next()) {	
				return key.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**  
	* @Title: insertRequirement  
	* @Description: TODO(���ڵ���ģ���еĲ����������)  
	* @param   
	* @return void    
	* @throws  
	*/
	public static void insertTestCase(TestCase testCase,String modelID) {
		
	}
	
	/**  
	* @Title: deleteVariable
	* @Description: TODO(ɾ������)  
	* @param   
	* @return void    
	* @throws  
	*/
	public static void removeVariable(String modelID) {
		
		//������ʱû�мӴ�database��ɾȥvariable,����Ҫ������ϸ�ڲ�����ʱ���
		deleteVariable(modelID);
	}
	
	private static void deleteVariable(String modelID) {
		initCon();
		String sql="DELETE FROM variablestable WHERE variablestable.model = "+modelID;
		try {
			java.sql.PreparedStatement deleteVariableStatement = con.prepareStatement(sql);
			deleteVariableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void remodeType(String modelID) {
		//������ʱû�мӴ�database��ɾȥvariable,����Ҫ������ϸ�ڲ�����ʱ���
		deleteType(modelID);
	}
	
	private static void deleteType(String modelID) {
		initCon();
		String sql="DELETE FROM typetable WHERE typetable.model = "+modelID;
		try {
			java.sql.PreparedStatement deleteTypeStatement = con.prepareStatement(sql);
			deleteTypeStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeRequirement(String modelID) {
		//������ʱû�мӴ�database��ɾȥvariable,����Ҫ������ϸ�ڲ�����ʱ���
		deleteRequirement(modelID);
	}
	
	private static void deleteRequirement(String modelID) {
		initCon();
		String sql="DELETE FROM requirementtable WHERE requirementtable.model = "+modelID;
		try {
			java.sql.PreparedStatement deleteRequirementStatement = con.prepareStatement(sql);
			deleteRequirementStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeRowRequirement(String modelID) {
		deleteRowRequirement(modelID);
	}
	private static void deleteRowRequirement(String modelID) {
		initCon();
		String sql="DELETE FROM rowrequirement WHERE rowrequirement.model = "+modelID;
		try {
			java.sql.PreparedStatement deleteRowRequirementStatement = con.prepareStatement(sql);
			deleteRowRequirementStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeModel(String modelID) {
		database.removeChild(modelID);
		removeVariable(modelID);
		remodeType(modelID);
		removeRequirement(modelID);
		removeRowRequirement(modelID);
		deleteModel(modelID);
	}
	private static void deleteModel(String modelID) {
		initCon();
		String sql="DELETE FROM models WHERE models.`No` = "+modelID;
		try {
			java.sql.PreparedStatement deleteModelStatement = con.prepareStatement(sql);
			deleteModelStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}