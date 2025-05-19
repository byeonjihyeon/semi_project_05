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
 * Servlet implementation class AdminPrevilegesServlet
 */
@WebServlet("/admin/super/admins") //관리자 권한 설정 페이지로 이동
public class AdminsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 (필터)
		//2. 값 추출 - x
	
		//3. 로직 (각 관리자마다의 권한 리스트)
		AdminService service = new AdminService();
		ArrayList<Admin> list = service.selectAdmins();
		System.out.println(list);
		
		//4. 결과처리
			//4.1 페이지 이동
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/admins.jsp");
		request.setAttribute("adminList", list);
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
