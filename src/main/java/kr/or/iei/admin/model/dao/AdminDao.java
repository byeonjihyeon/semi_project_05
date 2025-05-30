package kr.or.iei.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymApplication;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.member.model.vo.Member;

public class AdminDao {
	
	//관리자(각 url마다 권한을 포함하려 arraylist사용) 조회 (아이디,패스워드)
	public ArrayList<Admin> searchAdmin(Connection conn, String adminId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Admin> loginAdmin = new ArrayList<Admin>();
		
		//String query = "select * from tbl_member where member_id =? and member_pw =? and member_type in (0,1)";
		String query = "select * from tbl_member join tbl_admin_job using (member_id) where member_id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			
			rset = pstmt.executeQuery();
			//[0] => 슈퍼관리자 권한 url, [1] => 회원 권한 url [2]=>헬스 권한url [3] => 게시판 권한url
			while(rset.next()) {
				Admin admin = new Admin();
				
				admin.setMemberId(adminId);
				admin.setMemberPw(rset.getString("member_pw"));
				admin.setMemberAddr(rset.getString("member_addr"));
				admin.setMemberDate(rset.getString("enrolldate"));
				admin.setMemberEmail(rset.getString("member_email"));
				admin.setMemberGrade(rset.getString("member_grade"));
				admin.setMemberName(rset.getString("member_name"));
				admin.setMemberPhone(rset.getString("member_phone"));
				
				
				admin.setJobCode(rset.getString("job_code"));
				admin.setUrl(rset.getString("url"));
				admin.setSelYN(rset.getString("sel_Yn"));
				admin.setInsYN(rset.getString("ins_Yn"));
				admin.setUpdYN(rset.getString("upd_Yn"));
				admin.setDelYN(rset.getString("del_Yn"));
				admin.setRegDate(rset.getString("reg_date"));
				
				loginAdmin.add(admin);
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
	
	//전체회원 조회(관리자 제외)
	public ArrayList<Member> selectMemberList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		//가입일순으로 10명씩 조회
		String query = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT * FROM TBL_Member A where member_type=2 ORDER BY enrolldate DESC) A ) WHERE RNUM >=? AND RNUM <=?";
		
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
				//m.setMemberNickname(rset.getString("member_nickname"));
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
				//srchMember.setMemberNickname(rset.getString("member_nickname"));
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

