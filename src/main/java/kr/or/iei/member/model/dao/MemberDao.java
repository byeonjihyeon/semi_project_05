package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;

public class MemberDao {

	public Member loginChk(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_member where member_id = ? and member_pw = ?";
		Member loginM = null;
				
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginM = new Member();
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberDate(rset.getString("enrolldate"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberGrade(rset.getString("member_grade"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setReportedCnt(rset.getInt("reported_cnt")
			);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
				
		return loginM;
	}

	public int idDuplChk(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) cnt from tbl_member where member_id = ?";
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cnt;
	}

	public int joinMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into tbl_member values(?,?,?,'null',?,'회원',?,sysdate,0)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberEmail());
			pstmt.setString(5, m.getMemberPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int nickNameDuplChk(Connection conn, String userNickName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int cnt = 0;
		
		String query = "select count(*) cnt from tbl_member where member_nickname = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userNickName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return cnt;
	}

	public String searchId(Connection conn, String userName, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String memberId = null;
		
		String query = "select member_id, member_Email from tbl_member where member_name = ? and member_email = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memberId = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return memberId;
	}

	

}
