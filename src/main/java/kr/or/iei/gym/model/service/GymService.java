package kr.or.iei.gym.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.dao.GymDao;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.GymTicket;

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

	public int insertGym(Gym gym, ArrayList<GymFile> fileList) {
		Connection conn = JDBCTemplate.getConnection();
		String gymPw = BCrypt.hashpw(gym.getGymPw(), BCrypt.gensalt());
		gym.setGymPw(gymPw);;
		
		
		//(2) tbl_notice에 insert(게시글 정보 선등록)
		int result = dao.insertGym(conn, gym);
		
		if(result > 0) {
			for(GymFile file : fileList) {
				
				//(3) tbl_notice_file에 insert(게시글에 대한 파일 등록)
				result = dao.insertGymFile(conn, file);
				
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
		
		JDBCTemplate.close(conn);
		
		return result;
		
		
		
	}

	public Gym loginChkGym(String userId, String password) {
		Connection conn = JDBCTemplate.getConnection();
		Gym loginGym = dao.loginChkGym(conn, userId);
		//사용자가 입력한 비번이 암호화된 비번과 일치하지 않으면 조회해온 로그인 객체에 null로 변경
		if(loginGym != null) {
			if(!BCrypt.checkpw(password, loginGym.getGymPw())) {
				loginGym = null;
			}
			GymTicket ticket = dao.loginGymTicket(conn, userId);
			if(ticket != null) {
				loginGym.setTicket(ticket);
			}
		}
		
		JDBCTemplate.close(conn);
		
		return loginGym;
	}

	public int updateGym(Gym gym, ArrayList<GymFile> fileList) {
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

	    for (GymFile file : fileList) {
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
	
	
}
