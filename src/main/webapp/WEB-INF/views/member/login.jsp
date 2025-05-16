<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
   <div class="login-container">
      <div class="logo">
          <img src="your-logo.png" alt="헬스장 플랫폼 로고" />
      </div>
      <form action="/loginChk" method="POST" class="login-form">
          <!-- 회원 유형 선택 -->
          <div class="user-type">
              <label>
                  <input type="radio" name="userType" value="general" checked> 일반회원
              </label>
              <label>
                  <input type="radio" name="userType" value="gym"> 헬스장
              </label>
          </div>

          <!-- 아이디/비밀번호 입력 필드 -->
          <div class="input-group">
              <label for="userId">아이디</label>
              <input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요" required>
          </div>
          <div class="input-group">
              <label for="password">비밀번호</label>
              <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
          </div>

          <!-- 로그인 버튼 -->
          <button type="submit" class="login-btn">로그인</button>
      </form>
      <div class="links">
          <a href="javascript:void(0);" onclick="searchInfo('id')">아이디 찾기</a> | <a href="javascript:void(0);" onclick="searchInfo('pw')">비밀번호 찾기</a> | <a href="/member/joinFrm">회원가입</a> | <a href="#">헬스장 등록</a>
      </div>
      <script>
      	function searchInfo(gb){
      		
      		if(gb == 'id'){
      			location.href = "/member/searchInfo?gb=" + gb
      		}
      		
      		if(gb == 'pw'){
      			location.href = "/member/searchInfo?gb=" + gb
      		}
      		
      	}
      </script>
  </div>
</body>
</html>