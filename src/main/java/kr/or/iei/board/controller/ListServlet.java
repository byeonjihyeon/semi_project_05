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
import kr.or.iei.common.ListData;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/board/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 -필터
		//2. 값 추출 - 요청페이지 번호
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		
		//3. 로직 - 전체 게시글 정보 조회
		BoardService service = new BoardService();
		ListData<Board> listData = service.selectBoardList(reqPage);
		
		//4. 결과처리
			//4.1 이동할 페이지 경로 지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
			//4.2 화면 구현에 필요한 데이터 등록
		request.setAttribute("boardList", listData.getList());
		request.setAttribute("pageNavi", listData.getPageNavi());
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
