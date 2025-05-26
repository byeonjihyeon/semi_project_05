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
<title>마이페이지</title>
<style>
/* =================== Font =================== */
@import url('https://fonts.googleapis.com/css2?family=Orbit&display=swap');

/* =================== Global Styles =================== */
body {
    font-family: 'Orbit', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

/* =================== Layout =================== */
   .mypage-container {
    display: flex;
    padding: 40px;
    min-height: 700px;
    width: 1200px;
    margin: 0 auto;
    font-family: 'Segoe UI', sans-serif;

}

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

/* =================== Sidebar =================== */
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

/* =================== Main Content =================== */
.main-content h2 {
    font-size: 24px;
    margin-bottom: 30px;
    text-align: center;
    color: #333;
}

.member-form-table {
    width: 800px;
    border-spacing: 0 16px;
    margin: 0 auto; /* 가운데 정렬 */
}

th {
    text-align: left;
    width: 140px;
    color: #444;
    font-weight: bold;
    padding-left: 100px;
}

td {
    padding-bottom: 8px;
    width: 500px;
}

/* =================== Input & Button =================== */
input[type="text"],
input[type="button"] {
    width: 350px;
    padding: 10px 14px;
    font-size: 15px;
    border: 2px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
    transition: all 0.2s ease-in-out;
}

input[type="text"]:focus {
    border-color: #ff414d;
    outline: none;
    box-shadow: 0 0 5px rgba(255, 65, 77, 0.4);
}

input[readonly] {
    background-color: #f0f0f0;
    cursor: not-allowed;
}

button[type="submit"],
button[type="button"],
input[type="button"] {
    background-color: #ff414d;
    color: #fff;
    border: none;
    padding: 10px 20px;
    font-size: 15px;
    font-weight: bold;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-top: 10px;
}

button[type="submit"]:hover,
button[type="button"]:hover,
input[type="button"]:hover {
    background-color: #d6001c;
}

/* =================== Button Group =================== */
.form-buttons {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 30px;
}

/* =================== Message =================== */
#nameMsg,
#phoneMsg,
#emailMsg {
    color: #ff414d;
    font-size: 13px;
    margin-top: 4px;
}

/* =================== Responsive =================== */
@media (max-width: 768px) {
    .mypage-container {
        flex-direction: column;
        margin: 20px;
    }

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
        padding: 25px;
    }

    .member-form-table {
        width: 100%;
    }

    input[type="text"],
    input[type="button"] {
        width: 100%;
    }
}

	.withdraw-button {
    position: absolute;
    bottom: 30px;
    right: 30px;
}

.withdraw-button button {
    background-color: white;
}

.withdraw-button button:hover {
    background-color: white;
}
.main-content {
    position: relative; /* 위치 기준을 잡아줌 */
    flex: 1;
    padding: 50px;
    background-color: #fafafa;
}

