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
 * Servlet implementation class AboardViewServlet
 */
//공지사항 - 하나의 글 조회
@WebServlet("/board/aview")
public class AboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		//값 추출
		String boardNo = request.getParameter("boardNo");
		
		BoardService service = new BoardService();
		Board oneB = service.selectOneBoard(boardNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/AboardView.jsp");
		
		request.setAttribute("boardInfo", oneB);
			
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
