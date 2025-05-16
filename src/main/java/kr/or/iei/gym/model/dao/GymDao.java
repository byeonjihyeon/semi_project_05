package kr.or.iei.gym.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymFile;

public class GymDao {

	public int idDuplChk(Connection conn, String gymId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cnt = 0;
		
		String query = "select count(*) cnt from tbl_gym where gym_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, gymId);
			
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

	public int insertGym(Connection conn, Gym gym) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into tbl_gym (gym_id, gym_pw, gym_name, gym_addr, gym_email, gym_phone) values (?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, gym.getGymId());
			pstmt.setString(2, gym.getGymPw());
			pstmt.setString(3, gym.getGymName());
			pstmt.setString(4, gym.getGymAddr());
			pstmt.setString(5, gym.getEmail());
			pstmt.setString(6, gym.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertGymFile(Connection conn, GymFile file) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "insert into tbl_gym_file values (seq_gym_file.nextval, ?, ?, ?)";
	
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, file.getFileName());
			pstmt.setString(2, file.getFilePath());
			pstmt.setString(3, file.getGymId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Gym loginChkGym(Connection conn, String userId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_gym where gym_id = ? and gym_pw = ?";
		
		Gym loginGym = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginGym = new Gym();
				loginGym.setGymId(rset.getString("gym_id"));
				loginGym.setGymPw(rset.getString("gym_pw"));
				loginGym.setGymName(rset.getString("gym_name"));
				loginGym.setGymAddr(rset.getString("gym_addr"));
				loginGym.setGymMemberCnt(rset.getInt("gym_member"));
				loginGym.setGymEnrollDate(rset.getString("gym_enrolldate"));
				loginGym.setApprovalCode(rset.getString("approval_code"));
				loginGym.setEmail(rset.getString("gym_email"));
				loginGym.setPhone(rset.getString("gym_phone"));
				loginGym.setOpenTime(rset.getString("open_time"));
				loginGym.setDetail(rset.getString("detail"));
				loginGym.setConvenience(rset.getString("convenience"));
				 
				System.out.println(loginGym.getGymId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return loginGym;
	}
	
	

}
