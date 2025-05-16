<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<meta charset="UTF-8">
	<title>헬스장 등록 페이지</title>
	<link rel="stylesheet" href="<c:url value='\resources\css\register.css'/>">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
      <main class="main-content">
      	<div class="register-container">
		    <h2>헬스장등록창</h2>
		    
		    <form action="/gym/register" method="post" enctype="multipart/form-data" autocomplete="off">
			    <div class="form-group">
			        <label for="gymId">ID *</label>
			        <div class = "input-wrap">
			        	<input type="text" id="gymId" name="gymId" required
			               placeholder="영어, 숫자 8~20자" maxlength="20"
			               pattern="^[a-zA-Z0-9]{8,20}$"
			               title="영어, 숫자로 8~20자 입력">
			            <button type="button" id = "idDuplChkBtn">중복확인</button>
			        </div>
			        
			        <p id="idMessage" class="input-msg"></p>
			        
			    </div>
			
			    <div class="form-group">
			        <label for="password">비밀번호 *</label>
			        <input type="password" id="password" name="password" required
			               placeholder="영문, 숫자, 특수문자(!,@,#,$) 6~30자"
			               maxlength="30" pattern="^[a-zA-Z0-9!@#$]{6,30}$"
			               title="영문, 숫자, 특수문자(!,@,#,$) 포함 6~30자 입력">
			    </div>
			
			    <div class="form-group">
			        <label for="passwordCheck">비밀번호 확인 *</label>
			        <input type="password" id="passwordCheck" name="passwordCheck" required
			               maxlength="30" placeholder="비밀번호 재입력">
			        <p id="pwMessage" class="input-msg"></p>
			    </div>
			
			    <div class="form-group">
			        <label for="gymName">헬스장명 *</label>
			        <input type="text" id="gymName" name="gymName" required
			               placeholder="헬스장 이름 (2~30자)" maxlength="30">
			        <p id="gymNameMessage" class="input-msg"></p>
			    </div>
			
			    <div class="form-group">
			        <label for="gymAddress">헬스장 주소 *</label>
			        <input type="text" id="gymAddress" name="gymAddress" required
			               placeholder="도로명 주소를 입력하세요.">
			    </div>
			
			    <div class="form-group">
			        <label for="email">이메일 *</label>
			        <input type="email" id="email" name="email" required
			               placeholder="example@example.com"
			               pattern="^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+\.[a-z]{2,3}$"
			               title="유효한 이메일 주소 입력">
			        <p id="emailMessage" class="input-msg"></p>
			    </div>
			
			    <div class="form-group">
			        <label for="phone">전화번호 *</label>
			        <input type="tel" id="phone" name="phone" required
			               placeholder="010-0000-0000" maxlength="13"
			               pattern="^010-\d{3,4}-\d{4}$"
			               title="010-0000-0000 형식으로 입력">
			        <p id="phoneMessage" class="input-msg"></p>
			    </div>
			
			    <div class="form-group">
			        <label for="document">필수서류</label>
			        <input type="file" id="document" name="document[]" multiple required>
			        <p id="documentMessage" class="input-msg"></p>
			    </div>
			
			    <div class="checkbox-area">
			        <p>모든 약관에 동의합니다.</p>
			        <ul>
			            <li><input type="checkbox" required> 개인정보 수집 이용 동의</li>
			            <li><input type="checkbox" required> 제3자 제공 동의</li>
			            <li><input type="checkbox"> 마케팅 정보 수신 동의</li>
			        </ul>
			    </div>
			
			    <div class="form-group">
			        <button type="submit">등록 / 수정하기</button>
			    </div>
			</form>

		</div>
      	
      </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
	
	<script>
	$(
			
	)
	const gymId = $('#gymId');
	const idMessage = $('#idMessage');
	const idDuplChkBtn = $('#idDuplChkBtn');

	const gymPw = $('#password');
	const pwMessage = $('#pwMessage');
	const gymPwRe = $('#passwordCheck');

	const gymName = $('#gymName');
	const gymNameMessage = $('#gymNameMessage');

	const gymEmail = $('#email');
	const emailMessage = $('#emailMessage');

	const gymPhone = $('#phone');
	const phoneMessage = $('#phoneMessage');

	const checkObj = {
	    "gymId": false,
	    "idDuplChk": false,
	    "password": false,
	    "passwordCheck": false,
	    "gymName": false,
	    "email": false,
	    "phone": false,
	    "termsAgreed": false
	};

	// ID 유효성 검사
	gymId.on('input', function() {
	    checkObj.idDuplChk = false;

	    idMessage.removeClass('valid invalid');

	    const regExp = /^[a-zA-Z0-9]{8,20}$/;

	    if (regExp.test($(this).val())) {
	        idMessage.text('');
	        idMessage.addClass('valid');
	        checkObj.gymId = true;
	    } else {
	        idMessage.text('영어, 숫자 8~20글자 사이로 입력하세요.');
	        idMessage.addClass('invalid');
	        checkObj.gymId = false;
	    }
	});

	// ID 중복 체크
	idDuplChkBtn.on('click', function() {
	    idMessage.removeClass('valid invalid');

	    if (!checkObj.gymId) {
	        alert("유효한 ID를 입력하고 중복확인을 진행하세요.");
	        return;
	    }

	    $.ajax({
	        url: "/gym/idDuplChk",
	        data: { 'gymId': gymId.val() },
	        type: "get",
	        success: function(res) {
	            if (res == 0) {
	                alert("사용 가능한 ID입니다.");
	                checkObj.idDuplChk = true;
	            } else {
	                alert("이미 사용 중인 ID입니다.");
	                checkObj.idDuplChk = false;
	            }
	        },
	        error: function() {
	            alert("중복 확인 중 오류 발생");
	        }
	    });
	});

	// 비밀번호 유효성 검사
	gymPw.on('input', function() {
	    pwMessage.removeClass('valid invalid');

	    const regExp = /^[a-zA-Z0-9!@#$]{6,30}$/;

	    if (regExp.test(gymPw.val())) {
	        pwMessage.text('');
	        pwMessage.addClass('valid');
	        checkObj.password = true;

	        if (gymPwRe.val().length > 0) checkPw();
	    } else {
	        pwMessage.text('비밀번호 형식이 유효하지 않습니다.');
	        pwMessage.addClass('invalid');
	        checkObj.password = false;
	    }
	});

	// 비밀번호 확인 유효성 검사
	gymPwRe.on('input', checkPw);

	function checkPw() {
	    pwMessage.removeClass('valid invalid');

	    if (gymPw.val() === gymPwRe.val()) {
	        pwMessage.text('');
	        pwMessage.addClass('valid');
	        checkObj.passwordCheck = true;
	    } else {
	        pwMessage.text('비밀번호가 일치하지 않습니다.');
	        pwMessage.addClass('invalid');
	        checkObj.passwordCheck = false;
	    }
	}

	// 헬스장명 유효성 검사
	gymName.on('input', function() {
	    gymNameMessage.removeClass('valid invalid');

	    const value = this.value.trim();
	    if (value.length >= 2 && value.length <= 30) {
	        gymNameMessage.text('');
	        gymNameMessage.addClass('valid');
	        checkObj.gymName = true;
	    } else {
	        gymNameMessage.text('2~30글자 이내로 작성해주세요.');
	        gymNameMessage.addClass('invalid');
	        checkObj.gymName = false;
	    }
	});

	// 이메일 유효성 검사
	gymEmail.on('input', function() {
	    emailMessage.removeClass('valid invalid');

	    const regExp = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+\.[a-z]{2,3}$/;

	    if (regExp.test(this.value)) {
	        emailMessage.text('');
	        emailMessage.addClass('valid');
	        checkObj.email = true;
	    } else {
	        emailMessage.text('이메일 형식이 올바르지 않습니다.');
	        emailMessage.addClass('invalid');
	        checkObj.email = false;
	    }
	});

	// 전화번호 유효성 검사
	gymPhone.on('input', function() {
	    phoneMessage.removeClass('valid invalid');

	    const regExp = /^010-\d{3,4}-\d{4}$/;

	    if (regExp.test(this.value)) {
	        phoneMessage.text('');
	        phoneMessage.addClass('valid');
	        checkObj.phone = true;
	    } else {
	        phoneMessage.text('전화번호 형식이 올바르지 않습니다.');
	        phoneMessage.addClass('invalid');
	        checkObj.phone = false;
	    }
	});

	// 약관 동의 체크
	document.querySelectorAll('.checkbox-area input[type="checkbox"]').forEach(cb => {
	    cb.addEventListener('change', () => {
	        const requiredTerms = document.querySelectorAll('.checkbox-area input[type="checkbox"]:required');
	        checkObj.termsAgreed = [...requiredTerms].every(cb => cb.checked);
	    });
	});

	// 최종 제출 검증
	document.querySelector('form').addEventListener('submit', function(e) {
	    for (const key in checkObj) {
	        if (!checkObj[key]) {
	            alert(`${key} 항목이 유효하지 않거나 확인되지 않았습니다.`);
	            e.preventDefault();
	            return false;
	        }
	    }
	});
	</script>


</body>
</html>