<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬스장 마이페이지</title>
<style>
    .mypage-container {
        display: flex;
        margin: 30px;
        min-height: 600px;
    }
    .sidebar {
        width: 220px;
        border-right: 1px solid #ccc;
        padding: 20px;
    }
    .sidebar h3 {
        margin-bottom: 20px;
        font-size: 18px;
    }
    .sidebar ul {
        list-style: none;
        padding: 0;
    }
    .sidebar ul li {
        margin-bottom: 12px;
    }
    .sidebar ul li a {
        color: #333;
        text-decoration: none;
    }
    .main-content {
        flex: 1;
        padding: 30px;
    }
    .main-content h2 {
        margin-bottom: 20px;
    }
    .welcome-box {
        display: flex;
        align-items: center;
        margin-bottom: 30px;
    }
    .welcome-box img {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        margin-right: 20px;
    }
    .gym-photo {
        width: 400px;
        height: 250px;
        border: 1px solid #ccc;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 16px;
        color: #999;
        background-color: #f9f9f9;
    }
</style>
</head>


<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
<main>
	<div class="mypage-container">
    <!-- 사이드바 -->
    <div class="sidebar">
        <h3>헬스장 관리자 페이지</h3>
        <ul>
            <li><a href="/gym/updateInfoFrm">헬스장 정보 등록/수정</a></li>
            <li><a href="/gym/changePassword">비밀번호 변경</a></li>
            <li><a href="/gym/paymentList">결제 내역 조회</a></li>
            <li><a href="/gym/memberList">회원 조회</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2>마이페이지 (첫화면)</h2>
        <div class="welcome-box">
            <img src="/resources/images/user-icon.png" alt="User Icon" />
            <div>
                <p><strong>${sessionScope.loginMember.memberId}</strong>님, 환영합니다!</p>
                <p>회원님의 등급은 <strong>${sessionScope.loginMember.grade}</strong>입니다.</p>
                <p>※ 프로필 설정 및 활동 통계는 추후 추가 예정입니다.</p>
            </div>
        </div>
        <div class="gym-photo">
            [헬스장 사진]
        </div>
    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>

</html>






