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

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.UserGrowth;

/**
 * Servlet implementation class InsertGrowthServlet
 */
@WebServlet("/member/insertGrowth")
public class InsertGrowthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGrowthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		// 값 추출
		String memberTall = request.getParameter("memberTall");
		String memberWeight = request.getParameter("memberWeight");
		String hopeWeight =  request.getParameter("hopeWeight");
		String memberId = request.getParameter("memberId");
		

		// 로직	=> 입력받은 값 운동일지에 등록 ! insert !
		UserGrowth growth = new UserGrowth();
		growth.setMemberTall(memberTall);
		growth.setMemberWeight(memberWeight);
		growth.setMemberHopeWeight(hopeWeight);
		
		MemberService service = new MemberService();
		int result =  service.insertGrowth(growth, memberId);
		 
		ArrayList<UserGrowth> list = service.searchHistory(growth);
		list.add(growth);
		
		System.out.println(list);
		RequestDispatcher view = null;
		System.out.println(result);
		if(result > 0) {
			
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "정상적으로 등록완료 되었습니다.");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/member/recordGrowth");
			
		}else {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "등록중, 오류가 발생하였습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/member/recordGrowth");
		}
		view.forward(request, response);
		// 결과처리
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
