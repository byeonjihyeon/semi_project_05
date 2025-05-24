package kr.or.iei.gym.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.dao.GymDao;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymApplyFile;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.GymTicket;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;

public class GymService {
	
	
	
	private GymDao dao;
	
	public GymService() {
		dao = new GymDao();
	}

	public int idDuplChk(String gymId) {
		Connection conn = JDBCTemplate.getConnection();
		int cnt = dao.idDuplChk(conn, gymId);
		JDBCTemplate.close(conn);
		return cnt;
	}

	
	

	
	
	public int registerGym(Gym gym, ArrayList<GymApplyFile> fileList) {
		Connection conn = JDBCTemplate.getConnection();
		String gymPw = BCrypt.hashpw(gym.getGymPw(), BCrypt.gensalt());
		gym.setGymPw(gymPw);
		
		
		//(2) tbl_notice에 insert(게시글 정보 선등록)
		int result = dao.insertGym(conn, gym);
		
		if(result > 0) {
			result = dao.insertApplyGym(conn, gym.getGymId());
			if(result >= 1) {
				String insertApplyNo = String.valueOf(result);
				
				for(GymApplyFile file : fileList) {
					
					//(3) tbl_notice_file에 insert(게시글에 대한 파일 등록)
					result = dao.insertGymApplyFile(conn, file, insertApplyNo);
					
					//파일 정보 등록 중, 정상 수행되지 않았을 경우 모두 롤백처리하고, 메소드 종료
					if(result < 1) {
						JDBCTemplate.rollback(conn);
						JDBCTemplate.close(conn);
						return 0;
					}
				}
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
		
		
	}
	public Gym selectOneGym(String gymId) {
		Connection conn = JDBCTemplate.getConnection();
		Gym gym = dao.selectOneGym(conn, gymId);
		if(gym != null) {
			GymTicket ticket = dao.selectGymTicket(conn, gymId);
			gym.setTicket(ticket);
		}
		JDBCTemplate.close(conn);
		return gym;
	}
	
	public GymTicket selectTicket(String gymId) {
		Connection conn = JDBCTemplate.getConnection();
		GymTicket ticket = dao.selectGymTicket(conn, gymId);
		ticket.setGymId(gymId);		
		
		JDBCTemplate.close(conn);
		return ticket;
	}
	public Gym loginChkGym(String userId, String password) {
		Connection conn = JDBCTemplate.getConnection();
		Gym loginGym = dao.loginChkGym(conn, userId);
		//사용자가 입력한 비번이 암호화된 비번과 일치하지 않으면 조회해온 로그인 객체에 null로 변경
		if(loginGym != null) {
			if(!BCrypt.checkpw(password, loginGym.getGymPw())) {
				loginGym = null;
			}
			GymTicket ticket = dao.selectGymTicket(conn, userId);
			if(ticket != null) {
				loginGym.setTicket(ticket);
			}
		}
		
		JDBCTemplate.close(conn);
		
		return loginGym;
	}

	public int updateGym(Gym gym, ArrayList<GymFile> fileList, ArrayList<GymApplyFile> applyFileList) {
	    Connection conn = JDBCTemplate.getConnection();
	    int result = dao.updateGym(conn, gym);

	    if (result <= 0) {
	        JDBCTemplate.rollback(conn);
	        JDBCTemplate.close(conn);
	        return 0;
	    }

	    result = dao.updateGymTicket(conn, gym.getTicket());
	    if (result <= 0) {
	        JDBCTemplate.rollback(conn);
	        JDBCTemplate.close(conn);
	        return 0;
	    }
	    
	    result = dao.insertApplyGym(conn, gym.getGymId());
	    if (result <= 0) {
	        JDBCTemplate.rollback(conn);
	        JDBCTemplate.close(conn);
	        return 0;
	    }
	    
	    for (GymApplyFile file : applyFileList) {
	    	String applyGymNo = String.valueOf(result); 
	        result = dao.insertGymApplyFile(conn, file, applyGymNo);
	        if (result <= 0) {
	            JDBCTemplate.rollback(conn);
	            JDBCTemplate.close(conn);
	            return 0;
	        }
	        
	    }
	    
	    for (GymFile file : fileList) {
	    	System.out.println("service update gymfile의 gymId: "+ file.getGymId());
	        result = dao.insertGymFile(conn, file);
	        if (result <= 0) {
	            JDBCTemplate.rollback(conn);
	            JDBCTemplate.close(conn);
	            return 0;
	        }
	    }

	    

	    JDBCTemplate.commit(conn);
	    JDBCTemplate.close(conn);
	    return 1;  // 모든 작업 성공 시 1 반환
	}

	public int updateGymPw(String gymId, String newGymPw) {
		Connection conn = JDBCTemplate.getConnection();
		
		newGymPw = BCrypt.hashpw(newGymPw, BCrypt.gensalt());
		
		int result = dao.updateMemberPw(conn, gymId, newGymPw);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public List<Gym> selectAllGym() {
		Connection conn = JDBCTemplate.getConnection();
		
		List<Gym> gymList = dao.selectAllGym(conn);
		for(Gym gym: gymList) {
			GymTicket ticket = dao.selectGymTicket(conn, gym.getGymId());
			gym.setTicket(ticket);
			List<GymFile> fileList = dao.selectGymFile(conn, gym.getGymId());
			gym.setFileList(fileList);
		}
		
		JDBCTemplate.close(conn);
		return gymList;
	}

	public int insertPaymentInfo(Payment payment, Usage usage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = 0;
		System.out.println("service first result: " + result);
		result = dao.insertPayment(conn, payment);
		if(result > 0) {
			System.out.println("service 결제내역 db 처리 후 성공 result: " + result);

			result = dao.insertUsage(conn, usage);
			if(result > 0) {
				System.out.println("service 이용내역 db 처리 후 성공 result: " + result);

				JDBCTemplate.commit(conn);
				JDBCTemplate.close(conn);
				return result;
			}
		}
		JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		System.out.println("service 이용내역 db 처리 후 실패 result: " + result);
		return result;
	}

	public String selectTicketId(String gymId, String membership) {
		Connection conn = JDBCTemplate.getConnection();
		String ticketId = null;
		ticketId = dao.selectTicketId(conn, gymId, membership);
		JDBCTemplate.close(conn);
		return ticketId;
	}

	
	
	
}
