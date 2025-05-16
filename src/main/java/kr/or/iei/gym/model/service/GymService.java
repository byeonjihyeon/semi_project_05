package kr.or.iei.gym.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.gym.model.dao.GymDao;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymFile;

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
	
	
}
