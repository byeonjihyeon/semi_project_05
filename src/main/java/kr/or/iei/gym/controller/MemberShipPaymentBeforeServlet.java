package kr.or.iei.gym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberShipPaymentBeforeServlet
 */
@WebServlet("/membership/paymentBefore")
public class MemberShipPaymentBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberShipPaymentBeforeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); // false: 세션이 없으면 null 반환
		
	    // 세션이 없거나 로그인된 회원이 없을 경우 로그인 페이지로 리디렉션
	    if (session == null || session.getAttribute("loginMember") == null) {
	        response.sendRedirect("/member/loginFrm"); // 로그인 페이지 경로
	        return;
	    }
	    
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		
		String gymId = request.getParameter("gymId");
		GymService service = new GymService();
		Gym gym = service.selectOneGym(gymId);
		int price = 0;
		
		String membership = request.getParameter("membership");
		System.out.println(membership);
		
		if(membership.equals("oneMonth")) {
			price = Integer.parseInt(gym.getTicket().getOneMonth());
			membership = "1개월";
		}else if(membership.equals("threeMonth")) {
			price = Integer.parseInt(gym.getTicket().getThreeMonth());
			membership = "3개월";
		}else if(membership.equals("sixMonth")) {
			price = Integer.parseInt(gym.getTicket().getSixMonth());
			membership = "6개월";
		}else if(membership.equals("oneYear")) {
			price = Integer.parseInt(gym.getTicket().getOneYear());
			membership = "12개월";
		}else if(membership.equals("oneDay")) {
			price = Integer.parseInt(gym.getTicket().getOneDay());
			membership = "일일권";
		}
		
		request.setAttribute("gym", gym);
		request.setAttribute("price", price);
		request.setAttribute("membership", membership);
		request.setAttribute("member", loginMember);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/gym/paymentBefore.jsp");
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
