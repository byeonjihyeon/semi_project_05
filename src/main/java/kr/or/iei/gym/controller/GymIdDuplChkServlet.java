package kr.or.iei.gym.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.gym.model.service.GymService;

/**
 * Servlet implementation class GymIdDuplChkServlet
 */
@WebServlet("/gym/idDuplChk")
public class GymIdDuplChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GymIdDuplChkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1. 인코딩 - 필터
		
				//2. 값 추출 - 중복체크에 필요한 사용자 입력 아이디
				String gymId = request.getParameter("gymId");
				
				//3. 로직 - 아이디 중복체크
				GymService gymService = new GymService();
				
				/*
				 기존 04번 프로젝트까지는 아이디 중복체크 결과를 Member로 리턴받음. 메소드를 재사용하여서
				 지금은 단순히 아이디 중복체크만 진행. 회원의 모든 컬럼값을 조회할 필요 없음.
				 count 그룹 함수를 사용하면 조건식에 일치하는 행의 갯수를 숫자로 리턴
				 * */
				int cnt = gymService.idDuplChk(gymId);
				
				//4. 결과 처리
					//4.1 이동할 JSP 경로 지정
				//RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/join.jsp");
					//4.2 화면 구현에 필요한 데이터 등록
				//request.setAttribute("cnt", cnt);
					//4.3 페이지 이동
				//view.forward(request, response);
				
				//4. 결과 처리(비동기 통신 ajax)
				response.getWriter().print(cnt); //응답 스트림을 통해 데이터를 응답
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
