package kr.or.iei.gym.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.vo.Gym;

/**
 * Servlet implementation class GymFindFrmServlet
 */
@WebServlet("/gym/list")
public class GymListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		//실제 파일 저장 경로 지정
		String savePath = "/resources/upload/gym/image/";
		
		
		//등록된 헬스장 리스트 가져오기
		GymService service = new GymService();
		List<Gym> gymList = service.selectAllGym();
		if(gymList.size()==0) gymList = null;
		request.setAttribute("gymList", gymList);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/gym/gymList.jsp");
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
