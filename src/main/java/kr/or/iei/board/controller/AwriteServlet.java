package kr.or.iei.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.common.KhRenamePolicy;

/**
 * Servlet implementation class AwriteServlet
 */
@WebServlet("/board/awrite")
public class AwriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		//오늘 날짜 지정
		String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		// C드라이브부터 webapp 폴더까지 경로 C:\serverworkspace
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		
		// "resources/upload" 안에있는 경로까지 
		String savePath = rootPath + "resources/upload/" + toDay + "/";
		
		//이미지 사이즈
		int maxSize = 1024 * 1024 * 100;
		
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new KhRenamePolicy());
		
		//MultipartRequest 객체로 값 추출. (request.getParameter();도 가능하지만 파일첨부시는 요고사용하자)
		String boardTitle = mRequest.getParameter("boardTitle");
		String boardContent = mRequest.getParameter("boardContent");
		String boardType = mRequest.getParameter("boardType");
		String memberId = mRequest.getParameter("memberId");
		
		//input type의 file인 태그가 여러개일 때, 처리 코드
		//Enumeration : iterator의 구버전 (토크나이저와 유사)
		Enumeration<String> files = mRequest.getFileNames();
		
		ArrayList<BoardFile> fileList = new ArrayList<BoardFile>();
	
		while(files.hasMoreElements()) {
			String name = files.nextElement();
			
			String fileName = mRequest.getOriginalFileName(name); //사용자가 업로드한 파일명
			String filePath = mRequest.getFilesystemName(name);
			
			if(filePath != null) {//input type이 file인 요소들 중, 업로드 된 요소만 처리하기 위함.
				BoardFile file = new BoardFile();
				file.setFileName(fileName);
				file.setFilePath(filePath);
				file.setFileType("G"); //게시판 == B, 공지사항 == G
				
				fileList.add(file);
			}
			}
			//로직
			Board board = new Board();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setBoardType(boardType);
			board.setMemberId(memberId);
			
			BoardService service = new BoardService();
			int result = service.insertBoard(board, fileList);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			
			
				if(result > 0) {
					request.setAttribute("title", "성공");
					request.setAttribute("msg", "게시글이 작성되었습니다");
					request.setAttribute("icon", "success");
					request.setAttribute("loc", "alist?reqPage=1");
				}else {
					request.setAttribute("title", "실패");
					request.setAttribute("msg", "게시글 작성중 오류가 발생하였습니다");
					request.setAttribute("icon", "error");
					request.setAttribute("loc", "/board/awriteFrm");
				}
			
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
