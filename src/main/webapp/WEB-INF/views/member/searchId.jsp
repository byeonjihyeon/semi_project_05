<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 -->  
 <script src:https://code.jquery.com/jquery-3.7.1.min.js></script> <!-- jquery 구글 -->
<!DOCTYPE html>

 <style>
	td {
	width: 100px;
	}
</style>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
	<div class="login-container">
      
      <form action="/member/searchId" method="POST" class="login-form" onsubmit='return validateForm()'>
          <table>
          	<tr style="text-align: center;">
          		<td colspan="3" style="padding-bottom: 30px">아이디 찾기</td>
          	</tr>
          	<tr>
          		<td style="padding-bottom: 15px; justify-content: center; text-align: center;">
          			<label for="userName" >이름 입력 : </label>
          		</td>
          		<td style="padding-right: 10px; ">
          		<input type="text" id="userName" name="userName" style="margin-bottom: 10px; margin-right: 20px; border:none; border-bottom: solid 1px;" required>
          		</td>
          	</tr>
          	<tr>
          		<td style="padding-bottom: 15px;">
          			<label for="memberEmail" >이메일 입력 : </label>
          		</td>
          		<td style="padding-right: 10px;">
          		<input type="email" id="memberEmail" name="memberEmail" style="margin-bottom: 10px; margin-right: 20px; border:none; border-bottom: solid 1px;" required>
          		</td>
          	</tr>
          	
          	<tr>
          		<td colspan="3" style="text-align: center;  ">
          			<button type='submit' id="searchId">찾기</button>
          		</td>
          	</tr>
          </table>
      </form>
  </div>
  <script>
  	
  </script>
</body>
</html>