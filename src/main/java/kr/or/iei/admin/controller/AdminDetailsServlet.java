package kr.or.iei.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminDetailsServlet
 */
@WebServlet("/admin/super/details")
public class AdminDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩-필터
		//2. 값 추출
		String adminId = request.getParameter("id");
		//3. 비즈니스로직 (관리자 1명 조회)
		AdminService service = new AdminService();
		ArrayList<Admin> list = service.searchIdAdmin(adminId); 
		
		//4. 결과처리
		//4.1 페이지 이동
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/adminDetails.jsp");
		//4.2 결과 보여줄 객체 등록
		request.setAttribute("adminDetails", list);
		//4.3.페이지 이동
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