button#deleteBtn {
    position: absolute;
    right: 1px; /* main-content의 오른쪽에서 50px 떨어진 위치 */
    bottom: 1px; /* main-content의 아래쪽에서 50px 떨어진 위치 */
    background-color: #ff414d;
    
    color: #fff;
    background:none;
    color: black;
    padding: 10px 20px;
    font-size: 13px;
    width: 100px;
    height: 41px;
    font-weight: bold;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s;
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
            <li><a href="/member/updateMemberFrm" style="color: red;">회원 정보 수정</a></li>
            <li><a href="/member/updatePwFrm">비밀번호 변경</a></li>
            <li><a href="/member/userHistoryList?reqPage=1">이용 내역 조회</a></li>
            <li><a href="/member/recordGrowth?reqPage=1">나의 몸무게 일지</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2 style="text-align: center;">회원 정보 수정</h2>
       		
       		<div>
       			<form action="/member/updateMember" method="post" style="margin: 0 auto;">
       			<input type="hidden" name="memberId" id="memberId" value="${sessionScope.loginMember.memberId}">
       				<table border="0" style="width: 800px;">
       				<tr>
       					<th><label for="userName">이름</label></th>
       					<td><input type="text" id="userName" name="userName" value="${sessionScope.loginMember.memberName}"></td>
       				</tr>
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="nameMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       					<th><label for="userPhone">전화번호</label></th>
       					<td><input type="text" id="userPhone" name="userPhone" value="${sessionScope.loginMember.memberPhone}" ></td>
       				</tr>
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="phoneMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       					<th><label for="userEmail">이메일</label></th>
       					<td><input type="text" id="userEmail" name="userEmail" value="${sessionScope.loginMember.memberEmail}" ></td>
       				</tr>
       				
       				<tr>
       				<th></th>
       				<td colspan="2"><p id="emailMsg" style="font-size: 13px;"></p></td>
       				</tr>
       				<tr>
       				 	<th>주소</th>
        				<td>
          					<input type="text" id="address" name="address" placeholder="주소" class="member-edit-input" readonly value="${sessionScope.loginMember.memberAddr}" style="width: 350px">
          					<input type="button" onclick="searchAddr()" value="주소 찾기" style="width:90px; font-size: 13px;">
          					<br>
          				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" class="member-edit-input" style="margin-top: 10px; width: 350px;" >
        				</td>
      				</tr>
       				</table>
       				<div class="form-buttons">
    					<button type="submit" id="validateForm">수정하기</button>
					</div>
       			</form>
       			<form action="/member/delete">
					<div class="withdraw-button">
    					<button type="submit" onclick="deleteMember()" style="font-weight: normal; color: blue;">회원탈퇴</button>
  					</div>
  				</form>
       		</div>
    </div>
</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script>

const checkObj = {	//회원수정하기에서도 회원가입과 마찬가지로 유효성 검사 진행 미리 변수 선언
		"userName" : true,
		"userPhone" : true,
		"userEmail" : true
};

const userName = $("#userName");
const nameMsg = $("#nameMsg");

$(userName).on('input',function(){
		checkObj.userName = false;
		
		const regExp = /^[가-힣]{2,5}$/;
		
		if(regExp.test($(this).val())){
			checkObj.userName = true;
			$(nameMsg).text('정상적으로 이름 입력!');
		}else{
			checkObj.userName = false;
			$(nameMsg).text('한글로 2~5글자로 입력해주세요.');
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
			$(phoneMsg).text('전화번호 양식에 맞게 입력해주세요.(010-0000-0000)');
			checkObj.userPhone = false;
		}
	});
	
  	const userEmail = $('#userEmail');
  	const emailMsg = $('#emailMsg');
  	
  	$(userEmail).on('input',function(){
  		checkObj.userEmail = false;
  		
  		$.ajax({
  			url : "/member/emailDuplChk",
  			data : {'userEmail' : $(userEmail).val()},
  			type : "get",
  			success : function(res){
  				if(res == 0){
  					console.log(res);
  					const regExp = /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[a-zA-Z]([-_.]?[0-9a-zA-Z])+(\.[a-z]{2,3})$/; 
  					
  					if(regExp.test($('#userEmail').val())){
  						checkObj.userEmail = true;
  						
  						$('#emailMsg').text('사용 가능한 이메일입니다.');
  					}else{
  			  			$(emailMsg).text('이메일 양식에 맞춰 작성해주세요.');
  			  			checkObj.userEmail = false;
  			  		}
  				}else{
  					console.log(res)
  					checkObj.userEmail = false;
  					
  					$(emailMsg).text('사용중인 이메일입니다.');
  				}
  			}
  		});
  		
  		
  		
  		
  	});
  	
	//주소 찾기(카카오API)
    function searchAddr() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                   
                } 

              
                document.getElementById("address").value = addr + " " + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
	
	$('#validateForm').on('click', function(){
		let str = '';
		
		for(let key in checkObj){
			if(!checkObj[key]){
				switch(key){
				case "userName" : str = "이름 형식이 유효하지 않습니다."; break;
				case "userPhone" : str = "전화번호 형식이 유효하지 않습니다."; break;
				case "userEmail" : str = "이메일 형식이 유효하지 않습니다."; break;
				}
				
				swal({
					title : '알림',
					text : str,
					icon : "warning"
				});
				return false;	// 버튼 제어
			}
				return;
		};
	});

</script>
</body>

</html>






