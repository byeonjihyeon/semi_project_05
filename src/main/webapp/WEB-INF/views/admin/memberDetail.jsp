<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 세부정보 조회</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	       		 <div class="member-info-card">
				  <h2>회원 상세 정보</h2>
				
				  <table class="member-info-table">
					    <tr>
					    	<th>회원 아이디</th>
					    	<td>${memberDetails.memberId}</td>
				    	</tr>
					    <tr>
						    <th>이름</th>
						    <td>${memberDetails.memberName}</td>
					    </tr>
					    <tr>
					   	    <th>비밀번호</th>
					    	<td><button class="member-info-btn-small">초기화</button></td>
					    </tr>
					    <tr>
					    	<th>전화번호</th>
					    	<td>${memberDetails.memberPhone}</td>
				    	</tr>
					    <tr>
					    	<th>이메일</th>
					    	<td>${memberDetails.memberEmail}</td>
				    	</tr>
					    <tr>
					    	<th>주소</th>
					    	<td>${memberDetails.memberAddr}</td>
				    	</tr>
					    <tr>
					    	<th>등급</th>
					    	<td>${memberDetails.memberGrade}</td>
				    	</tr>
				  </table>
				
				  <div class="member-info-button-wrapper">
		  		   	<a href='javascript:void(0);' onclick="updateMemberInfo('${memberDetails.memberId}');">
					    <button class="member-info-btn">정보수정</button>
					</a>
					    <button class="member-info-btn member-info-btn-secondary">이용내역</button>
				  </div>
				</div>
	   		</main>
	</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script>
	function updateMemberInfo(memberId){
		let popupWidth = 500;
		let popupHeight = 650;
		
		let top = screen.availHeight / 2 - popupHeight /2;
		let left = screen.availWidth /2 - popupWidth / 2;
		
		window.open("/admin/member/updateFrm?id="+memberId, "updateMemberInforFrm", "width=" + popupWidth + ",height=" + popupHeight +", top=" + top +", left="+left);
		
	}
</script>   
</body>
</html>