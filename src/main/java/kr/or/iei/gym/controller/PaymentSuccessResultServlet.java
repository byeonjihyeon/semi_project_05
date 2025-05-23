package kr.or.iei.gym.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentSuccessResultServlet
 */
@WebServlet("/payment/result")
public class PaymentSuccessResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentSuccessResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gymName = request.getParameter("gymName");
		String amount = request.getParameter("amount");
		
		
		LocalDate today = LocalDate.now();

        int year = today.getYear();
        int month = today.getMonthValue(); // 1~12
        int day = today.getDayOfMonth();
        String date = year + "년 " + month + "월 " + day + "일";
		
		
		request.setAttribute("gymName", gymName);
		request.setAttribute("amount", amount);
		request.setAttribute("paymentDate", date);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/gym/paymentSuccess.jsp");
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
