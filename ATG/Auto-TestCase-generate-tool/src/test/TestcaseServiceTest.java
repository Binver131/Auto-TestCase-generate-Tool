package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.RowRequirement;
import bean.Testcase;
import bean.Type;
import jdbc.DBTools;
import mapper.RowRequirementMapper;
import mapper.TestcaseMapper;
import mapper.TypeMapper;

public class TestcaseServiceTest {
	@Test
    public void findTestcaseByRequirementID() throws Exception{
        SqlSession sqlSession=DBTools.getSession();
        TestcaseMapper testcaseMapper = sqlSession.getMapper(TestcaseMapper.class);
        try{
            List<Testcase> list = testcaseMapper.findTestcaseByRequirementID(1);
            for(int i=0,k=list.size();i<k;i++){
            	//合并的时候这边要根据string和需求整合成hashmap
                System.out.println(list.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
	@Test
	public void insertTestcase() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		TestcaseMapper testcaseMapper = sqlSession.getMapper(TestcaseMapper.class);
		try{
            Testcase testcase =new Testcase();
            //model.setModelID(1);
            testcase.setRequirementID(1);
            testcase.setTestcaseCondition("2");
            testcase.setTestcaseInput("3");
            testcase.setTestcaseOutput("4");
            testcase.setTestcaseType("auto");
            testcase.setTestcaseEvaluate("equal");
            testcaseMapper.insertTestcase(testcase);
            System.out.println(testcase.getTestcaseID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
