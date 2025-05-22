<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
    <link rel="stylesheet" href="/resources/css/gym/gym_1.css">
    <link rel="stylesheet" href="/resources/css/gym/register.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
        <h2>비밀번호 변경</h2>
        <form action="/gym/changePassword" method="post">
            <table class="info-table">
                <tr><td>현재 비밀번호</td><td><input type="password" name="currentPw" required></td></tr>
                <tr><td>변경할 비밀번호</td><td><input type="password" name="newPw" required></td></tr>
                <tr><td>비밀번호 확인</td><td><input type="password" name="confirmPw" required></td></tr>
            </table>
            <div class="form-actions">
                <button type="submit">비밀번호 변경하기</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
