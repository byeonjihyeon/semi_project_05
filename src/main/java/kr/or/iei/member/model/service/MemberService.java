package kr.or.iei.member.model.service;

import java.sql.Connection;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

public class MemberService {
	private MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}

	public Member loginChk(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		Member loginM = dao.loginChk(conn, m);
		JDBCTemplate.close(conn);
		return loginM;
	}

	public int idDuplChk(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int cnt = dao.idDuplChk(conn, userId);
		
		JDBCTemplate.close(conn);
		return cnt;
	}

	public int joinMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.joinMember(conn, m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int nickNameDuplChk(String userNickName) {
		Connection conn = JDBCTemplate.getConnection();
		int cnt = dao.nickNameDuplChk(conn, userNickName);
		JDBCTemplate.close(conn);
		return cnt;
	}

	public String searchId(String userName, String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		String memberId = dao.searchId(conn, userName, userEmail);
		JDBCTemplate.close(conn);
		return memberId;
	}


	
	
}
