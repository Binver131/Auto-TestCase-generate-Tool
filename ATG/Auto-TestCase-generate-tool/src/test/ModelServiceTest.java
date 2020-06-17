package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.eclipse.swt.internal.C;
import org.junit.Test;

import bean.Model;
import bean.Testcase;
import handlers.importHandler;
import jdbc.ConnectHelp;
import jdbc.DBTools;
import mapper.ModelMapper;
import mapper.TestcaseMapper;

public class ModelServiceTest {
	@Test
    public void findModelAll() throws Exception{
//        SqlSession sqlSession=DBTools.getSession();
//        ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
//        try{
//            List<Model> list = modelMapper.findModelAll();
//            for(int i=0,k=list.size();i<k;i++){
//            	//合并的时候这边加入很多东西
//                System.out.println(list.get(i).toString());
//            }
//            sqlSession.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            sqlSession.rollback();
//        }
	//ConnectHelp.getDataBaseInstance();
    }
	@Test
	public void insertModel() throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
		try{
            Model model =new Model();
            //model.setModelID(1);
            model.setModelIdentifier("123");
            model.setModelName("123");
            model.setModelContent("12345qwqe");
            model.setModelClass("A");
            modelMapper.insertModel(model);
            System.out.println(model.getModelID());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
	@Test
	public void deleteModelByModelID()throws Exception{
		SqlSession sqlSession=DBTools.getSession();
		ModelMapper modelMapper = sqlSession.getMapper(ModelMapper.class);
		try {
			modelMapper.deleteModelByModelID(24);
			sqlSession.commit();
		}catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
	}
}
