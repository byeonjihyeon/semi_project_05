package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.common.ListData;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class GymApplyDetailsServlet
 */
//헬스장 신청 내역 페이지로 이동
@WebServlet("/admin/gym/applications")
public class GymApplicationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymApplicationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출
		int page = Integer.parseInt(request.getParameter("page"));
		//3. 로직 - 헬스장 등록신청한 리스트 조회
		//AdminService service = new AdminService();
		//ListData<Gym> listData = service.selectAppGymList(page);
		//4. 결과 처리
			//4.1 이동할 페이지 경로지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/gymAppDetails.jsp");
			//4.2 화면 구현에 필요한 데이터 등록 (리스트, 페이지네이션)
			//4.3 페이지 이동
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
