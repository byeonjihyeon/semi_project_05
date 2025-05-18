package kr.or.iei.member.model.service;

import java.sql.Connection;

import org.mindrot.jbcrypt.BCrypt;

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
		
		System.out.println(loginM.getMemberId());
		System.out.println(loginM.getMemberPw());

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
		String memberPw = BCrypt.hashpw(m.getMemberPw(), BCrypt.gensalt());
		m.setMemberPw(memberPw);
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

	public String searchToEmail(String userId, String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		String toEmail = dao.searchToEmail(conn, userId, userEmail);
		JDBCTemplate.close(conn);
		return toEmail;
	}

	public int updateNewPw(String userId, String newRandomPw) {
		Connection conn = JDBCTemplate.getConnection();
		String updateNewPw = BCrypt.hashpw(newRandomPw, BCrypt.gensalt());
		
		int result = dao.updateNewPw(conn, userId, updateNewPw);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	
	
}
