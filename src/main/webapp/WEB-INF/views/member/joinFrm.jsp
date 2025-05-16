<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
<!DOCTYPE html>

<style>
	p {
	font-size: 13px;
	}
	#mainContent {
	font-size : 25px;
	font : bold;
	}
</style>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	   <div class="login-container">
    
      <form action="/member/join" method="post" class="login-form" onsubmit='return validateForm()'>
          <!-- 회원 유형 선택 -->
          <div class="user-type">
              <p style="text-align: center;" id="mainContent">회원가입</p>
          </div>

          
          <div class="input-group">
              <label for="userId">아이디</label>
              <input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요" style="width:315px;" required>
              <button type="button" id="idDuplChkBtn" name="idDuplChkBtn" style="margin-left: 15px;">중복체크</button>
              <p id="idMsg" name="idMsg"></p>
          </div>
          <div class="input-group">
              <label for="userPw">비밀번호</label>
              <input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요" required>
              <p id="pwMsg" name="pwMsg">영어,숫자,특수문자 포함 6~30글자</p>
          </div>
          <div class="input-group">
              <label for="userPwRe">비밀번호확인</label>
              <input type="password" id="userPwRe" name="userPwRe" placeholder="비밀번호를 입력하세요"  required>
              <p id="pwReMsg" name="pwReMsg"></p>
          </div>
          <div class="input-group">
              <label for="userName">이름</label>
              <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요"  required>
              <p id="nameMsg" name="nameMsg"></p>
          </div>
          <div class="input-group">
              <label for="userEmail">이메일</label>
              <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" required>
              <p id="emailMsg" name="emailMsg"></p>
          </div>
          <div class="input-group">
              <label for="userPhone">전화번호</label>
              <input type="text" id="userPhone" name="userPhone" placeholder="전화번호를 입력하세요(010-0000-0000)" required>
              <p id="phoneMsg" name="phoneMsg" style="padding-bottom: 50px;"></p>
          </div>

          <!-- 로그인 버튼 -->
          <button type="submit" class="login-btn">회원가입</button>
      </form>
  </div>
        <script>
      	const checkObj = {
      			"userId" : false,
      			"idDuplChk" : false,
      			"userPwRE" : false,
      			"userName" : false,
      			"userEmail" : false,
      			"userPhone" : false,
      			"userPw" : false
      	};
      	
      	const userId = $('#userId');
      	const idDuplChkBtn = $('#idDuplChkBtn');
      	const idMsg = $('#idMsg');
      	
      	$(userId).on('input', function(){
      		checkObj.idDuplChk = false;
      		
      		const regExp = /^[a-zA-z0-9]{8,20}$/ 	//"영어, 숫자 8~20글자
      		
      		if(regExp.test($(this).val())){
      			$(idMsg).html('사용 가능한 ID 입니다.');
      			checkObj.userId = true;
      			
      		}else{
      			$(idMsg).text('영어와 숫자를 이용해 8~20글자로 작성해주세요.');
      			checkObj.uesrId = false;
      			
      		}
      		
      	});
      	
      	
      	
      	$(idDuplChkBtn).on('click',function(){
      		if(!checkObj.userId){
      			swal({
					title : "알림",
					text : "유효한 ID를 입력하고, 중복체크를 진행하세요.",
					icon : "warning"
				});			
      			return;
      		}
      	$.ajax({
      		url : "/idDuplChk",
      		data : {'userId' : $(userId).val()},
      		type : "get",
      		success : function(res){
      			if(res == 0){
      				console.log(res);
      				swal ({
      					title : "알림",
      					text : "사용 가능한 ID 입니다.",
      					icon : "success"
      				
      				});
      				
      				checkObj.idDuplChk = true;
      			}else{
      				swal ({
      					title : "알림",
      					text : "이미 사용중인 ID 입니다.",
      					icon : "error"
      				
      				});
      				checkObj.idDuplChk = false;
      			}
      		}
      	});
      	});
      	
      	const userPw = $('#userPw');
      	const userPwRe = $('#userPwRe');
      	const pwMsg = $('#pwMsg');
      	const pwReMsg = $('#pwReMsg');
      		
      	$(userPw).on('input',function(){
      		checkObj.userPw = false;
      		checkObj.userPwRe = false;
      		
      		const regExp = /^[a-zA-Z0-9!@#$]{6,30}$/; // 영어,숫자,특수문자 포함 6 ~ 30자.
      		
      		if(regExp.test($(this).val())){
      			$(pwMsg).text('사용 가능한 비밀번호 입니다.');
      			checkObj.userPw = true;
      			
      		}else{
      			$(pwMsg).text('비밀번호의 양식에 맞게 작성해주세요.');
      			checkObj.userPw = false;
      		}
      	});
      	
      	$(userPwRe).on('input',function(){
      		checkObj.userPwRe = false;
      		
      		if($(userPw).val() == $(userPwRe).val()){
      			$(pwReMsg).text('비밀번호가 일치합니다.');
      			checkObj.userPwRe = true;
      		}else{
      			$(pwReMsg).text('비밀번호가 서로 다릅니다. 다시 입력해주세요.');
      			checkObj.userPwRe = false;
      		}
      	});
      	
      	const userName = $('#userName');
      	const nameMsg = $('#nameMsg');
      	
      	$(userName).on('input',function(){
      		checkObj.userName = false;
      		
      		const regExp = /^[가-힣]{3,5}$/;
      		
      		if(regExp.test($(this).val())){
      			checkObj.userName = true;
      			$(nameMsg).text('정상적으로 이름 입력!');
      		}else{
      			checkObj.userName = false;
      			$(nameMsg).text('한글로 3~5글자로 입력해주세요.');
      		}
      	});
      	
      	
      	

      	
      	
      	const userEmail = $('#userEmail');
      	const emailMsg = $('#emailMsg');
      	
      	$(userEmail).on('input',function(){
      		checkObj.userEmail = false;
      		
      		const regExp = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+(\.[a-z]{2,3})$/;
      		
      		if(regExp.test($(this).val())){
      			$(emailMsg).text('사용가능한 Email 입니다.');
      			checkObj.userEmail = true;
      		}else{
      			$(emailMsg).text('이메일 양식에 맞춰 작성해주세요.');
      			checkObj.userEmail = false;
      		}
      	});
      	
      	const userPhone = $('#userPhone');
      	const phoneMsg = $('#phoneMsg');
      	
      	$(userPhone).on('input',function(){
      		checkObj.userPhone = false;
      		
      		const regExp = /^010-\d{3,4}-\d{4}$/;
      		
      		if(regExp.test($(this).val())){
      			$(phoneMsg).text('전화번호 정상 입력 !');
      			checkObj.userPhone = true;
      		}else{
      			$(phoneMsg).text('전화번호 양식에 맞게 입력해주세요.');
      			checkObj.userPhone = false;
      		}
      	});
      	
      	function validateForm(){
      		let str = '';
      		
      		for(let key in checkObj){
      			if(!checkObj[key]){
      				switch(key){
      				case "userId" : str = "아이디 형식이 유효하지 않습니다."; 
      							break;
      				case "userPw" : str = "비밀번호 형식이 유효하지 않습니다."; 
      							break;
      				case "userPwRe" : str = "비밀번호 확인 형식이 유효하지 않습니다."; 
      							break;
      				case "userName" : str = "이름 형식이 유효하지 않습니다."; 
      							break;
      				case "userEmail" : str = "이메일 형식이 유효하지 않습니다."; 
      							break;
      				case "userPhone" : str = "전화번호 형식이 유효하지 않습니다"; 
      							break;
      				case "idDuplChk" : str = "아이디 중복체크를 진행해주세요."; 
      							break;
      				}
      				
      				swal({
      					title : "알림",
      					text : str,
      					icon : "warning"
      				});
      				
      				return false;
      			};
      			return;
      		};
      	};
      </script>
</body>
