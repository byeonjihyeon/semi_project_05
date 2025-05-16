package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.common.ListData;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class adminMembersServlet
 */
//회원 관리 페이지로 이동
@WebServlet("/admin/member/list")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 - 요청 페이지
		int page = Integer.parseInt(request.getParameter("page"));
		//3. 로직 - 전체 회원 리스트 조회
		AdminService service = new AdminService();
		ListData<Member> listData = service.selectMemberList(page);
		
		//4. 결과 처리
			//4.1 이동할 페이지 경로지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp");
			//4.2 화면 구현에 필요한 데이터 등록
		request.setAttribute("memberList", listData.getList());
		request.setAttribute("pageNavi", listData.getPageNavi());
		//현재 페이지 등록
		request.setAttribute("currentPage" , listData.getCurrentPage());
		//한화면에 보여줄 갯수 등록
		request.setAttribute("pageSize", listData.getPageSize());
			//4.3 페이지 이동
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
