<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	       		 <div class="member-info-card">
				  <h2>헬스 신청 상세 정보</h2>
				 	 <table class="member-info-table">
					    <tr>
					    	<th>등록신청번호</th>
					    	<td>${gymApplication.gymApplication.insertGymNo}</td>
				    	</tr>
					    <tr>
						    <th>신청일자</th>
						    <td>${gymApplication.gymApplication.screeningDate}</td>
					    </tr>
					    <tr>
					   	    <th>헬스장명</th>
					    	<td>${gymApplication.gymName}</td>
					    </tr>
					    <tr>
					    	<th>계정</th>
					    	<td>${gymApplication.gymId}</td>
				    	</tr>
				    	<tr>
					    	<th>연락처</th>
					    	<td>${gymApplication.phone}</td>
				    	</tr>
					    <tr>
					    	<th>주소</th>
					    	<td>${gymApplication.gymAddr}</td>
				    	</tr>
				    	<tr>
					    	<th>편의시설</th>
					    	<td>${gymApplication.convenience}</td>
				    	</tr>
					    <tr>
					    	<th>세부내용</th>
					    	<td>${gymApplication.detail}</td>
				    	</tr>
					    <tr>
					    	<th>첨부서류</th>
					    		<c:forEach>
					    		</c:forEach>
					    	
				    	</tr>
				  </table>
				
				  <div class="member-info-button-wrapper">
		  		   	<a href='javascript:void(0);' onclick="#">
					    <button class="member-info-btn">변경</button>
					</a>
					    <button class="member-info-btn member-info-btn-secondary">뒤로가기</button>
				  </div>
				</div>
	   		</main>
</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>