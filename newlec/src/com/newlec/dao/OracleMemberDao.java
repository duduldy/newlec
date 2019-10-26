package com.newlec.dao;								
								
import java.util.List;								
								
import org.apache.ibatis.session.SqlSession;								
import org.apache.ibatis.session.SqlSessionFactory;								
import org.apache.log4j.Logger;								
								
import com.newlec.config.SqlMapConfig;								
import com.newlec.domain.MemberVO;								
								
public class OracleMemberDao implements MemberDao {								
								
	static final Logger log = Logger.getLogger("");							
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();							
	SqlSession sqlsession; 							
								
	public OracleMemberDao() {							
		sqlsession = sessionFactory.openSession(true); // sqlSession 얻어오기ㅇㅇㅇ				
	}							
								
	@Override							
	public String retrieveDate() throws Exception {							
		// TODO Auto-generated method stub	oko					
								
		String result = sqlsession.selectOne("selectDate");						
		log.info(result);						
		return result;						
	}							
								
	@Override							
	public void getMemberList() {							
		// TODO Auto-generated method stub						
		List<MemberVO> memberVO = sqlsession.selectList("getListMember");						
		log.info(memberVO);						
	}							
								
	@Override							
	public void insertMember(MemberVO member) {							
		// TODO Auto-generated method stub						
								
		log.info("★멤버★ : " + member);						
								
		sqlsession.insert("insertMember", member);						
	}							
								
}								
