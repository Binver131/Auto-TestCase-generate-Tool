package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Type;

public class TypeDaoImp implements TypeDao{
	private SqlSession sqlSession;
	
    public TypeDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Type> findTypeByModelID(int modelID) throws Exception {
		List<Type> list = sqlSession.selectList("mapper.TypeMapper.findTypeByModelID");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertType(Type type) throws Exception {
		sqlSession.insert("mapper.TypeMapper.insertType", type);
		sqlSession.commit();
		sqlSession.close();
	}
	@Override
	public void deleteTypeByModelID(int modelID) throws Exception {
		sqlSession.delete("mapper.TypeMapper.deleteModelByModelID", modelID);
		sqlSession.commit();
		sqlSession.close();
		
	}

}
