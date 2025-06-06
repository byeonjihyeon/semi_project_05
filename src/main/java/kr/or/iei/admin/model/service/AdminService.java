package kr.or.iei.admin.model.service;

import java.security.SecureRandom;
import java.sql.Connection;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.common.ListData;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.member.model.vo.Member;

public class AdminService {
	private AdminDao dao;

	public AdminService() {
		dao = new AdminDao();
	}

	//관리자 회원 1명 조회
	public ArrayList<Admin> searchAdmin(String adminId, String adminPw) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Admin> loginAdmin = dao.searchAdmin(conn, adminId);
		JDBCTemplate.close(conn);
		
		if(loginAdmin.isEmpty()) {
			return null;
		}else {
			boolean pwChk = BCrypt.checkpw(adminPw, loginAdmin.get(0).getMemberPw());
			
			if(pwChk) {
				return loginAdmin;
			}else {
				return null;
			}
		}
	}

	public ListData<Member> selectMemberList(int page) {
		Connection conn = JDBCTemplate.getConnection();
		
		//한 페이지에 보여줄 회원수 10명
		int viewMemberCnt = 10;
		
		//요청페이지 행 끝번호
		int end = page * viewMemberCnt;
		//요청페이지 행 시작번호
		int start = end - viewMemberCnt + 1;
		
		//dao에 회원전체리스트 요청
		ArrayList<Member> list = dao.selectMemberList(conn, start, end);
		
		//페이지네이션 작업 < 1, 2, 3, 4, 5 >
		
		//전체 회원수 조회
		int totalMembers = dao.selectTotalMembers(conn);
		
		//전체 페이지수
		int totPage = 0;
		
		if(totalMembers % viewMemberCnt > 0) {
			totPage = totalMembers / viewMemberCnt + 1;
		}else {
			totPage = totalMembers / viewMemberCnt;
		}
		
		//페이지네이션 사이즈 1 2 3 4 5
		int pageNaviSize = 5;
		
		//요청페이지에 따른 시작번호
		int pageNo = ((page-1) / pageNaviSize) * pageNaviSize + 1;
		
		//페이지 하단에 보여줄 페이지네이션 html 코드 작성
		String pageNavi = "<ul class='pagination square-style'>";
		
		//이전 버튼 (시작번호 6, 11, ...)
		if(page != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/member/list?page=" + (page-1) +"'>";
			pageNavi += "<span class='material-icons'>" + "<" + "</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			
			//페이지 번호 작성 중, 사용자가 요청한 페이지일 때, 클래스를 다르게 지정하여 시각적인 효과
			if(pageNo == page) {
				pageNavi += "<a class='page-item active-page' href='/admin/member/list?page=" + pageNo + "'>";
			}else {
				pageNavi += "<a class='page-item' href='/admin/member/list?page=" + pageNo + "'>";
			}
			
			//시작태그와 종료 태그 사이에 작성되는 값
			pageNavi += pageNo; 
			pageNavi += "</a></li>";
			
			pageNo++;
			
		
			//설정한 pageNaviSize만큼 항상 그리지 않고, 마지막 페이지 그렸으면 더 이상 생성하지 않도록 처리
			if(pageNo > totPage) {
				break;
			}
		}
		
		//다음 버튼
		if(pageNo <= totPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/admin/member/list?page=" + pageNo + "'>";
			pageNavi += "<span class='material-icons'>" + ">" +"</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		//서블릿으로 리턴해야 하는 값 => 회원 전체 리스트와 페이지 하단에 보여줄 페이지네이션(pageNavi)
		ListData<Member> listData = new ListData<Member>();
		listData.setList(list);
		listData.setPageNavi(pageNavi);
		listData.setCurrentPage(page);
		listData.setPageSize(viewMemberCnt);
		
		JDBCTemplate.close(conn);
		return listData;
	}
	
	//아이디로 회원 조회
	public Member selectMember(String searchId) {
		Connection conn = JDBCTemplate.getConnection();
		Member srchMember = dao.selectMember(conn, searchId);
		JDBCTemplate.close(conn);
		
		return srchMember;
	}
	
	//아이디로 회원 삭제
	public int deleteMember(String deleteId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.deleteMember(conn, deleteId);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateMember(Member mdfMember) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, mdfMember);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Member> searchMembers(String field, String inputValue) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = dao.searchMembers(conn, field, inputValue);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}

	public ArrayList<Admin> selectAdmins() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Admin> list = dao.selectAdmins(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Admin> searchIdAdmin(String adminId) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Admin> list = dao.searchIdAdmin(conn, adminId);
		JDBCTemplate.close(conn);
		return list;
	}

	public int updateAdminPreviliege(Admin admin) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateAdminPreviliege(conn, admin);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int createAdmin(Admin admin) {
		Connection conn = JDBCTemplate.getConnection();
		
		String encPw = BCrypt.hashpw(admin.getMemberPw(), BCrypt.gensalt());
		admin.setMemberPw(encPw);
		
		int result = -1;
		
		//회원 테이블에 관리자 등록
		result = dao.createAdmin(conn, admin);
		
		if(result > 0) {
			//권한 부여
			result = dao.createPreviliges(conn, admin.getMemberId());
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else{
				JDBCTemplate.rollback(conn);
			}
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

	//임시 비밀번호(10자리) 만들기 
	public String makeRandomPw() {
		String upper = "ABCDEFGHIZKLMNOPQRSTUVWXYZ"; 
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String digit = "0123456789";
		String special = "!@#$";
		
		String allChar = upper + lower + digit + special;
		
		//난수 발생 객체 
		SecureRandom random = new SecureRandom();
		//임시 비밀번호 10자리 저장 객체
		StringBuilder randomPw = new StringBuilder();
		
		//영대소문자, 숫자, 특수문자 각각 최소 1개씩은 임시 비밀번호에 포함되도록 처리
		randomPw.append(upper.charAt(random.nextInt(upper.length())));
		randomPw.append(lower.charAt(random.nextInt(lower.length())));
		randomPw.append(digit.charAt(random.nextInt(digit.length())));
		randomPw.append(special.charAt(random.nextInt(special.length())));
		
		//위에 4자리 추가후, 나머지 6자리 임시 비밀번호 발행 처리
		for(int i=0; i<6; i++) {
			//전체 문자열에서 무작위 추출하여 추가
			randomPw.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		
		//발행된 임시비밀번호 10자리를 무작위로 섞기
		char [] charArr = randomPw.toString().toCharArray();
		for(int i=0; i<charArr.length; i++) {
			//0~9 난수
			int randomIdx = random.nextInt(charArr.length);
			
			char temp = charArr[i];
			charArr[i] = charArr[randomIdx];
			charArr[randomIdx] = temp;
		}
		
		//최종 임시 비밀번호
		String newRandomPw = new String(charArr);
		
		return newRandomPw;
	}

	public int changeGrade(String id, String grade) {
		Connection conn = JDBCTemplate.getConnection();
		
		switch(grade) {
		case "allMng" : grade = "총괄 관리자";
				break;
		case "memberMng" : grade = "회원 관리자";
				break;
		case "gymMng" : grade = "헬스장 관리자";
			break;
		case "boardMng" : grade = "게시판 관리자";
			break;
		case "noMng" : grade = "미정";
			break;	
		}
		
		int result = dao.changeGrade(conn, id, grade);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateAdminPw(String adminId, String newAdminPw) {
		Connection conn = JDBCTemplate.getConnection();
		
		newAdminPw = BCrypt.hashpw(newAdminPw, BCrypt.gensalt());
		
		int result = dao.updateAdminPw(conn, adminId, newAdminPw);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ListData<Gym> selectGymApplications(int page) {
		Connection conn = JDBCTemplate.getConnection();
		
		//한 페이지 보여줄 헬스장 등록내역 10개
		int viewGymApplicationsCnt = 20;
		
		//요청페이지 행 끝번호
		int end = page * viewGymApplicationsCnt;
		//요청페이지 행 시작번호 
		int start = end - viewGymApplicationsCnt + 1;
		
		
		//dao에 등록신청헬스장리스트 요청
		ArrayList<Gym> list = dao.selectGymApplications(conn, start, end);
		
		//페이지네이션 작업 < >
		
		//전체 신청내역 수 조회
		int totalApplications = dao.selectTotalApplications(conn);
		
		//전체 페이지수
		int totPage = 0;
		
		if(totalApplications % viewGymApplicationsCnt > 0) {
			totPage = totalApplications / viewGymApplicationsCnt + 1;
		}else {
			totPage = totalApplications / viewGymApplicationsCnt;
		}
		
		//페이지 하단에 보여줄 페이지네이션 html 코드 작성
		
		
		String pageNavi = "";
			//이전 버튼
			if(page > 1) {
				pageNavi += "<a href='/admin/gym/applications?page="+ (page-1) +"><span>"+"<"+"</span></a>"; 
			}
			
			//다음 버튼
			if(page <= totPage) {
				pageNavi += "<a href='/admin/gym/applications?page="+ (page+1) +"><span>"+">"+"</span></a>";
				if(page == totPage) {
					pageNavi += "";
				}
			}
			
		//서블릿으로 리턴해야 하는 값 => 헬스장 신청내역 리스트와 페이지 하단에 보여줄 페이지버튼(pageNavi)
		ListData<Gym> listData = new ListData<Gym>();
		listData.setList(list);
		listData.setPageNavi(pageNavi);
		listData.setCurrentPage(page);
		listData.setPageSize(viewGymApplicationsCnt);
		
		JDBCTemplate.close(conn);
		
		return listData;
	}

	public Gym selectGymApplication(String applyNo) {
		Connection conn = JDBCTemplate.getConnection();
		Gym gym = dao.selectGymApplication(conn, applyNo);
		ArrayList<GymFile> applicationFiles = dao.selectGymApplicatonsFiles(conn, applyNo);
		
		JDBCTemplate.close(conn);
		
		return gym;
	}
	
	
	

	


	
	
	

}
