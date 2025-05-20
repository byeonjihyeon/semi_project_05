<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<srcipt src=https://code.jquery.com/jquery-3.7.1.min.js></srcipt>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상세 페이지</title>
<style>
span {
font-size: 15px;
font-weight: normal;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	       		 <div class="member-info-card">
				  <h2>관리자 상세 정보</h2>
				 	 <table class="member-info-table">
					    <tr>
					    	<th>관리자 아이디</th>
					    	<td>${adminDetails[0].memberId}</td>
				    	</tr>
					    <tr>
						    <th>이름</th>
						    <td>${adminDetails[0].memberName}</td>
					    </tr>
					    <tr>
					   	    <th>비밀번호</th>
					    	<td><button class="member-info-btn-small">초기화</button></td>
					    </tr>
					    <tr>
					    	<th>전화번호</th>
					    	<td>${adminDetails[0].memberPhone}</td>
				    	</tr>
					    <tr>
					    	<th>이메일</th>
					    	<td>${adminDetails[0].memberEmail}</td>
				    	</tr>
					    <tr>
					    	<th>주소</th>
					    	<td>${adminDetails[0].memberAddr}</td>
				    	</tr>
					    <tr>
					    	<th>담당업무</th>
					    	<td>${adminDetails[0].memberGrade}</td>
				    	</tr>    	
					   </table>
				    
				    <h2>권한 <span>(최근 변경일 : ${adminDetails[0].regDate})</span></h2> 
				    <table class="member-info-table">
					
					  <!-- 관리자 -->
					  <form id="adminForm" action="/admin/super/updatePrivilege" method="get" onsubmit='return confirmMsg()'>
					    <tr>
					      <th>관리자</th>
					      <td>
					    	<input type="hidden" name="id" value="${adminDetails[0].memberId}">
					      	<input type="hidden" name="url" value="/admin/super">
					        <input type="checkbox" name="sel" value="Y" ${adminDetails[0].selYN eq 'Y' ? 'checked' : ''}>조회
					        <input type="checkbox" name="upd" value="Y" ${adminDetails[0].updYN eq 'Y' ? 'checked' : ''}>수정
					        <input type="checkbox" name="del" value="Y" ${adminDetails[0].delYN eq 'Y' ? 'checked' : ''}>삭제
					   	    <button type="submit" class="previliegeBtn member-info-btn-small" >수정</button>
					      </td>
					    </tr>
					  </form>
					
					  <!-- 회원 -->
					  <form id="memberForm" action="/admin/super/updatePrivilege" method="get" onsubmit='return confirmMsg()'>
					    <tr>
					      <th>회원</th>
					      <td>
					   	    <input type="hidden" name="id" value="${adminDetails[0].memberId}">
					  	  	<input type="hidden" name="url" value="/admin/member">
					        <input type="checkbox" name="sel" value="Y" ${adminDetails[1].selYN eq 'Y' ? 'checked' : ''}>조회
					        <input type="checkbox" name="upd" value="Y" ${adminDetails[1].updYN eq 'Y' ? 'checked' : ''}>수정
					        <input type="checkbox" name="del" value="Y" ${adminDetails[1].delYN eq 'Y' ? 'checked' : ''}>삭제
					        <button type="submit" class="previliegeBtn member-info-btn-small">수정</button>
					      </td>
					    </tr>
					  </form>
					
					  <!-- 헬스장 -->
					  <form id="gymForm" action="/admin/super/updatePrivilege" method="get" onsubmit='return confirmMsg()'>
					    <tr>
					      <th>헬스장</th>
					      <td>
					        <input type="hidden" name="id" value="${adminDetails[0].memberId}">
					      	<input type="hidden" name="url" value="/admin/gym">
					        <input type="checkbox" name="sel" value="Y" ${adminDetails[2].selYN eq 'Y' ? 'checked' : ''}>조회
					        <input type="checkbox" name="upd" value="Y" ${adminDetails[2].updYN eq 'Y' ? 'checked' : ''}>수정
					        <input type="checkbox" name="del" value="Y" ${adminDetails[2].delYN eq 'Y' ? 'checked' : ''}>삭제
					        <button type="submit" class="previliegeBtn member-info-btn-small">수정</button>
					      </td>
					    </tr>
					  </form>
					
					  <!-- 게시판 -->
					  <form id="boardForm" action="/admin/super/updatePrivilege" method="get" onsubmit='return confirmMsg()'>
					    <tr>
					      <th>게시판</th>
					      <td>
					      	<input type="hidden" name="id" value="${adminDetails[0].memberId}">
					      	<input type="hidden" name="url" value="/admin/board">
					        <input type="checkbox" name="sel" value="Y" ${adminDetails[3].selYN eq 'Y' ? 'checked' : ''}>조회
					        <input type="checkbox" name="upd" value="Y" ${adminDetails[3].updYN eq 'Y' ? 'checked' : ''}>수정
					        <input type="checkbox" name="del" value="Y" ${adminDetails[3].delYN eq 'Y' ? 'checked' : ''}>삭제
					        <button type="submit" class="previliegeBtn member-info-btn-small">수정</button>
					      </td>
					    </tr>
					  </form>
					</table>
				
				
				  
				</div>
	   		</main>
	</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
function confirmMsg(){
	if(!confirm('권한을 변경하시겠습니까?')){
		return false;
	}
}

</script>
</html>