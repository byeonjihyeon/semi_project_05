<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<srcipt src=https://code.jquery.com/jquery-3.7.1.min.js></srcipt>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 권한 설정</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	        	<section class='section notice-list-wrap'>
				<div class='page-title'>관리자 조회</div>
					<div class='list-content'>
						<div>
							<form action='javascript:void(0)' method="get">
								<select id='searchField' name='searchField'>
									<option value='admin_id'>아이디</option>
									<option value='admin_name'>이름</option>
								</select>
								<input type='text' name='search' id='searchValue'>
								<input type='button' id='search' value='조회'>
							</form>
						</div>
						<table class='adminTbl tbl-hover'>
							<tr>
								<th style='width:15%;'>관리자 아이디</th>
								<th style='width:15%;'>이름</th>
								<th style='width:15%;'>전화번호</th>
								<th style='width:15%;'>이메일</th>
								<th style='width:15%;'>담당업무</th>
								<th style='width:15%'>관리</th>
								
							</tr>
							<c:forEach var='admin' items="${adminList}" varStatus='status'>	
								<tr class='adminlist'>
								<%-- 	<td>${(status.index + 1) +(currentPage -1) * pageSize}</td>--%>
									<td id='adminId'>${admin.memberId}</td>
									<td>${admin.memberName}</td>
									<td>${admin.memberPhone}</td>
									<td>${admin.memberEmail}</td>
									<td>${admin.memberGrade}</td>
									<td>
										<a href="/admin/super/details?id=${admin.memberId}"><button>조회</button></a>
										<a href="#"><button onclick="deleteAdmin('${admin.memberId}')">삭제</button></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
			</section>	
	   		</main>
	</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>   	
<script>
//관리자 삭제
function deleteAdmin(adminId) {
	swal({
		icon : "warning",
		text : "삭제 하시겠습니까?",
		buttons : {
			cancel : {
				text : "취소",
				value : false,
				visible : true,
				closeModal : true
			},
			confirm : {
				text : "삭제",
				value : true,
				visible : true,
				closeModal : true
			}
		}
	}).then(function(val){
		if(val){
			location.href="/admin/super/delete?id="+adminId;
		}
	});
}
</script>
</body>
</html>