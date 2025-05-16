package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/member/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		// 값 추출
		String joinUserId = request.getParameter("userId");
		String joinUserPw = request.getParameter("userPw");
		String joinUserName = request.getParameter("userName");
		String joinUserEmail = request.getParameter("userEmail");
		String joinUserPhone = request.getParameter("userPhone");
		
		Member m = new Member();
		m.setMemberId(joinUserId);
		m.setMemberPw(joinUserPw);
		m.setMemberName(joinUserName);
		m.setMemberEmail(joinUserEmail);
		m.setMemberPhone(joinUserPhone);
		
		MemberService service = new MemberService();
		int result = service.joinMember(m);
		
		RequestDispatcher view;
		if(result > 0) {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "회원가입이 정상적으로 완료되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/loginFrm");
			
		}else {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "회원가입 중, 오류가 발생하였습니다..");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/joinFrm");
			
		}
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
