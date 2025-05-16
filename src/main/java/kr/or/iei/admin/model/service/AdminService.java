package kr.or.iei.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.admin.model.dao.AdminDao;
import kr.or.iei.admin.model.vo.Admin;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.common.ListData;
import kr.or.iei.member.model.vo.Member;

public class AdminService {
	private AdminDao dao;

	public AdminService() {
		dao = new AdminDao();
	}

	//관리자 로그인 (모든 정보 조회)
	public Admin adminLogin(String adminId, String adminPw) {
		Connection conn = JDBCTemplate.getConnection();
		Admin loginAdmin = dao.adminLogin(conn, adminId, adminPw);
		JDBCTemplate.close(conn);
		return loginAdmin;
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
		
		System.out.println(deleteId);
		System.out.println(result);
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
	


	
	
	

}