	public ArrayList<Member> searchMembers(Connection conn, String field, String inputValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		String query = null;
		
		if(field.equals("member_id")) {
			query = "select * from tbl_member where member_type=2 and member_id like ?";
		}else {
			query = "select * from tbl_member where member_type=2 and member_name like ?";
		}
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%" + inputValue + "%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberDate(rset.getString("enrolldate"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberGrade(rset.getString("member_grade"));
				m.setMemberName(rset.getString("member_name"));
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

	public ArrayList<Admin> selectAdmins(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Admin> list = new ArrayList<Admin>();
		
		String query = "select * from tbl_member where member_type in (0,1)";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Admin admin = new Admin();
				
				admin.setMemberId(rset.getString("member_id"));
				admin.setMemberAddr(rset.getString("member_addr"));
				admin.setMemberDate(rset.getString("enrolldate"));
				admin.setMemberEmail(rset.getString("member_email"));
				admin.setMemberGrade(rset.getString("member_grade"));
				admin.setMemberName(rset.getString("member_name"));
				admin.setMemberPhone(rset.getString("member_phone"));
				/*
				admin.setJobCode(rset.getString("job_code"));
				admin.setUrl(rset.getString("url"));
				admin.setSelYN(rset.getString("sel_Yn"));
				admin.setInsYN(rset.getString("ins_Yn"));
				admin.setUpdYN(rset.getString("upd_Yn"));
				admin.setDelYN(rset.getString("del_Yn"));
				admin.setRegDate(rset.getString("reg_date"));
				*/
				list.add(admin);
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return list;
	}

	//1. 아이디로 관리자 조회 (권한 포함)
	public ArrayList<Admin> searchIdAdmin(Connection conn, String adminId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Admin> list = new ArrayList<Admin>();
		
		String query = "select * from tbl_member join tbl_admin_job using (member_id) where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adminId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Admin admin = new Admin();
				admin.setMemberId(rset.getString("member_id"));
				admin.setMemberAddr(rset.getString("member_addr"));
				admin.setMemberDate(rset.getString("enrolldate"));
				admin.setMemberEmail(rset.getString("member_email"));
				admin.setMemberGrade(rset.getString("member_grade"));
				admin.setMemberName(rset.getString("member_name"));
				admin.setMemberPhone(rset.getString("member_phone"));
				admin.setJobCode(rset.getString("job_code"));
				admin.setUrl(rset.getString("url"));
				admin.setSelYN(rset.getString("sel_Yn"));
				admin.setInsYN(rset.getString("ins_Yn"));
				admin.setUpdYN(rset.getString("upd_Yn"));
				admin.setDelYN(rset.getString("del_Yn"));
				admin.setRegDate(rset.getString("reg_date"));
				list.add(admin);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int updateAdminPreviliege(Connection conn, Admin admin) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_admin_job set sel_yn =?, upd_yn =?, del_yn =? where member_id =? and url = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, admin.getSelYN());
			pstmt.setString(2, admin.getUpdYN());
			pstmt.setString(3, admin.getDelYN());
			pstmt.setString(4, admin.getMemberId());
			pstmt.setString(5, admin.getUrl());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		
		return result;
	}

	public int createAdmin(Connection conn, Admin admin) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "insert into tbl_member values (?, ?, ? , null, ?, '미정', '010-1234-1234', sysdate, default, 1)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, admin.getMemberId() );
			pstmt.setString(2, admin.getMemberPw() );
			pstmt.setString(3, admin.getMemberName() );
			pstmt.setString(4, admin.getMemberEmail());
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int createPreviliges(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String [] urlArr = {"/admin/super", "/admin/member", "/admin/gym", "/admin/board"};
		
		String query = "insert into tbl_admin_job values (? , 'J5', ?, 'N', 'N', 'N', 'N', sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			for(int i =0; i<urlArr.length; i++) {
				pstmt.setString(1, memberId);
				pstmt.setString(2, urlArr[i]);
				result = pstmt.executeUpdate();
				
				if(result == 0) {
					return 0;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int changeGrade(Connection conn, String id, String grade) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_member set member_grade =? where member_id =?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, grade);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateAdminPw(Connection conn, String adminId, String newAdminPw) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tbl_member set member_pw =? where member_id =?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, newAdminPw);
			pstmt.setString(2, adminId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
	
		return result;
	}

	//등록신청한 헬스장'들' 조회
	public ArrayList<Gym> selectGymApplications(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Gym> list = new ArrayList<Gym>();
		
		String query = "SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT * FROM TBL_gym join tbl_applygym using(gym_id) ORDER by judge_id DESC) A ) WHERE RNUM >=? AND RNUM <=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Gym gym = new Gym();
				gym.setGymName(rset.getString("gym_name"));
				gym.setGymId(rset.getString("gym_id"));
				gym.setGymAddr(rset.getString("gym_addr"));
				gym.setGymMemberCnt(rset.getInt("gym_member"));
				gym.setGymEnrollDate(rset.getString("gym_enrolldate"));
				gym.setApprovalCode(rset.getString("approval_code"));
				gym.setEmail(rset.getString("gym_email"));
				gym.setPhone(rset.getString("gym_phone"));
				gym.setOpenTime(rset.getString("open_time"));
				gym.setDetail(rset.getString("detail"));
				gym.setFacilities(rset.getString("FACILITIES"));
				
				GymApplication application = new GymApplication();
				application.setGymId(rset.getString("gym_id"));
				application.setInsertGymNo(rset.getString("insert_gym_no"));
				application.setScreeningDate(rset.getString("screening_date"));
				application.setJudgeId(rset.getString("judge_id"));
				
				gym.setGymApplication(application);
				
				list.add(gym);
				
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

	public int selectTotalApplications(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int cnt = 0;
		
		String query = "select count(*) as cnt FROM TBL_gym join tbl_applygym using(gym_id)";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			rset.next();
			int total = rset.getInt("cnt");
			
			cnt = total;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cnt;
	}

	//등록신청한 하나의 헬스장 조회	
	public Gym selectGymApplication(Connection conn, String applyNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Gym gym = null;
		
		String query = "select * FROM tbl_gym join tbl_applygym using(gym_id) where insert_gym_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, applyNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				gym = new Gym();
			
				gym.setGymId(rset.getString("gym_id"));
				gym.setGymName(rset.getString("gym_name"));
				gym.setGymAddr(rset.getString("gym_addr"));
				gym.setGymEnrollDate(rset.getString("gym_enrolldate"));
				gym.setApprovalCode(rset.getString("approval_code"));
				gym.setEmail(rset.getString("gym_email"));
				gym.setPhone(rset.getString("gym_phone"));
				gym.setOpenTime(rset.getString("open_time"));
				gym.setDetail(rset.getString("detail"));
				gym.setFacilities(rset.getString("FACILITIES"));
				
				GymApplication application = new GymApplication();
				application.setGymId(rset.getString("gym_id"));
				application.setInsertGymNo(rset.getString("insert_gym_no"));
				application.setScreeningDate(rset.getString("screening_date"));
				application.setJudgeId(rset.getString("judge_id"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return gym;
	}

	public ArrayList<GymFile> selectGymApplicatonsFiles(Connection conn, String applyNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		return null;
	}

	
	
}
