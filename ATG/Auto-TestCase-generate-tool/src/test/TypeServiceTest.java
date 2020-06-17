package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import bean.Testcase;
import bean.Type;
import jdbc.DBTools;
import mapper.RowRequirementMapper;
import mapper.TestcaseMapper;
import mapper.TypeMapper;

public class TypeServiceTest {
	@Test
    public void findTypeByModelID() throws Exception{
        SqlSession sqlSession=DBTools.getSession();
        TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
        try{
            List<Type> list = typeMapper.findTypeByModelID(1);
            for(int i=0,k=list.size();i<k;i++){
                System.out.println(list.get(i).toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
    }
	@Test
	public void insertType() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
		try{
            Type type =new Type();
            //model.setModelID(1);
            type.setTypeName("1");
            type.setTypeRange("2");
            type.setModelID(3);
            type.setTypeSize(4);
            type.setTypeRowName("5");
            typeMapper.insertType(type);
            System.out.println(type.getTypeID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
	@Test
	public void deleteTypeByModelID()throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		TypeMapper typeMapper = sqlSession.getMapper(TypeMapper.class);
		try {
			typeMapper.deleteTypeByModelID(24);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
