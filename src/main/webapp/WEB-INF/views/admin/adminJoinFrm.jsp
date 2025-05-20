<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  	<div class="container">
			<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
				<main class="content">
		      		 <div class="member-info-card">
				  <h2>관리자 등록</h2>
				 	 <table class="member-info-table">
					    <tr>
					    	<th>아이디</th>
					    	<td><input type='text' name='adminId'><button>중복 확인</button></td>
					    	<td></td>
				    	</tr>
					    <tr>
						    <th>이름</th>
						    <td><input type='text' name='adminName'></td>
					    </tr>
					     <tr>
						    <th>이메일</th>
						    <td><input type='text' name='adminName'></td>
					    </tr>
					    <tr>
					    	<th colspan='4'>권한</th>
				    	</tr>
				    	<tr>
				    		<th>회원 관리</th>
				    		<td>
					    		<input type='checkbox' value='selectMember'>조회
		    				 	<input type='checkbox' value='updateMember'>수정
		    				    <input type='checkbox' value='deleteMember'>삭제
				    	</tr>
				    	<tr>
				    		<th>헬스장 관리</th>
				    		<td>
					    		<input type='checkbox' value='selectGym'>조회
		    				 	<input type='checkbox' value='updateGym'>수정
		    				    <input type='checkbox' value='deleteGym'>삭제
				    	</tr>
				    	<tr>
				    		<th>게시판 관리</th>
				    		<td>
					    		<input type='checkbox' value='selectBoard'>조회
		    				 	<input type='checkbox' value='updateBoard'>수정
		    				    <input type='checkbox' value='deleteBoard'>삭제
				    	</tr>
				  </table>
				
				  <div class="member-info-button-wrapper">
		  		   	<a href='javascript:void(0);' onclick="updateMemberInfo('${memberDetails.memberId}');">
					    <button class="member-info-btn">등록</button>
					</a>
				  </div>
				</div>  	
		   		</main>
		</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>  
</body>
</html>