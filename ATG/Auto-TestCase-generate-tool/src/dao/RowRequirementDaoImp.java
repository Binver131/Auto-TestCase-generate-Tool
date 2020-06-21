package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Model;
import bean.RowRequirement;

public class RowRequirementDaoImp implements RowRequirementDao{
	private SqlSession sqlSession;
    public RowRequirementDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<RowRequirement> findRowRequirementByModelID(int ModelID) {
		List<RowRequirement> list = sqlSession.selectList("mapper.RowRequirementMapper.findRowRequirementByModelID");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertRowRequirement(RowRequirement rowRequirement) throws Exception {
		sqlSession.insert("mapper.RowRequirementMapper.insertRowRequirement",rowRequirement);
		sqlSession.commit();
		sqlSession.close();
	}
	@Override
	public void deleteRowRequirementByModelID(int modelID) throws Exception {
		sqlSession.insert("mapper.RowRequirementMapper.deleteRowRequirementByModelID",modelID);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
}
