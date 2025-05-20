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
  <link rel="icon" type="image/x-icon" href="/favicon.ico">
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	   <div class="login-container">
    
      <form action="/member/join" method="post" class="login-form">
          <!-- 회원 유형 선택 -->
          <div class="user-type">
              <p style="text-align: center;" id="mainContent">회원가입</p>
          </div>

          
          <div class="input-group">
              <label for="userId">아이디</label>
              <input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요" style="width:315px;" >
              <button type="button" id="idDuplChkBtn" name="idDuplChkBtn" style="margin-left: 15px;">중복체크</button>
              <p id="idMsg" name="idMsg">영어와 숫자를 이용해 8~20글자로 작성해주세요.</p>
          </div>
          <div class="input-group">
              <label for="userPw">비밀번호</label>
              <input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요" >
              <p id="pwMsg" name="pwMsg">영어,숫자,특수문자 포함 6~30글자</p>
          </div>
          <div class="input-group">
              <label for="userPwRe">비밀번호확인</label>
              <input type="password" id="userPwRe" name="userPwRe" placeholder="비밀번호를 입력하세요"  >
              <p id="pwReMsg" name="pwReMsg">비밀번호를 한번 더 입력해주세요.</p>
          </div>
          <div class="input-group">
              <label for="userName">이름</label>
              <input type="text" id="userName" name="userName" placeholder="이름을 입력하세요"  >
              <p id="nameMsg" name="nameMsg"></p>
          </div>
          <div class="input-group">
              <label for="userEmail">이메일</label>
              <input type="email" id="userEmail" name="userEmail" placeholder="이메일을 입력하세요" >
              <p id="emailMsg" name="emailMsg"></p>
          </div>
          <div class="input-group">
              <label for="userPhone">전화번호</label>
              <input type="text" id="userPhone" name="userPhone" placeholder="전화번호를 입력하세요(010-0000-0000)" >
              <p id="phoneMsg" name="phoneMsg" style="padding-bottom: 50px;">전화번호 양식에 맞게 입력해주세요.(010-0000-0000)</p>
          </div>

          <div class="input-group">      
          <button type="submit" class="login-btn" id="validateForm">회원가입</button>
          </div>
      </form>
  </div>
  
        <script>
      	const checkObj = {
      			"userId" : false,
      			"idDuplChk" : false,
      			"userPw" : false,
      			"userPwRe" : false,
      			"userName" : false,
      			"userEmail" : false,
      			"userPhone" : false
      	};
      	
      	const userId = $('#userId');
      	const idDuplChkBtn = $('#idDuplChkBtn');
      	const idMsg = $('#idMsg');
      	
      	$(userId).on('input', function(){
      		checkObj.idDuplChk = false;
      		
      		const regExp = /^[a-zA-z0-9]{8,20}$/ 	//"영어, 숫자 8~20글자
      		if($(userId).val().length > 0){      			
      		if(regExp.test($(userId).val())){
      			$(idMsg).text('사용 가능한 아이디 입니다.');
      			checkObj.userId = true;
      			
      		}else{
      			$(idMsg).text('영어와 숫자를 이용해 8~20글자로 작성해주세요.');
      			checkObj.userId = false;
      		}
      			
      		}else{
      			checkObj.userId = false;
      			$(idMsg).text('영어와 숫자를 이용해 8~20글자로 작성해주세요.');
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
      		if($(userPw).val().length > 0){      			
      		if(regExp.test($(userPw).val())){
      			$(pwMsg).text('사용 가능한 비밀번호 입니다.');
      			checkObj.userPw = true;
      			
      		}else{
      			$(pwMsg).text('비밀번호의 양식에 맞게 작성해주세요.');
      			checkObj.userPw = false;
      		}
      		}else{
      			checkObj.userPw = false;
      			$(pwMsg).text('비밀번호의 양식에 맞게 작성해주세요.')
      		}
      	});
      	
      	$(userPwRe).on('input',function(){
      		checkObj.userPwRe = false;
      		
      		if($(userPwRe).val().length > 0){      			
      		if($(userPw).val() == $(userPwRe).val()){
      			$(pwReMsg).text('비밀번호가 일치합니다.');
      			checkObj.userPwRe = true;
      		}else{
      			$(pwReMsg).text('비밀번호가 서로 다릅니다. 다시 입력해주세요.');
      			checkObj.userPwRe = false;
      		}
      		}else{
      			checkObj.userPwRe = false;
      			$(pwReMsg).text('비밀번호를 한번 더 입력해주세요.');
      		}
      	});
      	
      	const userName = $('#userName');
      	const nameMsg = $('#nameMsg');
      	
      	$(userName).on('input',function(){
      		checkObj.userName = false;
      		
      		const regExp = /^[가-힣]{2,5}$/;
      		
      		if($(userName).val().length > 0){      			
      		if(regExp.test($(userName).val())){
      			checkObj.userName = true;
      			$(nameMsg).text('정상적으로 이름 입력!');
      		}else{
      			checkObj.userName = false;
      			$(nameMsg).text('한글로 2~5글자로 입력해주세요.');
      		}
      		}else{
      			checkObj.userName = false;
      			$(nameMsg).text('한글로 2~5글자로 입력해주세요.')
      		}
      	});
      	
      	
      	

      	
      	
      	const userEmail = $('#userEmail');
      	const emailMsg = $('#emailMsg');
      	
      	$(userEmail).on('input',function(){
      		checkObj.userEmail = false;
      		
      		$.ajax({
      			url : "/member/emailDuplChk",
      			data : {"userEmail" : $(userEmail).val()},
      			type : 'get',
      			success : function(res){
      				if(res == 0){
      					const regExp = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+(\.[a-z]{2,3})$/;
      			if($(userEmail).val().length > 0){      				
      			if(regExp.test($(userEmail).val())){
      			$(emailMsg).text('사용가능한 이메일 입니다.');
      			checkObj.userEmail = true;
      		}else{
      			$(emailMsg).text('이메일 양식에 맞춰 작성해주세요.');
      			checkObj.userEmail = false;
      		}
      			}else{
      				checkObj.userEmail = false;
      				$(emailMsg).text('');
      			}
      	}else{
      		checkObj.userEmail = false;
      		$(emailMsg).text('사용중인 이메일입니다.')
      	}
     }
   });
      		
});
      	
      	const userPhone = $('#userPhone');
      	const phoneMsg = $('#phoneMsg');
      	
      	$(userPhone).on('input',function(){
      		checkObj.userPhone = false;
      		
      		const regExp = /^010-\d{3,4}-\d{4}$/;
      		
      		if($(userPhone).val().length > 0){      			
      		if(regExp.test($(userPhone).val())){
      			$(phoneMsg).text('전화번호 정상 입력 !');
      			checkObj.userPhone = true;
      		}else{
      			$(phoneMsg).text('전화번호 양식에 맞게 입력해주세요.(010-0000-0000)');
      			checkObj.userPhone = false;
      		}
      		}else{
      			checkObj.userPhone = false;
      			$(phoneMsg).text('전화번호 양식에 맞게 입력해주세요.(010-0000-0000)')
      		}
      	});
      	
      	$('.login-form').on('submit', function(e) {
      	    e.preventDefault(); // 기본 제출 막기

      	    let str = '';
      	    
      	    for(let key in checkObj){
      	        if(!checkObj[key]){
      	            switch(key){
      	                case "userId" : str = "아이디 형식이 유효하지 않습니다."; break;
      	                case "idDuplChk" : str = "아이디 중복확인이 되지 않았습니다"; break;
      	                case "userPw" : str = "비밀번호 형식이 유효하지 않습니다."; break;
      	                case "userPwRe" : str = "비밀번호 확인이 유효하지 않습니다."; break;
      	                case "userName" : str = "이름 형식이 유효하지 않습니다."; break;
      	                case "userEmail" : str = "이메일 형식이 유효하지 않습니다."; break;
      	                case "userPhone" : str = "전화번호 형식이 유효하지 않습니다."; break;
      	            }

      	            swal({
      	                title : "회원가입 실패",
      	                text : str,
      	                icon : "warning"
      	            });
      	            
      	            return false; // 한 가지라도 false이면 제출 막음
      	        }
      	    }

      	    // 모든 조건 만족 시에만 submit
      	    this.submit();  
      	});

</script>
</body>

</html>
