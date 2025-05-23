<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				  <h2>비밀번호 변경</h2>
				  <form action="/admin/chgPw"
				  		method='post'
				  		autocomplete='off'
				  		onsubmit='return validateForm()'>
				 	 <table class="member-info-table">
					    <tr>
					    	<th>현재 비밀번호</th>
					    	<td>
					    	<input id='adminPw' type='password' name='adminPw'>
					    	</td>
				    	</tr>
					    <tr>
						    <th>새 비밀번호</th>
						    <td><input id='newAdminPw' type='password' name='newAdminPw'> <br>
						    <span id='pwMsg'></span>
						    </td>
					    </tr>
					     <tr>
						    <th>비밀번호 확인</th>
						    <td><input id='newAdminPwRe' type='password' name='newAdminPwRe'> <br>
						    <span id='pwReMsg'></span>
						    </td>
					    </tr>
					  
				  </table>
				  <div class="member-info-button-wrapper">
					    <button type='submit' class="member-info-btn">변경</button>
				  </div>
				</form>
				</div>  	
		   		</main>
		</div>  
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>  
	<script>
	function validateForm(){
	
		const regExp = /^[a-zA-Z0-9!@#$]{6,30}$/;
		
		//새 비밀번호가 정규표현식 패턴에 만족하는지 확인.
		if(!regExp.test($('#newAdminPw').val())){
			swal({
				title : "알림",
				text : "새 비밀번호가 유효하지 않습니다.",
				icon : "warning"
			});	
			
			return false;
		}
		
		//새 비밀번호와 새 비밀번호 확인값이 같은지?
		if($('#newAdminPw').val() != $('#newAdminPwRe').val()){
			swal({
				title : "알림",
				text : "새비밀번호와 새비밀번호확인값이 일치하지 않습니다.",
				icon : "warning"
			});
			
			return false;
		}
	}
	
	</script>
</body>
</html>