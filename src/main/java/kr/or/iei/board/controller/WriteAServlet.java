package kr.or.iei.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.iei.board.model.service.BoardService;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardAdmin;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.common.KhRenamePolicy;

/**
 * Servlet implementation class WriteAServlet
 */
@WebServlet("/")
public class WriteAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// AListServlet
	// 값 추출 
		
		/*
	//오늘 날짜
	String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date());
	
	String rootPath = request.getSession().getServletContext().getRealPath("/");
	
	String savePath = rootPath + "resources/upload/" + toDay + "/";
	
	int maxSize = 1024 * 1024 * 100;
	
	File dir = new File(savePath);
	if(!dir.exists()) {
		dir.mkdir();
	}
	
	MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new KhRenamePolicy());
	
	
	String boardTitle = mRequest.getParameter("boadrTitle");
	String boardContent = mRequest.getParameter("boardContent");
	String boardType = mRequest.getParameter("boardType");
	ArrayList<BoardAdmin> boardAdmin = new ArrayList<BoardAdmin>();
	
	
	Enumeration<String> files = mRequest.getFileNames();
	
	ArrayList<BoardFile> fileList = new ArrayList<BoardFile>();
	
	while(files.hasMoreElements()) {
	String name = files.nextElement();
	
	String fileName = mRequest.getOriginalFileName(name);
	String filePath = mRequest.getFilesystemName(name);
		
	if(filePath != null) {
		BoardFile file = new BoardFile();
		file.setFileName(fileName);
		file.setFilePath(filePath);
		file.setFileType("G");
		
		fileList.add(file);
	}
	}
	
	Board board = new Board();
	board.setBoardTitle(boardTitle);
	board.setBoardContent(boardContent);
	board.setBoardType(boardType);
	board.setBoardAdmin(boardAdmin);
	
	BoardService service = new BoardService();
	int result = service.insertAboard(board, fileList);
	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
