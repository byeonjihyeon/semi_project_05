<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- gymReview.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>${gym.name} - 리뷰</title>
    <link rel="stylesheet" href="/resources/css/gym/gym-2.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	            <a href="gymDetail.jsp?gymId=${gym.id}" class="tab">상세</a>
	            <a href="gymReview.jsp?gymId=${gym.id}" class="tab selected">리뷰</a>
	            <a href="gymInquiry.jsp?gymId=${gym.id}" class="tab">문의</a>
	        </div>
	
	        <div class="review-summary">
	            <p class="rating">⭐ ${gym.avgRating} / 5.0 (${gym.reviewCount}개 리뷰)</p>
	            <div class="rating-breakdown">
	                <c:forEach var="score" begin="5" end="1" step="-1">
	                    <div>${score}점: <span>${gym.ratingStats[score]}</span></div>
	                </c:forEach>
	            </div>
	        </div>
	
	        <div class="review-list">
	            <c:forEach var="review" items="${gym.reviews}">
	                <div class="review">
	                    <p class="review-user">👤 ${review.username}</p>
	                    <p class="review-rating">⭐ ${review.rating}</p>
	                    <p class="review-content">${review.content}</p>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
</body>
</html>
