package kr.or.iei.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminChangePwSevlet
 */
@WebServlet("/admin/chgPw")
public class AdminChangePwSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangePwSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출
		//현재 비밀번호
		String adminPw = request.getParameter("adminPw"); 
		String newAdminPw = request.getParameter("newAdminPw");
		
		//3. 로직
		//세션 객체가 존재하면, 존재하는 객체 반환. 없으면 null 반환
		HttpSession session = request.getSession(false); 
		if(session != null) {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			
			//로그인 관리자 정보
			ArrayList<Admin> loginAdmin = (ArrayList<Admin>)session.getAttribute("loginAdmin");
			
			boolean pwChk = BCrypt.checkpw(adminPw, loginAdmin.get(0).getMemberPw());
			//암호화 비번과 평문 비번이 일치하지 않다면
			if(!pwChk) {
				request.setAttribute("icon", "warning");
				request.setAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
				view.forward(request, response);
				return;
			}
			
			AdminService service = new AdminService();
			int result = service.updateAdminPw(loginAdmin.get(0).getMemberId() , newAdminPw);
			
			//4. 결과 처리
			if(result > 0) {
				request.setAttribute("icon", "success");
				request.setAttribute("title", "비밀번호 변경 완료");
				request.setAttribute("msg", "변경된 비밀번호로 다시 로그인하세요.");
				request.setAttribute("loc", "/admin/loginFrm");
				session.invalidate();
			}else {
				request.setAttribute("msg", "비밀번호 변경처리중, 오류가 발생하였습니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "/admin/chgPwFrm");
			}
			
			view.forward(request, response);
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
