package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Type;
import bean.Variable;

public class VariableDaoImp implements VariableDao {
private SqlSession sqlSession;
	
    public VariableDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Variable> findVaribleByModelID(int modelID) throws Exception {
		List<Variable> list = sqlSession.selectList("mapper.VariableMapper.findVariableByModelID");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertVariable(Variable variable) throws Exception {
		sqlSession.insert("mapper.VariableMapper.insertVariable",variable);
		sqlSession.commit();
		sqlSession.close();
		
	}
	@Override
	public void deleteVariableByModelID(int modelID) throws Exception {
		sqlSession.insert("mapper.VariableMapper.deleteVariableByModelID",modelID);
		sqlSession.commit();
		sqlSession.close();
		
	}
}
