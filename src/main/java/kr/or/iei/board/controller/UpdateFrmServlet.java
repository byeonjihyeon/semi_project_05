package kr.or.iei.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.Board;

/**
 * Servlet implementation class UpdateFrmServlet
 */
@WebServlet("/board/updateFrm")
public class UpdateFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 
		String boardNo = request.getParameter("boardNo");
		//3. 로직
		BoardService service = new BoardService();
		Board board = service.selectOneBoard(boardNo);
		
		//4. 결과 처리
		//이동할 페이지 지정 - 수정하는 폼
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/upWriteFrm.jsp");
		request.setAttribute("oneBoard", board);
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
