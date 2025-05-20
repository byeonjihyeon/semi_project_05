package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class updatePwServlet
 */
@WebServlet("/member/updatePw")
public class updatePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 - 필터
		// 값 추출 
		 String userPw = request.getParameter("userPw");
		 String updatePw = request.getParameter("chgPw");
		 String userId = request.getParameter("memberId");
		 
		 HttpSession session = request.getSession(false); //세션객체가 존재하면 반환, 없으면 null 반환
		 
		 if(session != null) {
			 RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			 Member loginMember = (Member) session.getAttribute("loginMember"); 
			 
			 Boolean pwChk = BCrypt.checkpw(userPw, loginMember.getMemberPw());
			 
				 MemberService service = new MemberService();
				 int result = service.updatePw(userId, updatePw);
			 
				 if(result > 0){
					 request.setAttribute("title", "성공");
					 request.setAttribute("msg", "비밀번호가 변경되었습니다.");
					 request.setAttribute("icon", "success");
					 request.setAttribute("loc", "/member/myPageFrm");
				 }else {
					 request.setAttribute("title", "실패");
					 request.setAttribute("msg", "비밀번호가 변경중, 오류가 발생하였습니다..");
					 request.setAttribute("icon", "error");
					 request.setAttribute("loc", "/member/updatePwFrm");
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
