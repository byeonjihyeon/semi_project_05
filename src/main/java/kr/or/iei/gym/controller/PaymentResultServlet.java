package kr.or.iei.gym.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.iei.gym.model.service.GymService;
import kr.or.iei.gym.model.service.PortOneUtil;
import kr.or.iei.gym.model.vo.Gym;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;

/**
 * Servlet implementation class PaymentResultServlet
 */
@WebServlet("/payment/complete")
public class PaymentResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json;charset=UTF-8");

	    Gson gson = new Gson();
	    Map<String, Object> resultMap = new HashMap<>();

	    try {
	        // JSON 요청 바디 읽기
	        BufferedReader reader = request.getReader();
	        Map<String, String> requestData = gson.fromJson(reader, Map.class);

	        String impUid = requestData.get("imp_uid");
	        String merchantUid = requestData.get("merchant_uid");

	        // 1. 포트원 access token 발급
	        String accessToken = PortOneUtil.getAccessToken();

	        // 2. 결제 정보 조회
	        Map<String, Object> paymentInfo = PortOneUtil.getPaymentInfo(impUid, accessToken);

	        String status = (String) paymentInfo.get("status");
	        String payMethod = (String) paymentInfo.get("pay_method");
	        String cardName = (String) paymentInfo.get("card_name");
	        double amount = (Double) paymentInfo.get("amount");
	        String buyerName = (String) paymentInfo.get("buyer_name");
	        String customDataStr = (String) paymentInfo.get("custom_data");

	        // 3. customData JSON 문자열 파싱
	        Map<String, String> customData = gson.fromJson(customDataStr, Map.class);
	        String gymId = customData.get("gymId");
	        System.out.println("customData에서 뽑은 gymId: "+ gymId);
	        String membership = customData.get("membership");
	        System.out.println("customData에서 뽑은 membership: "+ membership);
	        if(membership.equals("1개월")) {
	        	membership = "oneMonth";
	        }else if(membership.equals("3개월")) {
	        	membership = "threeMonth";
	        }else if(membership.equals("6개월")) {
	        	membership = "sixMonth";
	        }else if(membership.equals("12개월")) {
	        	membership = "oneYear";
	        }else if(membership.equals("일일권")) {
	        	membership = "oneDay";
	        }
	        String memberId = customData.get("memberId");
	        System.out.println("customData에서 뽑은 memberId: "+ memberId);
	        // 4. DB 저장
	        GymService service = new GymService();
	        String ticketId = service.selectTicketId(gymId, membership);
	        System.out.println("servlet ticketId: "+ticketId);

	        Payment payment = new Payment();
	        payment.setMemberId(memberId);
	        payment.setTicketPrice(String.valueOf(amount));
	        payment.setPayMethod(payMethod);
	        payment.setCardName(cardName);
	        payment.setMerchantId(merchantUid);

	        Usage usage = new Usage();
	        usage.setMemberIdRef(memberId);
	        usage.setTicketIdRef(ticketId);
	        usage.setGymIdRef(gymId);

	        int result = service.insertPaymentInfo(payment, usage);
	        System.out.println("first payment servlet result: " + result);
	        if (result > 0) {
	        	Gym gym = service.selectOneGym(gymId);
	        	System.out.println("servlet db 처리 후 성공 result: " + result);
	            resultMap.put("success", true);
	            resultMap.put("orderId", merchantUid);
	            resultMap.put("gymName", gym.getGymName());
	            resultMap.put("amount", amount);
	        } else {
	        	System.out.println("servlet db 처리 후 실패 result: " + result);
	            resultMap.put("success", false);
	            resultMap.put("message", "DB 저장 실패");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        resultMap.put("success", false);
	        resultMap.put("message", "서버 오류 발생");
	    }

	    // 응답으로 JSON 반환
	    response.getWriter().write(gson.toJson(resultMap));
	}

}
