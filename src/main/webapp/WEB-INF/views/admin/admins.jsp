<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<srcipt src=https://code.jquery.com/jquery-3.7.1.min.js></srcipt>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 권한 설정</title>
<style>
button {
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  color: #333;
  padding: 6px 12px;
  font-size: 14px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

/* 검색창 (input type=text) */
input[type="text"] {
  padding: 6px 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
  transition: border-color 0.2s ease;
  height: 34px;
  vertical-align: middle;
}

input[type="text"]:focus {
  border-color: #888;
}

/* 조회 버튼 (input type=button) */
input[type="button"] {
  padding: 6px 12px;
  font-size: 14px;
  background-color: #f0f0f0;
  color: #333;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
  height: 36px;
  vertical-align: middle;
  margin-left: 6px;
  transition: background-color 0.2s ease;
}

select {
  -webkit-appearance: none;
  -moz-appearance: none;

  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 6px 10px;
  font-size: 14px;
  color: #333;
  width: 80px;
  height: 36px;
  vertical-align: middle;
  outline: none;
  cursor: pointer;
 
  /* 오른쪽 화살표 아이콘 (SVG) */
  background-image: url("data:image/svg+xml;utf8,<svg fill='gray' height='18' viewBox='0 0 24 24' width='18' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
}

select:focus {
  border-color: #888;
}

[name='search']{
width: 150px;
}
</style>
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
								<th style='width:20%'>관리</th>
								
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