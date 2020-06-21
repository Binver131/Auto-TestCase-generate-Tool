package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import bean.Testcase;

public class TestcaseDaoImp implements TestcaseDao{
	private SqlSession sqlSession;
    public TestcaseDaoImp(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	@Override
	public List<Testcase> findTestcaseByRequirementID(int requirementID) throws Exception {
		List<Testcase> list = sqlSession.selectList("mapper.TestcaseMapper.findTestcaseByRequirementID");
        sqlSession.close();
        return list;
	}
	@Override
	public void insertTestcase(Testcase testcase) throws Exception {
		sqlSession.insert("mapper.TestcaseMapper.insertTestcase", testcase);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
}
