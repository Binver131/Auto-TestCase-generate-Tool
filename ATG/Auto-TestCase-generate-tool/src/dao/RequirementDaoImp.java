package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Model;
import bean.Requirement;

public class RequirementDaoImp implements RequirementDao{
	private SqlSession sqlSession;
    public RequirementDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Requirement> findRequirementByRowRequirementID(int rowRequirementID) throws Exception {
		List<Requirement> list = sqlSession.selectList("mapper.RequirementMapper.findRequirementByRowRequirementID");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertRequirement(Requirement requirement) throws Exception {
		sqlSession.insert("mapper.RequirementMapper.insertRequirement",requirement);
		sqlSession.commit();
		sqlSession.close();
	}
	@Override
	public void deleteRequirementByModelID(int modelID) throws Exception {
		sqlSession.delete("mapper.RequirementMapper.deleteRequirementByModelID",modelID);
		sqlSession.commit();
		sqlSession.close();
	}
}
