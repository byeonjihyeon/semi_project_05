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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/board/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 필터
		//값추출
		String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date()); // 오늘날짜 
		
		String rootPath = request.getSession().getServletContext().getRealPath("/"); //세션
		
		String savePath = rootPath + "resources/upload/" + toDay + "/"; //오늘 업로드한 시간
		
		int maxSize = 1024 * 1024 * 100; //사진 사이즈
		
		File dir = new File(savePath); //이 경로를 기반으로 File 객체를 생성.
									//이 시점에서는 실제 디렉토리가 생성되거나 존재하는지 여부는 관계 없음.
		if(!dir.exists()) {  //해당 경로가 실제 디스크 상에 존재하는지 확인.
			dir.mkdirs();  //디렉토리가 존재하지 않으면, 필요한 모든 상위 디렉토리까지 모두 생성.
		}
		//코드는 파일 업로드를 처리할 때 사용하는 구문
		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new KhRenamePolicy());
		
		//로직
		String boardTitle = mRequest.getParameter("boardTitle");
		
		String boardContent = mRequest.getParameter("boardContent");
		
		String memberId = mRequest.getParameter("memberId");
	
		String boardId = mRequest.getParameter("boardId");
		
		String fileTypeId = mRequest.getParameter("fileTypeId");
		
		
		
		
		//파일을 전송한 input 요소들의 name 속성값들을 Enumeration 형태로 반환합니다.
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
			file.setFileTypeId(fileTypeId);
			addFileList.add(file);
		}
		}
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberId(memberId);
		board.setBoardId(boardId);
		
		String [] delFileNoList = mRequest.getParameterValues("delFileNo");  //삭제 대상 파일 정보
		
		BoardService service = new BoardService();
		ArrayList<BoardFile> delFileList = service.updateBoard(board, addFileList, delFileNoList);
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(delFileList != null) {//삭제 대상 리스트가 리턴되었을 때
			for(int i=0; i<delFileList.size(); i++) {
				BoardFile delFile = delFileList.get(i);
				
				String writeDate = delFile.getFilePath().substring(0, 8); //삭제 파일이 위치한 폴더명
				String delSavePath = rootPath + "resources/upload/" + writeDate + "/" + delFile.getFilePath();
				
				File file = new File(delSavePath);
				if(file.exists()) {
					file.delete();
				}
			}
			
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "게시물 수정 완료!");
			request.setAttribute("icon", "success");
			request.setAttribute("loc", "/board/list?reqPage=1");
			
		}else {

			request.setAttribute("title", "알림");
			request.setAttribute("msg", "게시물 수정 중, 오류가 발생하였습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("loc", "/board/list?reqPage=1");
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
