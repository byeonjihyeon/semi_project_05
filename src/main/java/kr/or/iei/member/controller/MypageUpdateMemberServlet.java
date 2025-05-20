package kr.or.iei.member.controller;

import java.io.IOException;

import javax.mail.Session;
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
 * Servlet implementation class MypageUpdateMemberServlet
 */
@WebServlet("/member/updateMember")
public class MypageUpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageUpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 - 필터
		// 값 추출
		String userId = request.getParameter("memberId");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String addrFirst = request.getParameter("address");
		String addrLast = request.getParameter("detailAddress");
		
		Member m = new Member();
		m.setMemberAddr(addrFirst + " " + addrLast);
		m.setMemberName(userName);
		m.setMemberPhone(userPhone);
		m.setMemberEmail(userEmail);
		m.setMemberId(userId);
		

		
		MemberService service = new MemberService();
		int result = service.updateMember(m);
		
		HttpSession session = request.getSession(false);
		Member loginM = (Member) session.getAttribute("loginMember");
		loginM.setMemberAddr(m.getMemberAddr());
		

		
		RequestDispatcher view = null; 
		if(result > 0) {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "회원정보수정 완료되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/myPageFrm");
			
			session.setAttribute("loginMember", loginM);
		}else {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "회원정보수정중, 오류가 발생하였습니다..");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/updateMemberFrm");
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
