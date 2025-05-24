<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Orbit&display=swap');
body {
    font-family: 'Orbit', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
/* 전체 컨테이너 */
.mypage-container {
    display: flex;
    padding: 40px;
    min-height: 700px;
    width: 1200px;
    margin: 0 auto;
    font-family: 'Segoe UI', sans-serif;
}

/* 사이드바 스타일 */
.sidebar {
  
  
  border-top-left-radius: 12px;
  border-bottom-left-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.06);
  
  border-right: 1px solid #eee;
  
    width: 240px;
    background-color: #1a1a1a;
    color: #fff;
    padding: 30px 20px;

}
.main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;       /* 가로 가운데 */
    justify-content: center;   /* 세로 가운데 */
    padding: 50px;
    background-color: #fafafa;
}

.sidebar h3 {
  font-size: 18px;
  margin-bottom: 20px;
  color: #333;
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 14px;
}

.sidebar ul li a {
  text-decoration: none;
  color: #333;
  font-weight: 500;
}

.sidebar ul li a:hover {
  color: #ff414d;
  font-weight: bold;
}



/* 메인 콘텐츠 영역 */
.main-content {
  flex: 1;
  background-color: #fff;
  padding: 40px;
  border-top-right-radius: 12px;
  border-bottom-right-radius: 12px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.06);
  border-left: none;
}

/* 나머지 스타일 (이전과 동일) */
.main-content h2 {
  text-align: center;
  margin-bottom: 40px;
  font-size: 22px;
  color: #222;
}

table {
  width: 100%;
  border-spacing: 0 18px;
}

th {
  text-align: left;
  width: 180px;
  font-size: 15px;
  color: #444;
  vertical-align: top;
  padding-top: 8px;
}

td input[type="password"] {
  width: 100%;
  max-width: 400px;
  padding: 12px 16px;
  font-size: 15px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #fdfdfd;
  transition: border-color 0.3s ease;
}

td input[type="password"]:focus {
  border-color: #ff414d;
  outline: none;
  box-shadow: 0 0 6px rgba(255, 65, 77, 0.2);
}

#pwMsg, #chgPwMsg, #chgPwChkMsg {
  font-size: 13px;
  color: #ff414d;
  margin-top: 6px;
}

button[type="submit"] {
  margin-top: 30px;
  background-color: #ff414d;
  color: #fff;
  border: none;
  padding: 12px 32px;
  font-size: 16px;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button[type="submit"]:hover {
  background-color: #d6001c;
}
</style>


</head>


<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
<main>
	<div class="mypage-container">
    <!-- 사이드바 -->
    <div class="sidebar">
        <h3><a href=/member/myPageFrm>마이페이지</a></h3>
        <ul>
            <li><a href="/member/updateMemberFrm">회원 정보 수정</a></li>
            <li><a href="/member/updatePwFrm"  style="color: red;">비밀번호 변경</a></li>
            <li><a href="/member/userHistoryList">이용 내역 조회</a></li>
            <li><a href="/member/recordGrowth?reqPage=1">나의 몸무게 일지</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2 style="text-align: center;">비밀번호 변경</h2>
       		
       		<div>
       			<form action="/member/updatePw" method="post">
       			<input type="hidden" name="memberId" id="memberId" value="${sessionScope.loginMember.memberId}">
       				<table border="0" style="width: 800px;">
       				<tr>
       					<th><label for="userPw">현재 비밀번호</label></th>
       					<td><input type="password" id='userPw' name='userPw'></td>
       				</tr>
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="pwMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       					<th><label for="chgPw">변경할 비밀번호</label></th>
       					<td><input type="password" id='chgPw' name='chgPw' ></td>
       				</tr>
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="chgPwMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       					<th><label for="chgPwChk">비밀번호 확인</label></th>
       					<td><input type="password" id="chgPwChk" name="chgPwChk" ></td>
       				</tr>
       				
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="chgPwChkMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       					<td colspan="2" style="text-align: center;">
       					<button type="submit" id="validateForm">수정하기</button>
       					</td>
       				</tr>
       				</table>
       			</form>
       		</div>
    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
	const check = {
			"userPw" : false,
			"chgPw" : false,
			"chgPwChk" : false
	};
	
	
	$(userPw).on('input',function(){
		check.userPw = false;
		
		$.ajax({
			url : "/member/pwDuplChk",
			data : {'userPw' : $(userPw).val(),
					'userId' : "${sessionScope.loginMember.memberId}"
			},
			type : "post",
			success : function(res){
				if(res == "true"){
					check.userPw = true;
					$('#pwMsg').text('현재 비밀번호와 일치합니다.');
				}else{
					check.userPw = false;
					$('#pwMsg').text('현재 비밀번호와 일치하지 않습니다.');
				}
				
			}
		
			
		});
		
	});

	$('#chgPw').on('input',function(){
		check.chgPw = false;
		check.chgPwChk = false;
		
		const regExp = /^[a-zA-Z0-9!@#$]{6,30}$/; // 영어,숫자,특수문자 포함 6 ~ 30자.
		
		if($('#chgPw').val().length > 0 ){
			if(regExp.test($('#chgPw').val())){
				check.chgPw = true;
				$('#chgPwMsg').text('사용 가능한 비밀번호 입니다.');
			}else{
				check.chgPw = false;
				$('#chgPwMsg').text('비밀번호 양식에 맞게 작성해주세요.');
			}
		}else{
			check.chgPw = false;
			$('#chgPwMsg').text('영어, 숫자, 특수문자를 이용해서 6 ~ 30자 이내로 작성해주세요.');
		}
	});
	
	$('#chgPwChk').on('input',function(){
		check.chgPwChk = false;
		
		if($('#chgPwChk').val().length > 0){
			if($('#chgPwChk').val() == $('#chgPw').val()){
			check.chgPwChk = true;
			$('#chgPwChkMsg').text('비밀번호가 일치합니다.');
	}else{
		check.chgPwChk = false;
		$('#chgPwChkMsg').text('두 비밀번호가 서로 일치하지 않습니다.');
	}
		}else{
			check.chgPwChk = false;
			$('#chgPwChkMsg').text('변경할 비밀번호를 한번 더 입력하세요.');
		}
	});
	
	$('#validateForm').on('click',function(){
		let str = '';
		
		for(let key in check){
			if(!check[key]){
				switch(key){
				case "userPw" : str = "현재 비밀번호가 유효하지 않습니다."; break;
				case "chgPw" : str = "변경할 비밀번호가 유효하지 않습니다."; break;
				case "chgPwChk" : str = "비밀번호 확인이 유효하지 않습니다."; break;
				}
				
				swal({
					title : "알림",
					text : str,
					icon : "warning"
				});
				
				return false;
			}
			 
		}
		
	});

</script>
</body>

</html>






