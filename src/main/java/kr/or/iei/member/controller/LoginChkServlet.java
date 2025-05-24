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

import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UserGrowth;

/**
 * Servlet implementation class LoginChk
 */
@WebServlet("/loginChk")
public class LoginChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginChkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩

		// 값 추출 
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		int type = request.getParameter("userType").equals("general")?2:3;
		
		HttpSession session = request.getSession();
		RequestDispatcher view;
		
		if(type == 2) {	//로그인시, 라디오버튼 회원을 선택시
			Member m = new Member();
			m.setMemberId(userId);
			m.setMemberPw(password);
			
			// 로직
			
			MemberService service = new MemberService();
			Member loginM = service.loginChk(m);
			
			//회원정보(암호화된 비밀번호) =?> askjhwqklbhioqwbnfkowqhbgfkjwqgkjfegbqik;
			//password = 1231234;
			
			
			Boolean passwordChk = BCrypt.checkpw(password, loginM.getMemberPw());
			
			//결과처리
				// 페이지 이동할 경로 지정.


				//로그인 성공시 바로 메인으로


				if(loginM != null && passwordChk) {
					
					view = request.getRequestDispatcher("index.jsp");
					session.setAttribute("loginMember", loginM);
				}else{
					// 로그인 실패시 msg로 문구 보여주고 로그인 창으로
					view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					request.setAttribute("title", "실패");
					request.setAttribute("msg", "로그인 중, 오류가 발생하였습니다.");
					request.setAttribute("icon", "error");
					request.setAttribute("loc", "/");
				}
				
				
				// 화면 구현에 필요한 데이터 등록
				
				// 페이지 이동
				view.forward(request, response);
		}else {//로그인시 라디오버튼 헬스장 관리자 선택 시,
			GymService service = new GymService();
			Gym loginGym = service.loginChkGym(userId, password);
			
			if(loginGym != null) {
				view = request.getRequestDispatcher("index.jsp");
				session.setAttribute("loginGym", loginGym);	
			}else {
				view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "로그인 중, 오류가 발생하였습니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("loc", "index.jsp");
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
