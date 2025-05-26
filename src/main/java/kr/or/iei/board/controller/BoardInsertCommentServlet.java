package kr.or.iei.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardComment
 */
@WebServlet("/board/insertComment")
public class BoardInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩 필터
		//값 추출
		String commentId = request.getParameter("commentId");
		String commentContent = request.getParameter("commentContent");
		String parentCommentId = request.getParameter("parentCommentId");
		String boardId = request.getParameter("boardId");
		String memberId = request.getParameter("memberId");
		String commentNo = request.getParameter("commentNo");
		
		//로직
		BoardComment comment = new BoardComment();
		comment.setCommentId(parentCommentId);
		comment.setCommentContent(commentContent);
		comment.setCommentNo(commentNo);
		
		BoardService service = new BoardService();
		int result = service.insertComment(comment);
		
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글이 작성되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글작성중 오류가 발생하였습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/board/view?boardNo="+commentNo+"updChk=false");
	
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
