package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Requirement;
import entity.TestCase;
import entity.Type;
import entity.Variables;
 
public class ConnectHelper {
	public static List<Type> typeList = new ArrayList<>();
	public static List<Requirement> requirementList = new ArrayList<>();
	public static List<TestCase> testcaseList = new ArrayList<>();
	public static List<Variables> variableList = new ArrayList<>();
	
    public static void connectHelper(String str) {
        // 声明Connection对象
        Connection con;
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名 test
        String url = "jdbc:mysql://localhost:3306/atc?useSSL=false";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "123456";
        // 遍历查询结果集
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("成功以 " + user + " 身份连接到数据库");
 
            // 2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            // 要执行的SQL语句
            String sql1 = "select * from typetable";
            String sql2 = "select * from requirementtable";
            String sql3 = "select * from testcasetable";
            String sql4 = "select * from variablestable";
            String sql5 = "select * from testcasetable where testcase_requirementid = '"+str+"'";
            // 3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql1);

            //List<Type> typeList = new ArrayList<>();
            while (rs.next()) {
                Type type = new Type();
            	type.setTypeID(rs.getInt("type_id"));
            	type.setTypename(rs.getString("type_name"));
            	type.setTyperange(rs.getString("type_range"));
                typeList.add(type); 
            }
            System.out.println(typeList);
            
            rs = statement.executeQuery(sql2);
            //List<Requirement> requirementList = new ArrayList<>();
            while (rs.next()) {
                Requirement requirement = new Requirement();
                requirement.setRequirementId(rs.getString("requirement_id"));
                requirement.setRequirementName(rs.getString("requirement_name"));
                requirement.setRequirementText(rs.getString("requirement_text"));
                requirement.setRequirementCondition(rs.getString("requirement_condition"));
                requirement.setRequirementInput(rs.getString("requirement_input"));
                requirement.setRequirementOutput(rs.getString("requirement_output"));              
                requirementList.add(requirement); 
            }
            System.out.println(requirementList);
            
//            rs = statement.executeQuery(sql3);
//            //List<TestCase> testcaseList = new ArrayList<>();
//            while (rs.next()) {
//            	TestCase testcase = new TestCase();
//            	testcase.setTestcaseID(rs.getInt("testcase_id"));
//            	testcase.setTestcaseRequirementID(rs.getString("testcase_requirementid"));
//            	testcase.setTestcaseCondition(rs.getString("testcase_condition"));
//                testcase.setTestcaseInput(rs.getString("testcase_input"));
//                testcase.setTestcaseOutput(rs.getString("testcase_output"));
//                testcase.setTestcaseEvaluate(rs.getString("testcase_evaluate"));          
//                testcase.setTestcaseType(rs.getString("testcase_type")); 
//                testcaseList.add(testcase); 
//            }
//            System.out.println(testcaseList);
//            
            rs = statement.executeQuery(sql4);
            //List<Variables> variableList = new ArrayList<>();
            while (rs.next()) {
            	Variables variables = new Variables();
            	variables.setVariablesID(rs.getInt("variables_id"));
            	variables.setVariablesName(rs.getString("variables_name"));
            	variables.setVariablesTypeID(rs.getInt("variables_type"));       
            	variableList.add(variables); 
            }
            System.out.println(variableList);
            
            rs = statement.executeQuery(sql5);
            //List<TestCase> testcaseList = new ArrayList<>();
            while (rs.next()) {
            	TestCase testcase = new TestCase();
            	testcase.setTestcaseID(rs.getInt("testcase_id"));
            	testcase.setTestcaseRequirementID(rs.getString("testcase_requirementid"));
            	testcase.setTestcaseCondition(rs.getString("testcase_condition"));
                testcase.setTestcaseInput(rs.getString("testcase_input"));
                testcase.setTestcaseOutput(rs.getString("testcase_output"));
                testcase.setTestcaseEvaluate(rs.getString("testcase_evaluate"));          
                testcase.setTestcaseType(rs.getString("testcase_type")); 
                testcaseList.add(testcase); 
            }
            System.out.println(testcaseList);
            
            rs.close();
            con.close();
        }  
        catch (ClassNotFoundException e) {
            // 数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally {
            System.out.println("获取数据库数据完毕！");
        }
    }
}