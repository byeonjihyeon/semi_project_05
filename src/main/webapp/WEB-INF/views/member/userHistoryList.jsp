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
.main-content button {
    background-color: #1a1a1a;
    color: #fff;
    border: none;
    border-radius: 8px;
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.2s ease, transform 0.1s ease;
}

.main-content button:hover {
    background-color: #ff414d;
    transform: translateY(-2px);
}

.main-content button:active {
    transform: translateY(1px);
    background-color: #e63c45;
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
            <li><a href="/member/userHistoryList?reqPage=1" style="color: red;">이용 내역 조회</a></li>
            <li><a href="/member/recordGrowth">나의 몸무게 일지</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2>이용내역 조회</h2>
        <div class="welcome-box">
            
           <div style="width: 900px; display: flex; flex-wrap: wrap; gap: 20px;">
            
				<c:forEach var="i" items="${usingInfo}">
  					<div style="display: flex; align-items: center; gap: 20px; padding: 20px; width: 100%; margin-bottom: 30px; background-color: #fff; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">

					    <div style="width: 150px; height: 150px; background-color: #f0f0f0; display: flex; align-items: center; justify-content: center;">
      						<img src="/resources${i.gymFile.fileSavePath}${i.gymFile.filePath}" alt="헬스장 사진" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8px;">
    					</div>
	
    				<div style="flex: 1; display: flex; flex-direction: column; gap: 6px;">
      					    <input type="text" name="Period" id="Period" value="${i.usage.ticketPeriod}일 이용권" style="width: 302px; border: none; height: 30px; font: bold; font-size: 30px; padding-bottom: 10px;">       
          					<input type="text" name="usingNo" id="usingNo" value="주문번호 : ${i.payment.merchantId}" style="width: 302px; border: none; height: 30px;">
          					<input type="text" name="leftDate" id="leftDate" value="이용권 남은 기간 : ${i.usage.leftDate}일 " style="width: 302px; border: none; height: 30px;">
          					<input type="text" name="insertTicketDate" id="insertTicketDate" value="이용권 이용 등록날짜 : ${i.usage.enrollDate}" style="width: 302px; border: none; height: 30px;">
    				</div>

    
    				<div style="display: flex; flex-direction: column; gap: 10px;">
      					<form action="/member/payInfo">
      					<input type="hidden" name="paymentid" id="paymentid" value="${i.payment.paymentId}">
        					<button type="submit" style="width: 120px; height: 36px;">주문상세보기</button>
      					</form>
      					<form action="/member/gymReview">
        					<button type="submit" style="width: 120px; height: 36px;">리뷰작성</button>
      					</form>
      					<form action="/member/refund">
        					<button type="submit" style="width: 120px; height: 36px;">환불하기</button>
      					</form>	
      					<form action="/mem	ber/inquire">
        					<button type="submit" style="width: 120px; height: 36px;">판매자 문의</button>
				        </form>	
    					</div>
  					</div>
					</c:forEach>
				<div id="pageNami">${pageNavi}</div>
            </div><br>
            
        </div>

    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>