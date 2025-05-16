package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchIdServlet
 */
@WebServlet("/member/searchInfo")
public class SearchIdFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 필터 
		// 값 추출 
		String gb = request.getParameter("gb");
		// 로직 X
		// 결과처리
			// 페이지 이동 경로 지정
			RequestDispatcher view = null;
			
			if(gb.equals("id")) {				
				view = request.getRequestDispatcher("/WEB-INF/views/member/searchId.jsp"); //단순 로그인 화면으로 이동하기 위해 만들어진 서블릿
			}else if(gb.equals("pw")){
				view = request.getRequestDispatcher("/WEB-INF/views/member/searchPw.jsp");
			}else {
				request.setAttribute("title", "알림");
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("icon", "warning");
				request.setAttribute("loc", "/");
			}
			
			// 화면 구현에 필요한 데이터 등록 X
			// 페이지 이동
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
