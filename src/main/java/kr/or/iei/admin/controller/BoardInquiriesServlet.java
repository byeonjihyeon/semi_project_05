package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;

/**
 * Servlet implementation class InquiriesServlet
 */
@WebServlet("/admin/board/inquiries")
public class BoardInquiriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInquiriesServlet() {
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
		//3. 로직 - 일대일 문의 내역 조회
		//AdminService service = new AdminService();
		
		//4. 결과 처리
			//4.1 이동할 페이지 경로 지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/inquiries.jsp");
			//4.2. 화면 구현에 필요한 데이터 등록 (문의내역 리스트, 페이지네이션)
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
