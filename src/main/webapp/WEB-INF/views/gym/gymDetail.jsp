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
	            <h1>${gym.name}</h1>
	            <p class="location">📍 ${gym.address}</p>
	        </div>
	
	        <div class="image-gallery">
	            <img src="${gym.mainPhoto}" class="main-photo" alt="헬스장 대표사진" />
	            <div class="thumbnail-container">
	                <c:forEach var="photo" items="${gym.photos}" varStatus="status">
	                    <img src="${photo}" class="thumbnail" alt="헬스장 사진 ${status.index+1}" />
	                </c:forEach>
	            </div>
	        </div>
	
	        <div class="tab-menu">
	            <a href="gymDetail.jsp?gymId=${gym.id}" class="tab selected">상세</a>
	            <a href="gymReview.jsp?gymId=${gym.id}" class="tab">리뷰</a>
	            <a href="gymInquiry.jsp?gymId=${gym.id}" class="tab">문의</a>
	        </div>
	
	        <div class="gym-info">
	            <h2>헬스장 정보</h2>
	            <p>${gym.description}</p>
	        </div>
	
	        <div class="membership-table">
	            <table>
	                <thead>
	                    <tr>
	                        <th>이용권</th>
	                        <th>헬스</th>
	                        <th>운동복</th>
	                        <th>사물함</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <c:forEach var="ticket" items="${gym.tickets}">
	                        <tr>
	                            <td>${ticket.period}</td>
	                            <td>${ticket.price}원</td>
	                            <td>${ticket.uniform}</td>
	                            <td>${ticket.locker}</td>
	                        </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
	    </div>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
</body>
</html>
