package kr.or.iei.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class DeleteListServlet
 */
//글 하나 삭제 (재사용가능할듯)
@WebServlet("/board/delete")
public class DeleteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩 

		// 값 추출
		String deleteBoardId = request.getParameter("deleteBoardId");
		System.out.println(deleteBoardId);
		
		BoardService service = new BoardService();
		ArrayList<BoardFile> delFileList = service.deleteBoard(deleteBoardId);
		
	    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
	    
	    if(delFileList != null) {
	    	request.setAttribute("title", "성공");
	    	request.setAttribute("msg", "게시글이 삭제되었습니다.");
	    	request.setAttribute("icon", "success");
	    	request.setAttribute("loc", "list?reqPage=1");
	    	
	    	String rootPath = request.getSession().getServletContext().getRealPath("/");
	    	for(int i=0; i<delFileList.size(); i++) {
	    		BoardFile delFile = delFileList.get(i);
	    		
	    		String writeDate = delFile.getFilePath().substring(0, 8);
	    		String savePath = rootPath + "resources/upload/" + writeDate + "/"; //파일 저장 경로
				
				File file = new File(savePath + delFile.getFilePath());
				file.delete();
			}
    	}else {
	    		request.setAttribute("title", "실패");
		    	request.setAttribute("msg", "오류 발생.");
		    	request.setAttribute("icon", "error");
		    	request.setAttribute("loc", "list?reqPage=1");
	    }

	    view.forward(request, response);
		// 로직	=> 작성자랑 로그인한 작성자랑 같냐 ?
	    		// 같다면 삭제해라
	    		// 아니면 삭제 안되고 리스트 화면 돌려라
	    
		// 결과처리
		
	}	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		doGet(request, response);
	}
	
	

}
