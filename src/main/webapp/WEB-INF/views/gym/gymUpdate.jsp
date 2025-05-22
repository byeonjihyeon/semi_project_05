<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>헬스장 정보 등록/수정</title>
    <link rel="stylesheet" href="/resources/css/gym/gym_1.css">
    <link rel="stylesheet" href="/resources/css/gym/register.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<%--

 --%>
<style>
/* 테이블 전체 너비 명확히 설정 */
.info-table {
    width: 100%;
    table-layout: fixed; /* 셀 너비 균등 분배 */
    border-collapse: collapse;
}

/* 3열 구조 대응: td 스타일 */
.info-table td {
    padding: 10px;
    vertical-align: middle;
    border: 1px solid #ddd;
}

/* 열 너비 설정: 첫 번째 열은 좁게, 나머지 넓게 */
.info-table tr td:first-child {
    width: 20%;
    font-weight: bold;
    background-color: #f7f7f7;
}

/* input/textarea 너비 최대화 */
.info-table input[type="text"],
.info-table input[type="email"],
.info-table input[type="tel"],
.info-table input[type="password"],
.info-table input[type="file"],
.info-table textarea {
    width: 100%;
    padding: 6px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

/* 3열 이용권 row 대응: 두 번째 td 텍스트 (1개월 등) 스타일 조정 */
.info-table tr td:nth-child(2):not(:last-child) {
    width: 20%;
    text-align: center;
    background-color: #fafafa;
    font-weight: 500;
}

</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="main-container">
    <div class="sidebar">
        <h3>헬스장 관리자 페이지</h3>
        <ul>
            <li><a href="/gym/updateInfoFrm">헬스장 정보 등록/수정</a></li>
            <li><a href="/gym/changePasswordFrm">비밀번호 변경</a></li>
            <li><a href="/gym/paymentList">결제 내역 조회</a></li>
            <li><a href="/gym/memberList">회원 조회</a></li>
        </ul>
    </div>
    <div class="content">
        <h2>헬스장 정보 등록/수정</h2>
        <form action="/gym/updateInfo" method="post" enctype="multipart/form-data">
            <table class="info-table">
                <tr><td>헬스장명*</td><td colspan = "2"><input type="text" name="gymName" value = "${loginGym.gymName }"required></td></tr>
                <tr><td>헬스장 주소*</td><td colspan = "2"><input type="text" name="gymAddress" value = "${loginGym.gymAddr}"required></td></tr>
                <tr><td>이메일*</td><td colspan = "2"><input type="email" name="email" value = "${loginGym.email }"required></td></tr>
                <tr><td>전화번호*</td><td colspan = "2"><input type="tel" name="phone" value = "${loginGym.phone}"required></td></tr>
                <tr><td>사진</td><td colspan = "2"><input type="file" name="photo"></td></tr>
                <tr><td>운영시간</td><td colspan = "2"><input type="text" name="openHours" value = "${loginGym.openTime}"></td></tr>
                <tr><td>상세설명</td><td colspan = "2"><textarea name="description">${loginGym.detail}</textarea></td></tr>
                <tr><td>편의시설</td><td colspan = "2"><input type="text" name="facilities" value = "${loginGym.facilities}"></td></tr>
                <tr><td rowspan = "5">이용권*</td><td>1개월</td><td><input type="text" name="oneMonth" value = "${loginGym.ticket.oneMonth}"required></td></tr>
                <tr><td>3개월</td><td><input type="text" name="threeMonth" value = "${loginGym.ticket.threeMonth}" required></td></tr>
                <tr><td>6개월</td><td><input type="text" name="sixMonth" value = "${loginGym.ticket.sixMonth}"required></td></tr>
                <tr><td>12개월</td><td><input type="text" name="oneYear" value = "${loginGym.ticket.oneYear}"required></td></tr>
                <tr><td>일일권</td><td><input type="text" name="oneDay" value = "${loginGym.ticket.oneDay}" required></td></tr>
                
            </table>
            <div class="form-actions">
                <button type="submit">등록/수정하기</button>
            </div>
        </form>
    </div>
    
</div>
<div class = "withdraw-link">
	<a href="/gym/deleteAccount">회원 탈퇴하기</a>
</div>
    	<script>
    	console.log('${loginGym.ticket}');
    	</script>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
