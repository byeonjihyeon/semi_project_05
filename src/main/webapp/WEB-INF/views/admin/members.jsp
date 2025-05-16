<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<srcipt src=https://code.jquery.com/jquery-3.7.1.min.js>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
.list-content {
	height: 700px;
}
</style>
</head>
<body>
 <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	        	<section class='section notice-list-wrap'>
				<div class='page-title'>회원 관리</div>
					<div class='list-content'>
						<table class='adminTbl tbl-hover'>
							<tr>
								<th style='width:10%;'>NO</th>
								<th style='width:15%;'>회원 아이디</th>
								<th style='width:15%;'>이름</th>
								<th style='width:15%;'>등급</th>
								<th style='width:15%;'>이메일</th>
								<th style='width:15%;'>가입일</th>
								<th style='width:15%'>관리</th>
							</tr>
							<c:forEach var='member' items="${memberList}" varStatus='status'>	
								<tr>
									<td>${(status.index + 1) +(currentPage -1) * pageSize}</td>
									<td id='memberId'>${member.memberId}</td>
									<td>${member.memberName}</td>
									<td>${member.memberGrade}</td>
									<td>${member.memberEmail}</td>
									<td>${member.memberDate}</td>
									<td>
										<a href="/admin/member/details?id=${member.memberId}"><button>조회</button></a>
										<a href="#"><button onclick="deleteMember('${member.memberId}')">삭제</button></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				<div id='pageNavi'>${pageNavi}</div>
			</section>	
	   		</main>
	</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>   
</body>
<script>
function deleteMember(memberId) {
	
	  swal({
		 title : "삭제",
		 text : "삭제 하시겠습니까?",
		 icon : "warning",
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
		if(val){ //삭제 버튼 클릭시 동작
			console.log(memberId);
			location.href="/admin/member/delete?id="+ memberId;
		}  
	  });
}
</script>
</html>