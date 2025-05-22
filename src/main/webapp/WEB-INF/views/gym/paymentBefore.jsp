<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <meta charset="UTF-8">
    <title>회원권 구매 확인</title>
    <link rel="stylesheet" href="/resources/css/gym/payment.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<main>
    <div class="wrap">
        <div class="content">
            <div class="page-title">회원권 구매 확인</div>
            <section class="section bg-section">
                <div class="confirm-container">
                    <form>
                        <div class="confirm-info">
                            <div class="confirm-item">
                                <label>헬스장 이름</label>
                                <div class="confirm-value">${gym.gymName}</div>
                            </div>
                            <div class="confirm-item">
                                <label>선택한 회원권</label>
                                <div class="confirm-value">${membership}
                                </div>
                            </div>
                            <div class="confirm-item">
                                <label>가격</label>
                                <div class="confirm-value">"${price}"</div>
                            </div>
                        </div>

                        <div class="confirm-button-area">
                            <button type="button" class="btn-primary lg" onclick="requestPay()">결제하기</button>
                            <a href="/ticket/detail?gymId=${gymId}" class="btn-secondary lg">이전으로</a>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
function generateUUID() {
	  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
	    const r = Math.random() * 16 | 0;
	    const v = c === 'x' ? r : (r & 0x3 | 0x8);
	    return v.toString(16);
	  });
	}
    const IMP = window.IMP;
    IMP.init("imp46858506"); // 고객사 식별코드
    const mid = 'payment-'+crypto.randomUUID();
    function requestPay() {
    	console.log(crypto.randomUUID());
        IMP.request_pay({
            pg: "html5_inicis",
            pay_method: "card",
            channelKey: 'channel-key-d74978a8-067e-4506-b06b-0856171f9db3',
            merchant_uid: mid,
            pay_method: "card",
            name: "멋짐 회원권 - ${gym.gymName} (${membership})",
            amount: ${price},
            buyer_email: "${member.memberEmail}",
            buyer_name: "${member.memberName}",
            buyer_tel: "${member.memberPhone}",
            buyer_addr: "${member.memberAddr}",
            custom_data: {
                gymId: "${gymId}",
                membership: "${membership}",
                memberId: "${member.memberId}"
            }
        }, async function (response) {
            if (response.error_code) {
                alert("결제 실패: " + response.error_msg);
            } else {
                const res = await fetch("/payment/complete", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                        imp_uid: response.imp_uid,
                        merchant_uid: response.merchant_uid
                    })
                });

                const result = await res.json();
                
                if (result.success) {//인증 거치고, db에 잘 넣으면, 
                    location.href = "/payment/success?orderId=" + rsp.merchant_uid;
                } else {
                    alert("서버에서 결제 승인 실패");
                }
            }
        });
    }
</script>

</body>
</html>
