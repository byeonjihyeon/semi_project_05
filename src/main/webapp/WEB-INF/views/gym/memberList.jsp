<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 조회</title>
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
            <li><a href="/gym/changePassword">비밀번호 변경</a></li>
            <li><a href="/gym/paymentList">결제 내역 조회</a></li>
            <li><a href="/gym/memberList">회원 조회</a></li>
        </ul>
    </div>
    <div class="content">
        <h2>한 헬스장의 회원 목록</h2>
        <table class="info-table">
            <thead>
                <tr>
                    <th>회원 ID</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>가입일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="member" items="${memberList}">
                    <tr>
                        <td>${member.memberId}</td>
                        <td>${member.memberName}</td>
                        <td>${member.phone}</td>
                        <td>${member.enrollDate}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty memberList}">
                    <tr><td colspan="4">등록된 회원이 없습니다.</td></tr>
                </c:if>
            </tbody>
        </table>

        <div class="pagination">
            <c:forEach begin="1" end="10" var="i">
                <a href="/gym/memberList?page=${i}">${i}</a>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
