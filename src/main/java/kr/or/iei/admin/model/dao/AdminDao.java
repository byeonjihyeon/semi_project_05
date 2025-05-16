package kr.or.iei.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;

public class AdminDao {

	public Admin adminLogin(Connection conn, String adminId, String adminPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Admin loginAdmin = null;
		
		//회원(관리자포함) 테이블과 관리자별 업무테이블 조인하여 select
		String query = "select * from tbl_member join tbl_admin_job using (member_id) where member_id =? and member_pw =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminPw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginAdmin = new Admin();
				
				loginAdmin.setMemberId(adminId);
				loginAdmin.setMemberPw(adminPw);
				loginAdmin.setMemberAddr(rset.getString("member_addr"));
				loginAdmin.setMemberDate(rset.getString("enrolldate"));
				loginAdmin.setMemberEmail(rset.getString("member_email"));
				loginAdmin.setMemberGrade(rset.getString("member_grade"));
				loginAdmin.setMemberName(rset.getString("member_name"));
				loginAdmin.setMemberNickname(rset.getString("member_nickname"));
				loginAdmin.setMemberPhone(rset.getString("member_phone"));
				loginAdmin.setReportedCnt(rset.getInt("reported_cnt"));
				loginAdmin.setJobCode(rset.getString("job_code"));
				loginAdmin.setUrl(rset.getString("url"));
				loginAdmin.setSelYN("sel_yn");
				loginAdmin.setInsYN("ins_yn");
				loginAdmin.setUpdYN("upd_yn");
				loginAdmin.setDelYN("del_yn");
				loginAdmin.setRegDate("reg_date");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return loginAdmin;
	}
	
	//전체회원 조회(관리자 포함)
	public ArrayList<Member> selectMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		//가입일순으로 10명씩 조회
		String query = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT * FROM TBL_Member A ORDER BY enrolldate DESC) A ) WHERE RNUM >=? AND RNUM <=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberDate(rset.getString("enrolldate"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberGrade(rset.getString("member_grade"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setReportedCnt(rset.getInt("reported_cnt"));
				
				list.add(m);
				
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
	
	//전체 회원수 조회
	public int selectTotalMembers(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totalMembers = 0;
		
		String query = "select count(*) as cnt from tbl_member";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			totalMembers = rset.getInt("cnt");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return totalMembers;
	}
	
	//아이디로 회원 조회
	public Member selectMember(Connection conn, String searchId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member srchMember = null;
		
		String query = "select * from tbl_member where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				srchMember = new Member();
				
				srchMember.setMemberId(rset.getString("member_id"));
				srchMember.setMemberPw(rset.getString("member_pw"));
				srchMember.setMemberAddr(rset.getString("member_addr"));
				srchMember.setMemberDate(rset.getString("enrolldate"));
				srchMember.setMemberEmail(rset.getString("member_email"));
				srchMember.setMemberGrade(rset.getString("member_grade"));
				srchMember.setMemberName(rset.getString("member_name"));
				srchMember.setMemberNickname(rset.getString("member_nickname"));
				srchMember.setMemberPhone(rset.getString("member_phone"));
				srchMember.setReportedCnt(rset.getInt("reported_cnt"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return srchMember;
	}
	
	//회원 1명 삭제 (아이디)
	public int deleteMember(Connection conn, String deleteId) {
		PreparedStatement pstmt = null;
		
		String query = "delete from tbl_member where member_id = ?";
		int result = 0;
		
		try {	
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deleteId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member mdfMember) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_member set member_name = ?, member_phone = ?, member_email =?, member_addr = ?, member_grade = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdfMember.getMemberName());
			pstmt.setString(2, mdfMember.getMemberPhone());
			pstmt.setString(3, mdfMember.getMemberEmail());
			pstmt.setString(4, mdfMember.getMemberAddr());
			pstmt.setString(5, mdfMember.getMemberGrade());
			pstmt.setString(6, mdfMember.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
