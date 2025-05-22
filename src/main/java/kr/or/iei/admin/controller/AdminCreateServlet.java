package kr.or.iei.admin.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.admin.model.service.AdminService;
import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet implementation class AdminCreateServlet
 */
@WebServlet("/admin/super/createAdmin")
public class AdminCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 - 필터
		//2. 값 추출 - 아이디, 이름, 이메일, 담당업무
		String id = request.getParameter("adminId");
		String name = request.getParameter("adminName");
		String email = request.getParameter("email");
		
		//3. 로직
		Admin admin = new Admin();
		admin.setMemberId(id);
		admin.setMemberName(name);
		admin.setMemberEmail(email);
		
		//3.1 랜덤 비밀번호 만들기
		AdminService service1 = new AdminService();
		String randomPw = service1.makeRandomPw();
		
		admin.setMemberPw(randomPw);
		
		//3.2 이메일 전송
		//3.2.1 환경 설정 정보
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com"); //사용 smtp 서버명
		prop.put("mail.smtp.port", 587); //사용 smtp 포트
		prop.put("mail.smtp.auth", "true");	//인증 수행
		prop.put("mail,smtp.ssl.enable", "true"); //보안 설정 적용
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
		
		//3.2.2 세션 설정 및 인증 정보 설정
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("byeonchoco@naver.com", "C1QRC34848PV");
			}
		});
		
		//3.2 관리자 등록 (권한포함)
		AdminService service2 = new AdminService();
		int result = service2.createAdmin(admin);
		
		//3. 이메일로 새로 등록된 관리자 아이디와 임시 비밀번호 전송
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress("byeonchoco@naver.com", "mutgym"));
			
			InternetAddress to = new InternetAddress(email); //수신자 이메일 주소
			msg.setRecipient(Message.RecipientType.TO, to);
			
			msg.setSubject("[MUTGYM] 신규 관리자 계정 정보 안내"); //제목
			
			String htmlContent = "<!DOCTYPE html>" +
					"<html lang='ko'>" +
					"<head><meta charset='UTF-8'></head>" +
					"<body style='font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;'>" +
					"  <div style='background-color: #fff; border: 1px solid #dddddd; border-radius: 10px; padding: 30px; max-width: 600px; margin: auto; box-shadow: 0 2px 6px rgba(0,0,0,0.1);'>" +
					"    <div style='background-color: #CE2029; padding: 30px 0; text-align: center; border-radius: 10px 10px 0 0;'>" +
					"      <span style='color: white; font-size: 30px; font-weight: bold;'>MUTGYM</span>" +
					"    </div>" +
					"    <div style='padding: 20px; color: #333;'>" +
					"      <p>안녕하세요. 멋짐입니다. <br><strong>관리자 계정 안내</strong> 메일입니다.</p>" +
					"      <p>귀하께서는 <span style='font-weight: bold; color: #CE2029;'>신규 관리자</span>로 등록되었으며, 아래 계정 정보를 사용하여 시스템에 로그인하실 수 있습니다.</p>" +
					"      <div style='background-color: #f9f9f9; border: 1px solid #e0e0e0; border-radius: 6px; padding: 15px; margin: 20px 0;'>" +
					"        <p style='margin: 8px 0; font-size: 16px;'><strong>아이디:</strong> " + id + "</p>" +
					"        <p style='margin: 8px 0; font-size: 16px;'><strong>임시 비밀번호:</strong> " + randomPw + "</p>" +
					"      </div>" +
					"      <p>로그인 후 <span style='font-weight: bold; color: #CE2029;'>반드시 비밀번호를 변경</span>해 주세요.<br>" +
					"      계정 관련 문의사항이 있을 경우 <a href='mailto:admin@mutgym.com' style='color: #888; text-decoration: underline;'>admin@mutgym.com</a>으로 문의 바랍니다.</p>" +
					"      <p><strong>운영관리팀</strong></p>" +
					"    </div>" +
					"    <div style='margin-top: 40px; font-size: 12px; color: #888; text-align: center;'>" +
					"      본 메일은 발신전용입니다. <br>" +
					"      Copyright ⓒ MUTGYM. All rights reserved." +
					"    </div>" +
					"  </div>" +
					"</body>" +
					"</html>";
			
			msg.setContent(htmlContent, "text/html; charset=utf-8");
			
			Transport.send(msg);
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//4. 결과 처리
		//4.1. 이동할 페이지 지정
			RequestDispatcher view = null;
			
		if(result > 0) {
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("icon", "success");
			request.setAttribute("msg", "등록 완료하였습니다. 해당 관리자의 권한을 설정하여주세요.");
			request.setAttribute("loc", "/admin/super/details?id="+ id);
			
		}else {
			
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
