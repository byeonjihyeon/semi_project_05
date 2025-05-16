package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/admin/main")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코팅 - 필터
		//2. 값 추출
		String adminId = request.getParameter("loginId");
		String adminPw = request.getParameter("password");
		//3. 로직 - 관리자 로그인
			//3.1 입력한 아이디와 비밀번호랑 일치하고, 관리자가 DB에 존재하는지
			//3.2 일치하는 관리자의 컬럼 정보를 조회
			//3.3 로그인 이후에도 어느 JSP로 이동하든 회원 정보를 사용할 수 있도록 session 
			AdminService service = new AdminService();
			Admin loginAdmin = service.adminLogin(adminId, adminPw);
		
		//4. 결과 처리
			RequestDispatcher view = null;
			
			//관리자가 아닌 경우
			if(loginAdmin == null) {
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				
				request.setAttribute("title", "알림");
				request.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "/admin/loginFrm");
				
				view.forward(request, response);
			//관리자인 경우	 
			}else { 
				HttpSession session = request.getSession();
				session.setAttribute("loginAdmin", loginAdmin);
				
				//회원 관리 페이지로 이동
				response.sendRedirect("/admin/member/list?page=1");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
