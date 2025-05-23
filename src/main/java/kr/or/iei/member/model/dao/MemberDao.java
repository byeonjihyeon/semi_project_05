package kr.or.iei.member.model.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UserGrowth;

public class MemberDao {

	public Member loginChk(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from tbl_member join tbl_user_history using(member_id)"
				+ "              join tbl_gym_file using(gym_id) where member_id = ?";
		Member loginM = null;
		ArrayList<GymFile> gFile = new ArrayList<GymFile>();
		
				
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
		
		String query = "insert into tbl_member values(?,?,?,'.',?,'회원',?,sysdate, default, 2)";
		
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
		
		int result = 0;
		
		String query = "insert into tbl_my_history values(seq_my_history.nextval, ?, sysdate, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, growth.getMemberTall());
			pstmt.setString(3, growth.getMemberWeight());
			pstmt.setString(4, growth.getMemberHopeWeight());
			
			System.out.println(growth.getMemberTall());
			System.out.println(growth.getMemberWeight());
			System.out.println(growth.getMemberHopeWeight());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<UserGrowth> selectGrowthList(Connection conn, String memberId) {
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    ArrayList<UserGrowth> list = new ArrayList<>();

	    String query = "SELECT RECODE_DATE, height, weight, goal_weight "
	                 + "FROM tbl_my_history "
	                 + "WHERE member_id = ? "
	                 + "ORDER BY recode_date DESC";

	    try {
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, memberId);
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            UserGrowth ug = new UserGrowth();
	            ug.setGrowthDate(rset.getString("recode_date"));
	            ug.setMemberTall(rset.getString("height"));
	            ug.setMemberWeight(rset.getString("weight"));
	            ug.setMemberHopeWeight(rset.getString("goal_weight"));

	            list.add(ug);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }

	    return list;
	}

	public ArrayList<GymFile> searchFile(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<GymFile> memberFile = new ArrayList<GymFile>();
		String query = "select * from tbl_member join tbl_user_history using(member_id)"
				+ "              join tbl_gym_file using(gym_id) where member_id = ?";
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				GymFile gFile = new GymFile();
				
				
				gFile.setFileName(rset.getString("file_name"));
				gFile.setFileNo(rset.getString("file_no"));
				gFile.setFilePath(rset.getString("file_path"));
				gFile.setFileSavePath(rset.getString("file_save_path"));
				
				
				memberFile.add(gFile);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return memberFile;
	}

	public ArrayList<Usage> searchHistory(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Usage> memberHistory = new ArrayList<Usage>();
		
		
		String query = "select * from tbl_user_history join tbl_ticket using(ticket_kind) where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Usage usage = new Usage();
				
				
				
				usage.setUsageNo(rset.getString("user_history_no"));
				usage.setEnrollDate(rset.getString("ticket_insert_date"));
				String period = rset.getString("ticket_period");
				
				if(period.equals("oneDay")) {
					period = "1";
				}else if(period.equals("oneMonth")){
					period = "30";
				}else if(period.equals("threeMonth")) {
					period = "90";
				}else if(period.equals("sixMonth")) {
					period = "180";
				}else if(period.equals("oneYear")) {
					period = "360";
				}
				
				usage.setTicketPeriod(period);
				usage.setMemberIdRef(rset.getString("member_id"));
				usage.setTicketIdRef(rset.getString("ticket_kind"));
				usage.setGymIdRef(rset.getString("gym_id"));
				
				// 1. ticket_insert_date를 LocalDate로 가져온다고 가정
				LocalDate insertDate = rset.getDate("ticket_insert_date").toLocalDate();
				String ticketPeriodStr = rset.getString("ticket_period");
				
				// 2. 기간 문자열을 일 수로 변환
				Map<String, Integer> periodMap = new HashMap<>();
				periodMap.put("oneDay", 1);
				periodMap.put("oneMonth", 30);
				periodMap.put("threeMonth", 90);
				periodMap.put("sixMonth", 180);
				periodMap.put("oneYear", 360);
				
				// 3. 해당 기간 일 수 계산
				int periodDays = periodMap.getOrDefault(ticketPeriodStr, 0);
				
				// 4. 종료일 계산
				LocalDate endDate = insertDate.plusDays(periodDays);
				
				// 5. 현재 날짜 기준 남은 일수 계산
				long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), endDate);
				
				// 6. 내림 처리 (양수일 경우만 적용되므로 long 사용 시 floor 불필요)
				int daysLeft = (int) remainingDays;
				
				
				
				usage.setLeftDate(daysLeft);
				
				memberHistory.add(usage);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return memberHistory;
	}

	public ArrayList<Payment> searchPay(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Payment> memberPay = new ArrayList<Payment>();
		String query ="select * from tbl_member join tbl_buy using(member_id) where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Payment payInfo = new Payment();
				
				
				payInfo.setCardName(rset.getString("CARD_COMPANY_NAME"));
				payInfo.setMemberId(rset.getString("member_Id"));
				
				payInfo.setMerchantId(rset.getString("merchant_id"));
				payInfo.setPaymentDate(rset.getString("payment_date"));
				payInfo.setPaymentId(rset.getString("PAYMENT_HISTORY_NO"));
				payInfo.setPayMethod(rset.getString("PAY_METHOD"));
				payInfo.setTicketPrice(rset.getString("TICKET_PRICE"));
				
				memberPay.add(payInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return memberPay;
	}

	

	



	

}
