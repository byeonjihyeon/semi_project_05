<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 등록</title>
<style>
.member-info-card{
	width: 600px;
}
.valid {
  font-size : 10px;
  color: #333;
}
.invalid {
  font-size : 10px;
  color: #333;
}

 input[type="text"], input[type="password"], input[type="email"], input[type="tel"] {
    border: none;
    border-bottom: 1px solid #ccc;
    padding: 6px 8px;
    font-size: 14px;
    width: 240px;
    outline: none;
    background-color: transparent;
    transition: border-color 0.2s ease;
  }

  input[type="text"]:focus,
  input[type="password"]:focus,
  input[type="email"]:focus,
  input[type="tel"]:focus {
    border-bottom: 1px solid #CE2029;
  }

  button {
    background-color: #CE2029;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 6px 14px;
    font-size: 13px;
    cursor: pointer;
    transition: background-color 0.2s ease;
  }

  button:hover {
    background-color: #a91b22;
  }
  
   #idDuplChkBtn {
    background-color: #e0e0e0;
    color: #333;
    margin-left: 10px;
  }
  
  
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  	<div class="container">
			<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
				<main class="content">
		      		 <div class="member-info-card">
				  <h2>관리자 등록</h2>
				  <form action="/admin/super/createAdmin"
				  		method='post'
				  		autocomplete='off'
				  		onsubmit='return validateForm()'>
				 	 <table class="member-info-table">
					    <tr>
					    	<th>아이디</th>
					    	<td>
					    	<input id='adminId' type='text' name='adminId'><button type='button' id='idDuplChkBtn'>중복 확인</button> <br>
					    	<span id='idMsg'></span>
					    	</td>
				    	</tr>
				    	<tr>
				    		
				    	</tr>
					    <tr>
						    <th>이름</th>
						    <td><input id='adminName' type='text' name='adminName'> <br>
						    <span id='nameMsg'></span>
						    </td>
					    </tr>
					     <tr>
						    <th>이메일</th>
						    <td><input id='email' type='text' name='email'> <br>
						    <span id='emailMsg'></span>
						    </td>
					    </tr>
					  
				  </table>
				  <div class="member-info-button-wrapper">
					    <button type='submit' class="member-info-btn">등록</button>
				  </div>
				</form>
				</div>  	
		   		</main>
		</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>  
	<script>
	
	//사용자 입력값 검증 결과를 저장할 객체
	//사용자 값을 입력할 때마다 검증하여, 만족할 경우 true로, 만족하지 못할 경우 false
	//최종 회원가입 버튼 클릭시, 아래 객체의 모든 속성인지 true인지 검사할 것.
	const checkObj = {
			"adminId" : false,
			"adminName" : false,
			"adminEmail" : false,
			"idDuplChk" : false //아이디 중복체크 여부(true면 중복x, false면 중복o 또는 중복체크 미진행)
			
	};
	
	const adminId = $('#adminId');
	const idMsg = $('#idMsg'); //입력 id유효성 검증 겨로가 메시지를 보내줄 요소
	
	$(adminId).on('input', function(){
		//아이디 입력시마다, 중복체크값 false로 변경하여 다시 중복체크 할 수 있도록	
		checkObj.idDuplChk = false;
		
		//아래 코드 작성하지 않으면, 클래스를 추가해주기만 하기 때문에, 두 클래스가 공존할수 없음.
		$(idMsg).removeClass('valid');
		$(idMsg).removeClass('invalid');
		
		//아이디 정규표현식 앞에는 mj_를 포함하며, 뒤에는 영어또는숫자 3~15자
		const regExp = /^mj_[a-zA-Z0-9]{3,15}$/;
		
		//사용자가 입력한 Id가 정규표현식에 만족하는 경우
		if(regExp.test($(this).val())){
			$(idMsg).text('가입 가능한 아이디입니다.');
			checkObj.adminId = true;
			$(idMsg).addClass('valid');
		//사용자가 입력한 ID가 정규표현식에 만족하지 않는 경우
		}else{
			$(idMsg).text('mj_로 시작하고, 영문자+숫자 조합 3~15자를 입력하세요.');
			checkObj.adminId = false;
			$(idMsg).addClass('invalid');
		}
	
	});
	
	//아이디 중복 체크
	
	//중복체크 버튼 요소
	const idDuplChkBtn = $('#idDuplChkBtn');
	
	$(idDuplChkBtn).on('click', function(){
		//$(idMsg).removeClass('valid');
		//$(idMsg).removeClass('invalid');
		
		//아이디 유효성 검증 결과가 false일 때,
		if(!checkObj.adminId){
			swal({
				icon : "warning",
				text : "유효한 id를 입력하고, 중복체크를 진행하여주세요.",
			});
			return;
		}
		
		//중복체크 코드
		$.ajax({
			url : "/admin/create/idDuplChk",
			data : {'adminId' : $(adminId).val()},
			type : "get",
			success : function(res){
				if(res == null){
					swal({
						icon : "success",
						text : "사용 가능한 ID입니다."
					});
					
					checkObj.idDuplChk = true;
					
				}else{
					swal({
						icon : "warning",
						text : "이미 사용중인 ID 입니다."
					});
					
					checkObj.idDuplChk = false;
				}
			},
			
			error : function(){
				console.log('ajax오류');
			}
		});
	});
	
	
	//이름 유효성 검사
	const adminName = $('#adminName');
	const nameMsg = $('#nameMsg');
	
	$(adminName).on('input', function(){
		$(nameMsg).removeClass('invalid');
		$(nameMsg).removeClass('valid');
		
		//이름은 한글로 적어도 2~10글자
		const regExp = /^[가-힣]{2,10}$/;
		
		if(regExp.test($(this).val())){
			$(nameMsg).text('');
			$(nameMsg).addClass('valid');
			checkObj.adminName = true;
		}else{
			$(nameMsg).text('이름은 최소 2~10글자 입니다.');
			$(nameMsg).addClass('invalid');
			checkObj.adminName = false;
		}
	});
	
	//이메일 유효성 검사
	const adminEmail = $('#email');
	const emailMsg = $('#emailMsg');
	
	$(email).on('input', function(){
		$(emailMsg).removeClass('invalid');
		$(emailMsg).removeClass('valid');
		
		const regExp = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+(\.[a-z]{2,3})$/;
		
		if(regExp.test($(this).val())){
			/*
			$(emailMsg).text('');
			$(emailMsg).addClass('valid');
			*/
			$.ajax({
				url : "/member/emailDuplChk",
				data : {"userEmail" : $(adminEmail).val()},
				type : "get",
				success : function(res){
					//중복이메일이 있을 경우
					if(res != 0){
						console.log(res);
						$(emailMsg).text('중복된 이메일이 존재합니다.');
						$(emailMsg).addClass('invalid');
						checkObj.adminEmail = false;
					//없을 경우	
					}else{
						console.log(res);
						$(emailMsg).text('');
						$(emailMsg).addClass('valid');
						checkObj.adminEmail = true;
					}
				},
				error : function(){
					console.log('ajax 오류');
				}
			});
			
			
		}else{
			$(emailMsg).text('이메일 형식이 올바르지 않습니다.');
			$(emailMsg).addClass('invalid');
			checkObj.adminEmail = false;
		}
		
		
	});
	
	//등록 버튼 클릭시, checkObj의 모든 속성에 접근하여 true인지 검증
	function validateForm(){
		
		let str = "";
		
		for(let key in checkObj){
			console.log(checkObj);
			if(!checkObj[key]){
				switch(key){
					case "adminId" : str ="아이디 형식"; break;
					case "adminName" : str ="이름 형식"; break;
					case "adminEmail" : str ="이메일 형식"; break;
					case "idDuplChk" : str ="아이디 중복체크를 진행하세요."; break;
				}
				
				if(key != 'idDuplChk'){
					str += "이 유효하지 않습니다.";
				}
			
				swal({
				title : '관리자 등록 실패',
				icon : 'error',
				text : str
				});
			
			return false;
			}
		}
	}
	
	
	</script>
</body>
</html>