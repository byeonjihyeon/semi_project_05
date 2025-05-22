<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬스장 등록 신청내역 조회</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	        	<section class='section notice-list-wrap'>
				<div class='page-title'>헬스장 등록 신청내역</div>
					<div class='list-content'>
						<table class='adminTbl tbl-hover'>
							<tr>
								<th style='width:15%'></th>
								<th style='width:15%;'>헬스장명</th>
								<th style='width:15%;'>아이디</th>
								<th style='width:15%;'>신청일자</th>
								<th style='width:15%'>상태</th>
							</tr>
							
							<c:forEach var='gym' items="${gymApplications}">	
								<tr>
									<td>${gym.gymName}</td>
									<td>${gym.gymId}</td>
									<td></td>
									<td>${gym.gymName}</td>
									<td>
										<button type='submit'>조회</button>
										<button>삭제</button>
									</td>
								</tr>
							</c:forEach>
							 
						</table>
					</div>
					<div id='applyNavi'>${applyNavi}</div>
			</section>	
	   		</main>
	</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>  
</body>
</html>