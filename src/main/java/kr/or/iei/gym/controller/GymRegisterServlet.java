package kr.or.iei.gym.controller;

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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.or.iei.common.KhRenamePolicy;
import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymApplyFile;
import kr.or.iei.gym.model.vo.GymFile;

/**
 * Servlet implementation class GymRegisterServlet
 */
@WebServlet("/gym/register")
public class GymRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
		//C드라이브부터 webapp 폴더까지 경로 C:\serverworkspace
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		
		//실제 파일 저장 경로 지정
		String savePath = rootPath + "resources/upload/gym/judge/";
		
		//업로드 파일의 최대 크기 지정
		int maxSize = 1024 * 1024 * 100; //100 Mega Byte
		
		File dir = new File(savePath);
		if(!dir.exists()) { //오늘 날짜 폴더가 존재하지 않으면
			dir.mkdirs(); // 오늘 날짜 폴더 생성
		}
		
		MultipartRequest mRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new KhRenamePolicy());
		
		//MultipartRequest 객체로 값 추출
		String gymId = mRequest.getParameter("gymId");
		String gymPw = mRequest.getParameter("password");
		String gymName = mRequest.getParameter("gymName");
		String addrFirst = mRequest.getParameter("address");
		String addrLast = mRequest.getParameter("detailAddress");
		String gymEmail = mRequest.getParameter("email");
		String gymPhone = mRequest.getParameter("phone");
		
		
		//한번에 여러 파일 첨부할때 코드처리해야 함
		//input type이 file인 태그가 여러개일 때 처리 코드
		Enumeration<String> files =  mRequest.getFileNames();
		
		
		//여러개의 input type이 file인 요소가 존재할 경우, 해당 파일들을 저장할 리스트 생성
		ArrayList<GymApplyFile> fileList = new ArrayList<GymApplyFile>();
		
		
		while(files.hasMoreElements()) {
			String name = files.nextElement(); 
			
			String fileName = mRequest.getOriginalFileName(name); //사용자가 업로드한 파일명
			String filePath = mRequest.getFilesystemName(name);	  //변경된 파일명
			String fileSavePath = "/resources/upload/gym/judge/";
			if(filePath != null) { //input type이 file인 요소들 중, 업로드 된 요소만 처리하기 위함.
				GymApplyFile file = new GymApplyFile();
				file.setFileName(fileName);
				file.setFilePath(filePath);
				file.setFileSavePath(fileSavePath);			
				file.setGymId(gymId);
				
				fileList.add(file);
			}
		}
		
		//3. 로직
		Gym gym = new Gym();
		gym.setGymId(gymId);
		gym.setGymPw(gymPw);
		gym.setGymName(gymName);
		gym.setGymAddr(addrFirst + " " + addrLast);
		gym.setEmail(gymEmail);
		gym.setPhone(gymPhone);
		gym.setPhone(gymPhone);
		
		
		GymService service = new GymService();
		int result = service.registerGym(gym, fileList);
		
		//4. 결과 처리
			//4.1 이동할 페이지 경로 지정
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			//4.2 화면 구현에 필요한 데이터 등록
		if(result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "관리자 승인 대기중입니다. ");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "헬스장 등록중, 오류가 발생했습니다. ");
			request.setAttribute("icon", "error");
		}
		
		request.setAttribute("loc", "/");
		
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
