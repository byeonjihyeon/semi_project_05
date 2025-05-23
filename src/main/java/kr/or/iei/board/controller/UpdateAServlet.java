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
 * Servlet implementation class UpdateAServlet
 */
@WebServlet("/aboard/update")
public class UpdateAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//값 추출
		String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		String rootPath = request.getSession().getServletContext().getRealPath("/"); //새숀
		
		String savePath = rootPath + "resources/upload/board/notice/" + toDay + "/";
		
		int maxSize = 1024 * 1024 * 100;
		
		File dir = new File(savePath);
		
		if(!dir.exists()) {
			dir.mkdir();
			
		}
		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8" ,new KhRenamePolicy());
	
		String boardTitle = mRequest.getParameter("boardTitle");
		String boardContent = mRequest.getParameter("boardContent");
		String memberId = mRequest.getParameter("memberId");
		String boardId = mRequest.getParameter("boardId");
		String boardType = mRequest.getParameter("boardType");
		
		Enumeration<String> files = mRequest.getFileNames();
		
		ArrayList<BoardFile> addFileList = new ArrayList<BoardFile>();
				
		while(files.hasMoreElements()) {
			String name = files.nextElement();
			
			String fileName = mRequest.getOriginalFileName(name);
			String filePath = mRequest.getFilesystemName(name);
			
			if(filePath != null) {
				BoardFile file = new BoardFile();
				file.setFileName(fileName);
				file.setFilePath(filePath);
				file.setMemberId(memberId);
				file.setFileType(name);
				file.setFileTypeId(boardId);
				addFileList.add(file);
			}
		}
		
		Board board = new Board();
		board.setBoardType(boardType);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberId(memberId);
		board.setBoardId(boardId);
	
		String [] delFileNoList = mRequest.getParameterValues("delFileNo");
		
		BoardService service = new BoardService();
		ArrayList<BoardFile> delFileList = service.updateBoard(board, addFileList, delFileNoList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(delFileList != null) {
			for(int i=0; i<delFileList.size(); i++) {
				BoardFile delFile = delFileList.get(i);
				
				String writeDate = delFile.getFilePath().substring(0, 8);
				String delSavePath = rootPath + "resources/upload/board/notice/" + writeDate + "/" + delFile.getFilePath();
				
				File file = new File(delSavePath);
				if(file.exists()) {
					file.delete();
				}
			}
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "게시물 수정 완료!");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/board/alist?reqPage=1");
			
		}else {

			request.setAttribute("title", "알림");
			request.setAttribute("msg", "게시물 수정 중, 오류가 발생하였습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/board/alist?reqPage=1");
			
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
