package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Model;
import bean.RowRequirement;
import jdbc.DBTools;
import mapper.ModelMapper;
import mapper.RequirementMapper;
import mapper.RowRequirementMapper;

public class RowRequirementServiceTest {
	@Test
    public void findRowRequirementByModelID() throws Exception{
        SqlSession sqlSession=DBTools.getSession();
        RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
        try{
            List<RowRequirement> list = rowRequirementMapper.findRowRequirementByModelID(1);
            for(int i=0,k=list.size();i<k;i++){
            	//合并的时候
                System.out.println(list.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
	@Test
	public void insertRowRequirement() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
		try{
            RowRequirement rowRequirement =new RowRequirement();
            //model.setModelID(1);
            rowRequirement.setRowRequirementIdentifier("123");
            rowRequirement.setRowRequirementName("asd");
            rowRequirement.setRowRequirementContent("aaaaa");
            rowRequirement.setModelID(1);
            rowRequirementMapper.insertRowRequirement(rowRequirement);;
            System.out.println(rowRequirement.getRowRequirementID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
	@Test
	public void deleteRowRequirementByModelID()throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		RowRequirementMapper rowRequirementMapper = sqlSession.getMapper(RowRequirementMapper.class);
		try {
			rowRequirementMapper.deleteRowRequirementByModelID(24);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
