<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헬스장 조회</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  	<div class="container">
			<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
				<main class="content">
		        	<section class='section notice-list-wrap'>
					<div class='page-title'>헬스장 관리</div>
						<div class='list-content'>
							<table class='adminTbl tbl-hover'>
								<tr>
									<th style='width:15%;'>헬스장명</th>
									<th style='width:15%;'>아이디</th>
									<th style='width:15%;'>이메일</th>
									<th style='width:15%;'>가입일</th>
									<th style='width:15%'>관리</th>
								</tr>
								<%-- 나중에 수행해야할 리스트 출력
								<c:forEach var='member' items="${memberList}">	
									<tr>
										<td>${member.memberId}</td>
										<td>${member.memberPw}</td>
										<td>${member.memberGrade}</td>
										<td>${member.memberEmail}</td>
										<td>${member.memberDate}</td>
										<td>
											<button type='submit'>조회</button>
											<button>삭제</button>
										</td>
									</tr>
								</c:forEach>
								 --%>
							</table>
						</div>
				<%--	<div id='pageNavi'>${pageNavi}</div> 페이지네이션 추가해야함 --%>
				</section>	
		   		</main>
		</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>  
</body>
</html>