<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>헬스장 상세</title>
    <link rel="stylesheet" href="/resources/css/gym/gym-2.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	

    <main>
	    <div class="gym-container">
	        <div class="gym-header">
	            <h1>${gym.gymName}</h1>
	            <p class="location">📍 ${gym.gymAddr}</p>
	        </div>
	
	        <div class="image-gallery">
	            <img src="" class="main-photo" alt="헬스장 대표사진" />
	            <div class="thumbnail-container">
	                <c:forEach var="photo" items="" varStatus="status">
	                    <img src="" class="thumbnail" alt="헬스장 사진 " />
	                </c:forEach>
	            </div>
	        </div>
	
	        <div class="tab-menu">
	            <a href="/gym/detail?gymId=${gym.gymId}" class="tab selected">상세</a>
	            <a href="/gym/review?gymId=${gym.gymId}" class="tab">리뷰</a>
	            <a href="/gym/inquiry?gymId=${gym.gymId}" class="tab">문의</a>
	        </div>
	
	        <div class="gym-info">
	            <h2>헬스장 정보</h2>
	            <p>${gym.detail}</p>
	        </div>
	
	        <div class="membership-table">
	            <table>
	                <thead>
	                    <tr>
	                        <th>이용권</th>
	                        <th>헬스</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <tr>
	                    <td>1개월</td>
	                    <td>${gym.ticket.oneMonth}</td>
	                    </tr>
	                    <tr>
	                    <td>3개월</td>
	                    <td>${gym.ticket.threeMonth}</td>
	                    </tr>
	                    <tr>
	                    <td>6개월</td>
	                    <td>${gym.ticket.sixMonth}</td>
	                    </tr>
	                    <tr>
	                    <td>12개월</td>
	                    <td>${gym.ticket.oneYear}</td>
	                    </tr>
	                    <tr>
	                    <td>일일권</td>
	                    <td>${gym.ticket.oneDay}</td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	             <!-- 하단 버튼 (예: 회원권 구매) -->
	         <div class="action-bar">
	             <button onclick="location.href='/ticket/purchase?gymId=${gym.gymId}'">회원권 구매</button>
	         </div>
	    </div>
	    
         
        
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
    <script>
    console.log(${gym.ticket});
    </script>
</body>
</html>
