<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!--카카오 주소 찾기 -->  
<link rel="stylesheet" href="/resources/css/default.css"> <!-- CSS 파일 연결 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>

</head>
<body>
<div class="member-edit-container">
  <h2>회원 정보 수정</h2>
  <form id="member-edit-form" action="/admin/member/update">
    <input type="hidden" name="memberId" value="${updMember.memberId}">
    <table class="member-edit-table">
      <tr>
        <th>회원 아이디</th>
        <td>${updMember.memberId}</td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="name" class="member-edit-input" value="${updMember.memberName}"></td>
      </tr>
      <tr>
        <th>전화번호</th>
        <td><input type="text" name="phone" class="member-edit-input" value="${updMember.memberPhone}"></td>
      </tr>
      <tr>
        <th>이메일</th>
        <td><input type="email" name="email" class="member-edit-input" value="${updMember.memberEmail}"></td>
      </tr>
      <tr>
        <th>주소</th>
        <td>
          <input type="text" id="address" name="address" placeholder="주소" class="member-edit-input" readonly value="${updMember.memberAddr}">
          <input type="button" onclick="searchAddr()" value="주소 찾기" style="margin-top: 8px; height: 30;">
          <br>
          <input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소" class="member-edit-input" style="margin-top: 10px;">
        </td>
      </tr>
      <tr>
        <th>등급</th>
        <td>
          <select name="grade" class="member-edit-select">
            <option value="standard">standard</option>
            <option value="premium">premium</option>
            <option value="vip">vip</option>
          </select>
        </td>
      </tr>
    </table>
    <div class="member-edit-btn-group">
      <button type="submit" class="member-edit-btn" onclick="updateMember()">저장</button>
      <button type="button" class="member-edit-btn" onclick="closePop()">취소</button>
    </div>
  </form>
</div>
</body>
<script>
	function closePop(){
		self.close();
	}
	
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
</script>
</html>