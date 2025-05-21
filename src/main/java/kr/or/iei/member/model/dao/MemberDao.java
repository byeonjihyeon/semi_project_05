package kr.or.iei.member.model.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UserGrowth;

public class MemberDao {

	public Member loginChk(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_member where member_id = ?";
		Member loginM = null;
				
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {

				loginM = new Member();
				loginM.setMemberAddr(rset.getString("member_addr"));
				loginM.setMemberPw(rset.getString("member_pw"));
				loginM.setMemberDate(rset.getString("enrolldate"));
				loginM.setMemberEmail(rset.getString("member_email"));
				loginM.setMemberGrade(rset.getString("member_grade"));
				loginM.setMemberId(m.getMemberId());
				loginM.setMemberName(rset.getString("member_name"));
				//m.setMemberNickname(rset.getString("member_nickname"));
				loginM.setMemberPhone(rset.getString("member_phone"));
				loginM.setMemberPw(rset.getString("member_pw"));
				loginM.setReportedCnt(rset.getInt("reported_cnt"));
				loginM.setMemberType(rset.getInt("member_type"));
			
				
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
		
		String query = "insert into tbl_member values(?,?,?,'.',?,'회원',?,sysdate, default, 3)";
		
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

	public String searchToEmail(Connection conn, String userId, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select member_id, member_email from tbl_member where member_id = ? and member_email = ?";
		
		String toEmail = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				toEmail = rset.getString("member_email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return toEmail;
	}

	public int updateNewPw(Connection conn, String userId, String updateNewPw) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tbl_member set member_pw = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, updateNewPw);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_member set member_name = ?, member_phone = ?, member_email = ?, member_addr = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setString(4, m.getMemberAddr());
			pstmt.setString(5, m.getMemberId());
			
			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
				
				
		return result;
	}

	public int emailDuplChk(Connection conn, String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int cnt = 0;
		String query = "select count(*) cnt from tbl_member where member_email = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cnt = rset.getInt("cnt"); // 1
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		
		return cnt;
	}

	public String pwDuplChk(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String pwChk = null;
		
		String query = "select member_pw from tbl_member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pwChk = rset.getString("member_pw");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwChk;
	}

	public int updatePw(Connection conn, String userId, String updateNewPw) {
		PreparedStatement pstmt = null;
		int result = 0 ;
		
		String query = "update tbl_member set member_pw = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, updateNewPw);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete tbl_member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int insertGrowth(Connection conn, UserGrowth growth, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		int result = 0;
		
		String query = "insert into tbl_my_history values(seq_my_history.nextval, ?, sysdate, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, growth.getMemberTall());
			pstmt.setString(3, growth.getMemberWeight());
			pstmt.setString(4, growth.getMemberHopeWeight());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<UserGrowth> searchHistory(Connection conn, UserGrowth growth) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UserGrowth> list = new ArrayList<UserGrowth>();
		String query = "select * from tbl_member join tbl_my_history using(member_id) where member_id = ? and height is not null";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, growth.getMemberId());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				UserGrowth uG = new UserGrowth();
				uG.setRecordDate(rset.getString("RECODE_DATE"));
				uG.setMemberTall(rset.getString("HEIGHT"));
				uG.setMemberWeight(rset.getString("WEIGHT"));
				uG.setMemberHopeWeight(rset.getString("GOAL_WEIGHT"));
				uG.setMemberId(rset.getString("member_id"));
				
				list.add(uG);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}



	

}
