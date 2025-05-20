package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MypageDeleteMemberServlet
 */
@WebServlet("/member/delete")
public class MypageDeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageDeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // 세션객체가 있으면 반환, 없으면 false
		
		if(session != null) {
			Member m = (Member) session.getAttribute("loginMember");
			
			MemberService service = new MemberService();
			int result = service.deleteMember(m);
			
			RequestDispatcher view = null;
			if(result > 0) {
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "성공");
				request.setAttribute("msg", "정상적으로 탈퇴되었습니다.");
				request.setAttribute("icon", "success");
				request.setAttribute("loc", "/");
				session.invalidate();
			}else {
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "회원탈퇴중, 오류가 발생하였습니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "/member/updateMemberFrm");
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
