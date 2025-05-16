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

/**
 * Servlet implementation class adminGymsServlet
 */
@WebServlet("/admin/gym/list")
public class GymsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 - 요청 페이지
		int page = Integer.parseInt(request.getParameter("page"));
		//3. 전체 회원 리스트 조회
		//AdminService service = new AdminService();
		//ListData<Gyms> listData = service.selectGymList(page);
	
		//4. 결과 처리
			//4.1 이동할 페이지 경로 지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/gyms.jsp");
			//4.2 화면 구현에 필요한 데이터 등록 (헬스리스트, 페이지네이션)
			//4.3
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
