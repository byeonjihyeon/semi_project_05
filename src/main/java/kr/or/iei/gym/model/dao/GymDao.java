package kr.or.iei.gym.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.GymTicket;

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

	public Gym loginChkGym(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_gym where gym_id = ?";
		//아이디가 존재하면 gym 반환, 그렇지 않으면 null 반환
		Gym loginGym = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
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
				loginGym.setFacilities(rset.getString("FACILITIES"));
				 
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
	public Gym selectOneGym(Connection conn, String gymId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_gym where gym_id = ?";
		Gym gym = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gymId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				gym = new Gym();
				gym.setGymId(rset.getString("gym_id"));
				gym.setGymPw(rset.getString("gym_pw"));
				gym.setGymName(rset.getString("gym_name"));
				gym.setGymAddr(rset.getString("gym_addr"));
				gym.setGymMemberCnt(rset.getInt("gym_member"));
				gym.setGymEnrollDate(rset.getString("gym_enrolldate"));
				gym.setApprovalCode(rset.getString("approval_code"));
				gym.setEmail(rset.getString("gym_email"));
				gym.setPhone(rset.getString("gym_phone"));
				gym.setOpenTime(rset.getString("open_time"));
				gym.setDetail(rset.getString("detail"));
				gym.setFacilities(rset.getString("FACILITIES"));
				 
				System.out.println(gym.getGymId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gym;
	}
	public List<Gym> selectAllGym(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_gym";
		//아이디가 존재하면 gym 반환, 그렇지 않으면 null 반환
		List<Gym> gymList = new ArrayList<Gym>();
		Gym loginGym = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
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
				loginGym.setFacilities(rset.getString("FACILITIES"));
				 
				System.out.println(loginGym.getGymId());
				
				gymList.add(loginGym);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return gymList;		
	}
	public GymTicket selectGymTicket(Connection conn, String gymId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_ticket where gym_id = ?";
		GymTicket ticket = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gymId);
			
			rset = pstmt.executeQuery();
			
			ticket = new GymTicket();
			while(rset.next()) {
				
				String ticket_period = rset.getString("ticket_period");
				
				if(ticket_period.equals("oneDay")) {
					ticket.setOneDay(rset.getString("ticket_price"));
				}else if(ticket_period.equals("oneMonth")) {
					ticket.setOneMonth(rset.getString("ticket_price"));
				}else if(ticket_period.equals("threeMonth")) {
					ticket.setThreeMonth(rset.getString("ticket_price"));
				}else if(ticket_period.equals("sixMonth")) {
					ticket.setSixMonth(rset.getString("ticket_price"));
				}else if(ticket_period.equals("oneYear")) {
					ticket.setOneYear(rset.getString("ticket_price"));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ticket;
	}
	public int updateGym(Connection conn, Gym gym) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tbl_gym set gym_name = ?, gym_addr = ?, gym_email = ?, gym_phone = ?, open_time = ?, detail = ?, facilities = ? where gym_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gym.getGymName());
			pstmt.setString(2, gym.getGymAddr());
			pstmt.setString(3, gym.getEmail());
			pstmt.setString(4, gym.getPhone());
			pstmt.setString(5, gym.getOpenTime());
			pstmt.setString(6, gym.getDetail());
			pstmt.setString(7, gym.getFacilities());
			pstmt.setString(8, gym.getGymId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateGymTicket(Connection conn, GymTicket ticket) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String gymId = ticket.getGymId();
		String oneDay = ticket.getOneDay();
		String oneMonth = ticket.getOneMonth();
		String threeMonth = ticket.getThreeMonth();
		String sixMonth = ticket.getSixMonth();
		String oneYear = ticket.getOneYear();
		
		String[] ticketPrice = {oneDay, oneMonth, threeMonth, sixMonth, oneYear};
		String[] ticketName = {"oneDay", "oneMonth", "threeMonth", "sixMonth", "oneYear"};
		
		String query = "insert into tbl_ticket values (seq_tbl_ticket.nextval, ?, ?, ?, sysdate)";
		try {
			
			for(int i=0; i<5; i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, gymId);
				pstmt.setString(2, ticketPrice[i]);
				pstmt.setString(3, ticketName[i]);
				
				result = pstmt.executeUpdate();
				if(result <= 0) {
					break;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return result;
	}

	public int updateMemberPw(Connection conn, String gymId, String newGymPw) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_gym set gym_pw= ? where gym_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newGymPw);
			pstmt.setString(2, gymId);
			
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
