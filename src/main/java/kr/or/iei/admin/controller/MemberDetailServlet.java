package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailServlet
 */
//회원 상세정보창으로 이동
@WebServlet("/admin/member/details")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코팅 - 필터
		//2. 값 추출 (회원 아이디)
		String searchId = request.getParameter("id");
		//3. 비즈니스로직 (회원 1명 조회)
		AdminService service = new AdminService();
		Member srchMember = service.selectMember(searchId);
		
		
		//4. 결과처리
			//4.1 이동할 페이지 지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/memberDetail.jsp");
			//4.2 화면 구현에 필요한 데이터 등록
			request.setAttribute("memberDetails", srchMember);
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
