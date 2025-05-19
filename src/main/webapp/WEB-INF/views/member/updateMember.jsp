<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
            <li><a href="/gym/updateInfo">헬스장 정보 등록/수정</a></li>
            <li><a href="/gym/changePassword">비밀번호 변경</a></li>
            <li><a href="/gym/postList">글쓴 내역 조회</a></li>
            <li><a href="/gym/reviewList">회원 조회</a></li>
            <li><a href="/gym/quit">회원 탈퇴</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2 style="text-align: center;">회원 정보 수정</h2>
       		
       		<div>
       			<form action="/member/update" method="post">
       				<div class="input-group">
       					<label for="userName">이름</label>
       					<input type="text" id="userName" name="userName" value="${sessionScope.loginMember.memberName}" >
       				</div>
       			</form>
       		</div>
    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script >

</script>
</body>

</html>






