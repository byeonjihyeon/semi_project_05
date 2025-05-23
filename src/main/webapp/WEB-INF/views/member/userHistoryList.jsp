<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Orbit&display=swap');
body {
    font-family: 'Orbit', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
.mypage-container {
    display: flex;
    padding: 40px;
    min-height: 700px;
    width: 1200px;
    margin: 0 auto;
    font-family: 'Segoe UI', sans-serif;
}

/* 사이드바 스타일 */
.sidebar {
    border-top-left-radius: 12px;
    border-bottom-left-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.06);
    border-right: 1px solid #eee;
    width: 240px;
    background-color: #1a1a1a;
    color: #fff;
    padding: 30px 20px;
}
.sidebar h3 {
  font-size: 18px;
  margin-bottom: 20px;
  color: #333;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 14px;
}

.sidebar ul li a {
  text-decoration: none;
  color: #333;
  font-weight: 500;
}

.sidebar ul li a:hover {
  color: #ff414d;
  font-weight: bold;
}

/* 메인 콘텐츠 */
.main-content {
    flex: 1;
    background-color: #fff;
    padding: 40px;
    border-top-right-radius: 12px;
    border-bottom-right-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.06);
}

.main-content h2 {
    text-align: center;
    margin-bottom: 40px;
    font-size: 22px;
    color: #222;
}

.welcome-box {
    display: flex;
    align-items: center;
    margin-bottom: 30px;
    padding: 20px;
    border: 1px solid #eee;
    border-radius: 12px;
    background-color: #fafafa;
    box-shadow: 0 2px 8px rgba(0,0,0,0.04);
}
.welcome-box img {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    margin-right: 20px;
}
.welcome-box p {
    margin: 6px 0;
    font-size: 15px;
    color: #333;
}

.gym-photo {
    width: 25%;
    max-width: 600px;
    height: 150px;
    border: 1px solid #ccc;
    border-radius: 12px;
    background-color: #f9f9f9;
    color: #999;
    font-size: 16px;
    display: flex;
    align-items: center;
    justify-content: center;
    
    box-shadow: 0 2px 6px rgba(0,0,0,0.05);
}
</style>
</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
<main>
	<div class="mypage-container">
    <!-- 사이드바 -->
    <div class="sidebar">
        <h3><a href=/member/myPageFrm>마이페이지</a></h3>
        <ul>
            <li><a href="/member/updateMemberFrm">회원 정보 수정</a></li>
            <li><a href="/member/updatePwFrm">비밀번호 변경</a></li>
            <li><a href="/member/userHistoryList" style="color:red;">이용 내역 조회</a></li>
            <li><a href="#">결제 내역 조회</a></li>
            <li><a href="/member/recordGrowth?reqPage=1">나의 몸무게 일지</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2>이용내역 조회</h2>
        <div class="welcome-box">
            <c:forEach var="i" items="${usingInfo}">
            	${usingInfo}
            </c:forEach>
        </div>

    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>