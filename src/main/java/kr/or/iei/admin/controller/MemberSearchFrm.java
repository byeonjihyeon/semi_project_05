package kr.or.iei.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchFrm
 */
@WebServlet("/admin/member/searchInfo")
public class MemberSearchFrm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchFrm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 (필드, 입력값)
		//필드 (이름 또는 아이디)
		String field = request.getParameter("field"); //memberId 또는 name
		String inputValue = request.getParameter("search");
		System.out.println(field);
		//3. 비즈니스로직 (필드랑 입력값 전달에서 찾기)
		AdminService service = new AdminService();
		ArrayList<Member> list =  service.searchMembers(field,inputValue);
		
		if(list.isEmpty()) {
			return;
		}else {
			request.setAttribute("searchMemberList", list);
			response.getWriter().print(list);
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
