package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Type;
import bean.Variable;
import jdbc.DBTools;
import mapper.TypeMapper;
import mapper.VariableMapper;

public class VariableServiceTest {
	@Test
    public void findVariableByModelID() throws Exception{
        SqlSession sqlSession=DBTools.getSession();
        VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
        try{
            List<Variable> list = variableMapper.findVariableByModelID(1);
            for(int i=0,k=list.size();i<k;i++){
            	//到时候合并的时候这边需要将type补上
                System.out.println(list.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
	@Test
	public void insertVariable() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
		try{
            Variable variable =new Variable();
            //model.setModelID(1);
            variable.setVariableName("1");
            variable.setTypeID(2);
            variable.setModelID(3);
            variableMapper.insertVariable(variable);
            System.out.println(variable.getVariableID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
	@Test
	public void deleteVariableByModelID()throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		VariableMapper variableMapper = sqlSession.getMapper(VariableMapper.class);
		try {
			variableMapper.deleteVariableByModelID(24);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
