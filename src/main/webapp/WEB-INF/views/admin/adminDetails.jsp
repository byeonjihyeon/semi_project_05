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
#roleChgBtn {
padding: 5px 10px;
    font-size: 0.9rem;
    background-color: #CE2029;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-left: 15px;
}
select {
  appearance: none; /* 기본 브라우저 스타일 제거 */
  -webkit-appearance: none;
  -moz-appearance: none;

  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 8px 12px;
  font-size: 14px;
  color: #333;
  width: 200px;
  outline: none;
  cursor: pointer;
  background-image: url("data:image/svg+xml;utf8,<svg fill='gray' height='18' viewBox='0 0 24 24' width='18' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
}

select:focus {
  border-color: #CE2029;
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
					    	<th>담당</th>
					    	<td>
					    		<select name='role'>
					    			<option value='allMng' ${adminDetails[1].memberGrade == '총괄 관리자' ? 'selected' : ''}>총괄 관리자</option>
					    			<option value='memberMng' ${adminDetails[1].memberGrade == '회원 관리자' ? 'selected' : ''}>회원 관리자</option>
					    			<option value='gymMng' ${adminDetails[1].memberGrade == '헬스장 관리자' ? 'selected' : ''}>헬스장 관리자</option>
					    			<option value='boardMng' ${adminDetails[1].memberGrade == '게시판 관리자' ? 'selected' : ''}>게시판 관리자</option>
					    			<option value='noMng' ${adminDetails[1].memberGrade == '미정' ? 'selected' : ''}>미정</option>
					    		</select>
					    		<button id='roleChgBtn'>변경</button></td>
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
					        <input type="checkbox" id='adminSel' name="sel" value="Y" ${adminDetails[0].selYN eq 'Y' ? 'checked' : ''}>
					        <label for='adminSel'>조회</label>
					        <input type="checkbox" id= 'adminUpd' name="upd" value="Y" ${adminDetails[0].updYN eq 'Y' ? 'checked' : ''}>
					        <label for='adminUpd'>수정</label>
					        <input type="checkbox" id='adminDel' name="del" value="Y" ${adminDetails[0].delYN eq 'Y' ? 'checked' : ''}>
					        <label for='adminDel'>삭제</label>
					   	    <button type="submit" class="previliegeBtn member-info-btn-small" >변경</button>
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
					        <input type="checkbox" id='memberSel' name="sel" value="Y" ${adminDetails[1].selYN eq 'Y' ? 'checked' : ''}>
					        <label for='memberSel'>조회</label>
					        <input type="checkbox" id='memberUpd' name="upd" value="Y" ${adminDetails[1].updYN eq 'Y' ? 'checked' : ''}>
					        <label for='memberUpd'>수정</label>
					        <input type="checkbox" id='memberDel' name="del" value="Y" ${adminDetails[1].delYN eq 'Y' ? 'checked' : ''}>
					        <label for='memberDel'>삭제</label>
					        <button type="submit" class="previliegeBtn member-info-btn-small">변경</button>
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
					        <input type="checkbox" id='gymSel' name="sel" value="Y" ${adminDetails[2].selYN eq 'Y' ? 'checked' : ''}>
					        <label for='gymSel'>조회</label>
					        <input type="checkbox" id='gymUpd' name="upd" value="Y" ${adminDetails[2].updYN eq 'Y' ? 'checked' : ''}>
					        <label for='gymUpd'>수정</label>
					        <input type="checkbox" id='gymDel' name="del" value="Y" ${adminDetails[2].delYN eq 'Y' ? 'checked' : ''}>
					        <label for='gymDel'>삭제</label>
					        <button type="submit" class="previliegeBtn member-info-btn-small">변경</button>
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
					        <input type="checkbox" id='boardSel' name="sel" value="Y" ${adminDetails[3].selYN eq 'Y' ? 'checked' : ''}>
					        <label for='boardSel'>조회</label>
					        <input type="checkbox" id='boardUpd' name="upd" value="Y" ${adminDetails[3].updYN eq 'Y' ? 'checked' : ''}>
					        <label for='boardUpd'>수정</label>
					        <input type="checkbox" id='boardDel' name="del" value="Y" ${adminDetails[3].delYN eq 'Y' ? 'checked' : ''}>
					        <label for='boardDel'>삭제</label>
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
//이메일 유효성 및 중복확인 검사
$('#roleChgBtn').on('click',function(){
	let role = $('[name=role]'); //select 요소 추출
	let id = '${adminDetails[0].memberId}';
	$.ajax({
		url : "/admin/super/chgAdminGrade",
		data : {"grade" : role.val(), "id" : id },
		type : "get",
		success : function(res){
			if(res>0){
				swal({
					icon : "success",
					text : "변경 완료하였습니다."
				});
			}else{
				swal({
					icon : "error",
					text : "변경중 오류가 발생하였습니다."
				});
			}
		},
		error : function(){
			console.log('ajax 오류');
		}
	});
	
	
});


//권한 변경 메소드
function confirmMsg(){
	if(!confirm('권한을 변경하시겠습니까?')){
		return false;
	}
}

</script>
</html>