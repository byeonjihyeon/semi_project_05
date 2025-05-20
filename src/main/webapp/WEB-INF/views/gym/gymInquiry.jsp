<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- gymInquiry.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${gym.name} - 문의</title>
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
	            <a href="gymDetail.jsp?gymId=${gym.id}" class="tab">상세</a>
	            <a href="gymReview.jsp?gymId=${gym.id}" class="tab">리뷰</a>
	            <a href="gymInquiry.jsp?gymId=${gym.id}" class="tab selected">문의</a>
	        </div>
	
	        <div class="inquiry-list">
	            <table>
	                <thead>
	                    <tr>
	                        <th>번호</th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <c:forEach var="inquiry" items="${gym.inquiries}" varStatus="status">
	                        <tr>
	                            <td>${status.index + 1}</td>
	                            <td>${inquiry.title}</td>
	                            <td>${inquiry.username}</td>
	                            <td>${inquiry.date}</td>
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
