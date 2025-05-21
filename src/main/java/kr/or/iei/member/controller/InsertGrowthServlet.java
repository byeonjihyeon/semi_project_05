package kr.or.iei.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
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
		int memberTall = Integer.parseInt(request.getParameter("memberTall"));
		int memberWeight = Integer.parseInt(request.getParameter("memberWeight"));
		int hopeWeight =  Integer.parseInt(request.getParameter("hopeWeight"));
		String memberId = request.getParameter("memberId");
		
		
		
						
		
		// 로직	=> 입력받은 값 운동일지에 등록 ! insert !
		UserGrowth growth = new UserGrowth();
		growth.setMemberTall(memberTall);
		growth.setMemberWeight(memberWeight);
		growth.setMemberHopeWeight(hopeWeight);
		
		MemberService service = new MemberService();
		int result =  service.insertGrowth(growth, memberId);
		 
		
		response.getWriter().print(result);
		
		System.out.println(result);
		
		
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
