<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 --> 
<script src="/resources/js/sweetalert.min.js"></script> <!-- 로그인 결과 알림창 용도 -->  
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- jQuery 연결 -->     
<style>
	.logo img {
	width: 50px;
	}
	
	#adminTitle {
		margin-bottom: 20px;
		font-size: 20px;
		font-weight: bold;
	}
</style>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
	<div class="login-container">
	     <div class="logo">
	         <img src="your-logo.png" alt="헬스장 플랫폼 로고" />
	     </div>
	     <form action="/admin/main" method="post" autocomplete="off" onsubmit="return validateForm()" class="login-form">
	     <div id='adminTitle'>
	     	<span>관리자 로그인</span>
	     </div> 
	
	         <!-- 아이디/비밀번호 입력 필드 -->
	         <div class="input-group">
	             <label for="userId">아이디</label>
	             <input type="text" id="loginId" name="loginId" placeholder="아이디를 입력하세요">
	         </div>
	         <div class="input-group">
	             <label for="password">비밀번호</label>
	             <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">
	         </div>
	
	         <!-- 로그인 버튼 -->
	         <button type="submit" class="login-btn">로그인</button>
	     </form>
	     <div class="links">
	         <a href="#">비밀번호 찾기</a> 
	     </div>
	 </div>
	 <script>
	 	//로그인 버튼 클릭시, 아이디 또는 비밀번호 입력 x -> 경고창
	 	function validateForm(){
	 		//아이디 입력하지 않았을 때
	 		if($('#loginId').val().length < 1){ 
	 			
	 			swal({
	 				title : "알림",
	 				text : "아이디를 입력하세요.",
	 				icon : "warning"
	 			});
	 			
	 			return false;
	 		}
	 		
	 		//비밀번호를 입력하지 않았을 때
	 		if($('#password').val().length < 1){
	 			
	 			swal({
	 				title : "알림",
	 				text : "비밀번호를 입력하세요.",
	 				icon : "warning"
	 			});
	 			
	 			return false;
	 		}
	 	}
	 </script>
</body>
</html>