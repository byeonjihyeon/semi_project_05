package kr.or.iei.member.controller;

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

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class sendEmailCodeServlet
 */
@WebServlet("/member/sendEmail")
public class sendEmailCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendEmailCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		
		// 값 추출
		String userId = request.getParameter("userId");
		String userEmail = request.getParameter("userEmail");
		
		// 로직
		
		MemberService service = new MemberService();
		String toEmail = service.searchToEmail(userId, userEmail);
		
		// 결과처리
		
		if(toEmail != null) {
			// 임시 비밀번호 10자리
			String lower = "abcdefghijklmnopqrstuvwxyz";
			String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String digit = "0123456789";
			String special = "~!@#$%^&*";
			
			String allChar = upper + lower + digit + special;
			
			SecureRandom random = new SecureRandom();	//난수 발생
			StringBuilder randomPw = new StringBuilder();	//임시 비밀번호 10자리 저장 객체
			
			//영대소문자, 숫자, 특수문자 각각 최소 1개씩 
			randomPw.append(lower.charAt(random.nextInt(lower.length())));
			randomPw.append(upper.charAt(random.nextInt(upper.length())));
			randomPw.append(digit.charAt(random.nextInt(digit.length())));
			randomPw.append(special.charAt(random.nextInt(special.length())));
			
			for(int i=0; i<6; i++) {
				randomPw.append(allChar.charAt(random.nextInt(allChar.length())));
			}
			
			char [] charArr = randomPw.toString().toCharArray();
			for(int i=0; i<charArr.length; i++) {
				int randomIdx = random.nextInt(charArr.length);	// 0~9까지
				
				char temp = charArr[i];
				charArr[i] = charArr[randomIdx];
				charArr[randomIdx] = temp;
			}
			
			String newRandomPw = new String(charArr);
			
			Properties prop = new Properties();
			
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
			
			Session session = Session.getDefaultInstance(prop, new Authenticator() {
			
				
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("wnsguddl000@naver.com", "3N9S2YE874TZ");
				}
			});
			
			MimeMessage msg = new MimeMessage(session);
			
			try {
				msg.setSentDate(new Date());
				
				msg.setFrom(new InternetAddress("wnsguddl000@naver.com", "다짐 고객센터"));
				
				InternetAddress to = new InternetAddress(toEmail);
				msg.setRecipient(Message.RecipientType.TO, to);
				
				msg.setSubject("헬창의 당신 비밀번호는 이거입니다.");
				
				msg.setContent("헬창님의 새로운 비밀번호 : <span style='color:blue;'>" + newRandomPw + "</span> 입니다. " , "text/html; charset=utf-8");
				
				Transport.send(msg);
				
				int result = service.updateNewPw(userId, newRandomPw);
				
				RequestDispatcher view = null;
				if(result > 0) {
					view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					request.setAttribute("title", "알림");
					request.setAttribute("msg", "회원님의 임시 비밀번호를 이메일로 전송했습니다.");
					request.setAttribute("icon", "success");
					request.setAttribute("loc", "/member/loginFrm");
				
				}
				
				System.out.println("Email 전송 완료");
				view.forward(request, response);
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}else {
			RequestDispatcher view = null;
			
			view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("title", "알림");
			request.setAttribute("msg", "회원님의 정보가 존재하지 않습니다.");
			request.setAttribute("icon", "warning");
			request.setAttribute("loc", "/member/loginFrm");

			view.forward(request, response);
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
