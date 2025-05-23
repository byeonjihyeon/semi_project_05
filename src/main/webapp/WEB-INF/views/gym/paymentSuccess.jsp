<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>결제 완료</title>
    <link rel="stylesheet" href="/resources/css/gym/paymentSuccess.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<main>
    <div class="wrap">
        <div class="payment-success-box">
            <div class="icon">
                 <img src="/resources/upload/gym/payment/payment_success_icon2.png" alt="성공 아이콘" width="64" height="64">
            </div>
            <h2>결제가 완료되었습니다!</h2>
            <p class="message">헬스장 이용권 결제가 정상적으로 처리되었습니다.</p>

            <div class="payment-summary">
                <p><strong>헬스장 이름:</strong> <c:out value="${gymName}" /></p>
                <p><strong>결제 금액:</strong> <c:out value="${amount}" />원</p>
                <p><strong>결제 일시:</strong> <c:out value="${paymentDate}" /></p>
            </div>

            <div class="btn-area">
                <a href="/gym/list" class="btn-primary">헬스장 목록으로</a>
                <a href="/member/mypage" class="btn-secondary outline">마이페이지로</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
