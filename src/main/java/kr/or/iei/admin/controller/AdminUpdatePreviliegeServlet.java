package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminUpdatePreviliegeServlet
 */
@WebServlet("/admin/super/updatePrivilege")
public class AdminUpdatePreviliegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdatePreviliegeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 (아이디, url 권한에 대한 삽입,삭제,수정 권한)
		String memberId = request.getParameter("id");
		String url = request.getParameter("url");
		String sel = request.getParameter("sel");
	
		if(sel == null) {
			sel = "N";
		}
		String upd = request.getParameter("upd");
		if(upd == null) {
			upd = "N";
		}
		String del = request.getParameter("del");
		if(del == null) {
			del = "N";
		}
		
		Admin admin = new Admin();
		admin.setMemberId(memberId);
		admin.setUrl(url);
		admin.setSelYN(sel);
		admin.setUpdYN(upd);
		admin.setDelYN(del);
		
		//3. 비즈니스 로직 (관리자 권한 수정)
		AdminService service = new AdminService();
		int result = service.updateAdminPreviliege(admin);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if(result > 0) {
		 request.setAttribute("icon", "success");
		 request.setAttribute("msg", "변경 완료하였습니다.");
		}else {
		 request.setAttribute("icon", "error");
		 request.setAttribute("msg", "변경중 오류가 발생하였습니다.");
		}
		
		request.setAttribute("loc", "/admin/super/details?id="+memberId);
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
