package kr.or.iei.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownServlet
 */
//게시글 첨부파일 다운로드
@WebServlet("/board/fileDown")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		String filePath = request.getParameter("filePath");
		
		String writeDate = filePath.substring(0,8);
		//webapp 폴더 경로
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//파일 저장 경로
		String savePath = rootPath + "resources/upload/" + writeDate + "/"; 
		
		File file = new File(savePath+filePath);
		
		if(file.exists()) {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
		try {	
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			//브라우저에게 파일 다운로드를 지시하며, 다운로드될 파일명 지정.
			response.setContentType("application/octet-stream");
			String resFileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			response.setHeader("Content-Disposition", String.format("attchment; filename=\"%s\"", resFileName));
			
			ServletOutputStream sos = response.getOutputStream();
			bos = new BufferedOutputStream(sos);
			
			while(true) {
				int read = bis.read();
				if(read == -1) {
					break;
				}else {
					bos.write(read);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			bos.close();
			bis.close();
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
