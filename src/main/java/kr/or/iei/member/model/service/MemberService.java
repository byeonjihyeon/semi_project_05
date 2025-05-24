package kr.or.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;
import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UserGrowth;

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

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);

		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int emailDuplChk(String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		int cnt = dao.emailDuplChk(conn,userEmail);
		
		
		JDBCTemplate.close(conn);
		return cnt;
	}

	public String pwDuplChk(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		String pwChk = dao.pwDuplChk(conn, userId);
		JDBCTemplate.close(conn);
		return pwChk;
	}

	public int updatePw(String userId, String updatePw) {
		Connection conn = JDBCTemplate.getConnection();
		String updateNewPw = BCrypt.hashpw(updatePw, BCrypt.gensalt());
		int result = dao.updatePw(conn, userId, updateNewPw);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertGrowth(UserGrowth growth, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertGrowth(conn, growth, memberId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<UserGrowth> selectGrowthList(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
	    ArrayList<UserGrowth> list = dao.selectGrowthList(conn, memberId);
	    JDBCTemplate.close(conn);
	    return list;
		
	}

	public ArrayList<GymFile> searchFile(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<GymFile> memberFile = dao.searchFile(conn, memberId);
		JDBCTemplate.close(conn);
		return memberFile;
	}

	public ArrayList<Usage> searchHistory(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Usage> memberHistory = dao.searchHistory(conn, memberId);
		JDBCTemplate.close(conn);
		return memberHistory;
	}

	public ArrayList<Payment> searchPay(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Payment> memberPay = dao.searchPay(conn, memberId);
		JDBCTemplate.close(conn);
		return memberPay;
	}

	public ArrayList<GymFile> loadFile(String memberId, String paymentId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<GymFile> memberFile = dao.loadFile(conn, memberId, paymentId);
		JDBCTemplate.close(conn);
		return memberFile;
	}

	public ArrayList<Usage> loadHistory(String memberId, String paymentId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Usage> memberHistory = dao.loadHistory(conn, memberId, paymentId);
		JDBCTemplate.close(conn);
		return memberHistory;
	}

	public ArrayList<Payment> loadPay(String memberId, String paymentId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Payment> memberPay = dao.loadPay(conn, memberId, paymentId);
		JDBCTemplate.close(conn);
		return memberPay;
	}

	



	
	
}
