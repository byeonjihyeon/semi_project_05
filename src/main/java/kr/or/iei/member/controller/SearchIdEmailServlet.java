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
 * Servlet implementation class SearchIdEmailServlet
 */
@WebServlet("/member/searchId")
public class SearchIdEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		// 값 추출
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("memberEmail");
		// 로직 	=> 이름과 비밀번호를 가진 아이디가 있는가 ?
		
		MemberService service = new MemberService();
		String memberId = service.searchId(userName, userEmail);
		
		
		RequestDispatcher view = null;
		
		if(memberId != null) {
			
			int idLength = memberId.length(); // 아이디 길이
			String first = memberId.substring(0,2);	// 조회한 예시 ID로는 ad만 추출
			String last = memberId.substring(idLength-2);
			String marker = "*".repeat(idLength-4);
			
			memberId = first + marker + last;
			
			view = request.getRequestDispatcher("/WEB-INF/views/member/selectId.jsp");
			request.setAttribute("selectId", memberId);
			
		}else {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "회원님의 정보가 존재하지 않습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/");
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
