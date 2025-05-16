package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class deleteMemberServlet
 */

@WebServlet("/admin/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 (회원 아이디)
		String deleteId = request.getParameter("id");
		System.out.println("여기오긴함?");
		System.out.println(deleteId);
		//3. 로직 (회원 아이디 삭제)
		AdminService service = new AdminService();
		int result = service.deleteMember(deleteId);
		
		//4. 결과 처리
			//4.1 이동할 페이지 지정
			RequestDispatcher view;
			
			if(result > 0) { //회원 삭제 성공
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "알림");
				request.setAttribute("msg", "삭제 성공하였습니다.");
				request.setAttribute("icon", "success");
			}else { //회원 삭제 실패
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "알림");
				request.setAttribute("msg", "삭제중, 오류가 발생하였습니다.");
				request.setAttribute("icon", "error");
			}
			
				request.setAttribute("loc", "/admin/member/list?page=1");
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
