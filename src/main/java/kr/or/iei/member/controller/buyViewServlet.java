package kr.or.iei.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;
import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UsageDTO;

/**
 * Servlet implementation class buyViewServlet
 */
@WebServlet("/member/payInfo")
public class buyViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		
		// 값 추출
	 	String paymentId = request.getParameter("paymentid");
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		
		MemberService service = new MemberService();
		ArrayList<GymFile> memberFile = service.loadFile(m.getMemberId(), paymentId);
		ArrayList<Usage> memberHistory = service.loadHistory(m.getMemberId(), paymentId);
		ArrayList<Payment> memberPay = service.loadPay(m.getMemberId(), paymentId);
		System.out.println(memberFile);
		System.out.println(memberHistory);
		System.out.println(memberPay);
		
		ArrayList<UsageDTO> paymentList = new ArrayList<>();

		int count = Math.min(Math.min(memberFile.size(), memberHistory.size()), memberPay.size());
		

		for (int i = 0; i < count; i++) {
		    UsageDTO dto = new UsageDTO();
		    
		    dto.setGymFile(memberFile.get(i));
		    dto.setUsage(memberHistory.get(i));
		    dto.setPayment(memberPay.get(i));
		    paymentList.add(dto);
		}
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/viewPayInfo.jsp");
		request.setAttribute("payInfo", paymentList);
		
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
