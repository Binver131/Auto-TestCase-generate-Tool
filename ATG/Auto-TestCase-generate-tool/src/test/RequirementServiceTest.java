package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Model;
import bean.Requirement;
import bean.RowRequirement;
import bean.Testcase;
import jdbc.DBTools;
import mapper.ModelMapper;
import mapper.RequirementMapper;
import mapper.RowRequirementMapper;
import mapper.TestcaseMapper;

public class RequirementServiceTest {
	@Test
    public void findRequirementByRowRequirementID() throws Exception{
        SqlSession sqlSession=DBTools.getSession();
        RequirementMapper requirementMapper = sqlSession.getMapper(RequirementMapper.class);
        try{
            List<Requirement> list = requirementMapper.findRequirementByRowRequirementID(1);
            for(int i=0,k=list.size();i<k;i++){
            	//合并的时候这边加入很多东西
                System.out.println(list.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
	@Test
	public void insertRequirement() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		RequirementMapper requirementMapper = sqlSession.getMapper(RequirementMapper.class);
		try{
            Requirement requirement =new Requirement();
            //model.setModelID(1);
            requirement.setRequirementIdentifier("1");
            requirement.setRequirementName("2");
            requirement.setRequirementContent("3");
            requirement.setRequirementCondition("4");
            requirement.setRequirementInput("5");
            requirement.setRequirementOutput("6");
            requirement.setModelID(6);
            requirement.setRowRequirementID(7);
            requirementMapper.insertRequirement(requirement);;
            System.out.println(requirement.getRequirementID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
	@Test
	public void deleteRequirementByModelID()throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		RequirementMapper requirementMapper = sqlSession.getMapper(RequirementMapper.class);
		try {
			requirementMapper.deleteRequirementByModelID(6);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
