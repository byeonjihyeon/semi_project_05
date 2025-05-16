<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 -->  
 <script src:https://code.jquery.com/jquery-3.7.1.min.js></script> <!-- jquery 구글 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<div class="login-container">
      
      <form action="/member/sendEmail" method="get" class="login-form">
          <table>
          	<tr style="text-align: center;">
          		<td colspan="3" style="padding-bottom: 30px">비밀번호 찾기</td>
          	</tr>
          	<tr>
          		<td style="padding-bottom: 15px; justify-content: center; text-align: center;">
          			<label for="userId" >아이디 입력 : </label>
          		</td>
          		<td style="padding-right: 10px; ">
          		<input type="text" id="userId" name="userId" style="margin-bottom: 10px; margin-right: 20px; border:none; border-bottom: solid 1px;" required>
          		</td>
          	</tr>
          	<tr>
          		<td style="padding-bottom: 15px;">
          			<label for="memberEmail" >이메일 입력 : </label>
          		</td>
          		<td style="padding-right: 10px;">
          		<input type="email" id="userEmail" name="userEmail" style="margin-bottom: 10px; margin-right: 20px; border:none; border-bottom: solid 1px;" required>
          		</td>
          		<td style="padding-bottom: 15px;">
          			<button type="submit" >인증번호 요청</button>
          		</td>
          	</tr>
          </table>
      </form>
  </div>
 
</body>
</html>