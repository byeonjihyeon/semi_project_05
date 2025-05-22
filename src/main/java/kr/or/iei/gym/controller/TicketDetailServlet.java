package kr.or.iei.gym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.GymTicket;

/**
 * Servlet implementation class TicketDetailServlet
 */
@WebServlet("/ticket/detail")
public class TicketDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gymId = request.getParameter("gymId");
		GymService service = new GymService();
		Gym gym = null;
		GymTicket ticket = service.selectTicket(gymId);
		request.setAttribute("ticket", ticket);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/gym/gymTicket.jsp");
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
