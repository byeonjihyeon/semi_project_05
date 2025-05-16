package kr.or.iei.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/admin/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 (수정할 정보)
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String addr = request.getParameter("address"); //주소
		String detailAddr = request.getParameter("detailAddress"); 	//상세주소
		
		//주소 + 상세주소
		String totalAddr =  addr + " " + detailAddr;
		
		String memberGrade = request.getParameter("grade");
		
		//전달할 멤버 객체 생성
		Member mdfMember = new Member();
		mdfMember.setMemberId(memberId);
		mdfMember.setMemberName(name);
		mdfMember.setMemberPhone(phone);
		mdfMember.setMemberEmail(email);
		mdfMember.setMemberAddr(totalAddr);
		mdfMember.setMemberGrade(memberGrade);
		
		//3. 비즈니스 로직 (회원 수정하기)
		AdminService service = new AdminService();
		int result = service.updateMember(mdfMember);
		
		//4. 결과 처리
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		 
		if(result > 0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "회원 수정 완료되었습니다.");
			request.setAttribute("icon", "success");
			
		//정보 변경 성공시, 팝업창 닫고, 부모창에서 정보수정된 채로 이동처리
		request.setAttribute("callback", "self.close(); window.opener.location.href=\"/admin/member/list?page=1\";");
		
		}else {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "수정 중, 오류가 발생하였습니다.");
			request.setAttribute("icon", "error");
			request.setAttribute("callback", "self.close");
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
