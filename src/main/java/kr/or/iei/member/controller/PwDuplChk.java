package kr.or.iei.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class PwDuplChk
 */
@WebServlet("/member/pwDuplChk")
public class PwDuplChk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwDuplChk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩처리 - 필터
		// 값 추출
		String userPw = request.getParameter("userPw");
		String userId = request.getParameter("userId");
		
		MemberService service = new MemberService();
		String pwChk = service.pwDuplChk(userId);
		
		Boolean nowPwChk = BCrypt.checkpw(userPw, pwChk);
		
		response.getWriter().print(nowPwChk);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
