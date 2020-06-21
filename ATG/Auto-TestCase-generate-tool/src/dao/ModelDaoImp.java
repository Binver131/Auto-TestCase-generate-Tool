package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Model;

public class ModelDaoImp implements ModelDao{
	private SqlSession sqlSession;
    public ModelDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Model> findModelAll() throws Exception {
		List<Model> list = sqlSession.selectList("mapper.ModelMapper.findModelAll");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertModel(Model model) throws Exception {
		sqlSession.insert("mapper.ModelMapper.insertModel",model);
		sqlSession.commit();
		sqlSession.close();
	}
	@Override
	public void deleteModelByModelID(int modelID) throws Exception {
		sqlSession.delete("mapper.ModelMapper.deleteModelByModelID",modelID);
		sqlSession.commit();
		sqlSession.close();
	}
}
