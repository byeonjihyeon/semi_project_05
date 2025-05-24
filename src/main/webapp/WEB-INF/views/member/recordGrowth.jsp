<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new java.util.Date());
    request.setAttribute("today", today);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Orbit&display=swap');

/* Body and Global Styles */
body {
    font-family: 'Orbit', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    color: #000;
}

/* Container for the page */
.mypage-container {
    display: flex;
    padding: 40px;
    min-height: 700px;
    width: 1200px;
    margin: 0 auto;
    font-family: 'Segoe UI', sans-serif;
}

/* Sidebar Styles */
.sidebar {
    border-top-left-radius: 12px;
    border-bottom-left-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
    border-right: 1px solid #eee;
    width: 240px;
    background-color: #1a1a1a;
    color: #fff;
    padding: 30px 20px;
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

/* Main Content Styles */
.main-content {
    flex: 1;
    background-color: #fff;
    padding: 40px;
    border-top-right-radius: 12px;
    border-bottom-right-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
}

.main-content h2 {
    text-align: center;
    margin-bottom: 40px;
    font-size: 22px;
    color: #222;
}

/* Welcome Box */
.welcome-box {
    display: flex;
    align-items: center;
    margin-bottom: 30px;
    padding: 20px;
    border: 1px solid #eee;
    border-radius: 12px;
    background-color: #fafafa;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.welcome-box img {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    margin-right: 20px;
}

.welcome-box p {
    margin: 6px 0;
    font-size: 15px;
    color: #333;
}

/* Gym Photo Styles */
.gym-photo {
    width: 700px;
    max-width: 900px;
    height: 800px;
    border: 1px solid #ccc;
    border-radius: 12px;
    background-color: #f9f9f9;
    color: #999;
    font-size: 16px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
    padding: 20px;
}

/* Table Styles */
.recordList {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    background-color: #fff;
    border-radius: 10px;
    overflow: hidden;
}

.recordList th,
.recordList td {
    padding: 12px 15px;
    text-align: center;
    border-bottom: 1px solid #eee;
    font-size: 14px;
}

.recordList th {
    background-color: olive;
    color: #fff;
    font-weight: bold;
}

.recordList td {
    background-color: #fafafa;
}

/* Change the red background in table cells to gray */
.recordList tr:hover td {
    background-color: #f1f1f1;
}

/* Pagination Styles */
#pagination {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 20px;
}

#pagination .page-btn {
    display: inline-block;
    margin: 0 5px;
    padding: 8px 16px;
    border: 1px solid #ddd;
    background-color: #fff;
    cursor: pointer;
    border-radius: 5px;
    font-size: 14px;
    color: #000; /* Change pagination font color to black */
}

#pagination .page-btn:hover {
    background-color: #f0f0f0;
}

#pagination .page-btn.active {
    font-weight: bold;
    background-color: #ff414d;
    color: #fff;
    border: none;
}

/* Buttons */
button {
    padding: 8px 16px;
    border-radius: 5px;
    background-color: #ff414d;
    color: white;
    font-size: 14px;
    border: none;
    cursor: pointer;
    margin-top: 20px;
}

button:hover {
    background-color: #e33d48;
}

/* Show List Button */
#showListBtn {
    margin-top: 20px;
    display: inline-block;
    font-weight: bold;
}

/* Table Visibility */
#recordTable {
    display: none;
}

/* Responsive Gym Photo */
.gym-photo {
    width: 700px;
    max-width: 700px;
    height: 400px;
    border: 1px solid #ccc;
    border-radius: 12px;
    background-color: #f9f9f9;
    color: #999;
    font-size: 16px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
}
</style>
</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
<main>

	<div class="mypage-container">
    <!-- 사이드바 -->
    <div class="sidebar">
        <h3>마이페이지</h3>
        <ul>
            <li><a href="/member/updateMemberFrm">회원 정보 수정</a></li>
            <li><a href="/member/updatePwFrm">비밀번호 변경</a></li>
            <li><a href="/member/userHistoryList?reqPage=1">이용 내역 조회</a></li>
            <li><a href="/member/recordGrowth" style="color: red;">나의 몸무게 일지</a></li>
        </ul>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <h2>나의 몸무게 일지</h2>
        <form action="/member/insertGrowth" method="post">
        <div class="welcome-box">
            <div>
                <p style="margin-bottom: 25px;"><strong>${sessionScope.loginMember.memberName}</strong>님 오늘의 운동결과를 기록해주세요!</p>
                <input type="hidden" name="memberId" id="memberId" value="${sessionScope.loginMember.memberId}">
                <label for="memberTall" style="margin-left: 50px;">키 : </label>
           		<input type="text" id="memberTall" name="memberTall" style="margin-right: 15px; width: 70px; border:none; border-bottom: 1px solid;">
           		
           		<label for="memberWeight">몸무게 :</label>
           		<input type="text" id="memberWeight" name="memberWeight" style="margin-right: 15px; width: 70px; border:none; border-bottom: 1px solid;">
           		
           		<label for="hopeWeight">목표몸무게 : </label>
            	<input type="text" id="hopeWeight" name="hopeWeight" style="width: 70px; margin-right: 15px; border:none; border-bottom: 1px solid;">
            	
				<label for="sysdate">운동한 오늘의 날짜 :</label>
				<span style="color: #666; margin-right: 15px;" id="sysdate">(${today})</span>
				<button type="submit" id="validateForm">등록하기</button> 
            </div>
        </div>
        </form>
				
			<div class="gym-photo">
    			<div id="recordTable">
        			<table class="recordList">	
            			<tr>
                			<th colspan="4">${loginMember.memberName}님의 기록일지</th>
            			</tr>
            			<tr>
                			<th>기록 일자</th>
                			<th>키</th>
                			<th>몸무게</th>
                			<th>목표몸무게까지 남은 몸무게</th>
            			</tr>
            			<c:forEach var="i" items="${list}">
                    			<td>${i.growthDate}</td>
                    			<td>${i.memberTall} cm</td>
                    			<td>${i.memberWeight} kg</td>	
                    			<td>
                        	<c:choose>
                            	<c:when test="${i.memberWeight - i.memberHopeWeight > 0}">
                                	- ${i.memberWeight - i.memberHopeWeight} kg
                            	</c:when>
                            	<c:otherwise>
	                                목표 달성!
                            	</c:otherwise>
                        	</c:choose>
                    			</td>
                		</tr>
            			</c:forEach>
        			</table>
        			<!-- 페이지네이션 위치는 테이블 바로 아래로 -->
        			<div id="pagination"></div>
    			</div>
			</div>
        		<div style="text-align: center; margin-top: 50px;">
        		<button type="button" id="showListBtn">기록 조회하기</button>
        		</div>
   			</div>
	</div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script>

	const checkObj = {
		"tall" : false,
		"weight" : false,
		"hopeWeight" : false
	};
	
	$('#memberTall').on('input',function(){
		checkObj.tall = false;
		
		const regExp = /^\d+(\.\d+)?$/;
		
		if($(this).val().length > 0){			
		if(regExp.test($(this).val())){
			checkObj.tall = true;
		}else{
			checkObj.tall = false;
			
		}
		}else{
			checkObj.tall = false;
		}
	});
	
	$('#memberWeight').on('input',function(){
		checkObj.weight = false;
		
		const regExp = /^\d+(\.\d+)?$/;
		
		if($(this).val().length > 0){			
		if(regExp.test($('#memberWeight').val())){
			checkObj.weight = true;
		}else{
			checkObj.weight = false;
			
		}
		}else{
			checkObj.weight = false;
		}
	});
	
	
	$('#hopeWeight').on('input',function(){
		checkObj.hopeWeight = false;
		
		const regExp = /^\d+(\.\d+)?$/;
		
		if($(this).val().length > 0){			
		if(regExp.test($('#hopeWeight').val())){
			checkObj.hopeWeight = true;
		}else{
			checkObj.hopeWeight = false;
			
		}
		}else{
			checkObj.hopeWeight = false;
		}
	});
	
	$('#validateForm').on('click',function(){

	    let str = '';
		
	    if (${sessionScope.loginMember != null}) {

	        for (let key in checkObj) {
	        	
	            if (!checkObj[key]) {

	                switch (key) {
	                
	                    case "tall":
	                        str = "키 입력은 숫자로만 입력 가능합니다.";
	                        break;
	                    case "weight":
	                        str = "몸무게 입력은 숫자로만 입력 가능합니다.";
	                        break;
	                    case "hopeWeight":
	                        str = "목표몸무게 입력은 숫자로만 입력 가능합니다.";
	                        break;
	                        
	                }

	                swal({
	                    title: "알림",
	                    text: str,
	                    icon: "warning"
	                });

	                return false; // submit 막기
	                
	            }
	        }

	      	return;
	        
	    }

	    return false;
	});
	
    $('#showListBtn').on('click', function () {
        $('#recordTable').toggle();
        if ($('#recordTable').is(':visible')) {
            setupPagination();
        }
    });

    function setupPagination() {
        const rows = $('.record-row');
        const rowsPerPage = 5;
        const totalRows = rows.length;
        const totalPages = Math.ceil(totalRows / rowsPerPage);
        const groupSize = 5;
        let currentPage = 1;

        function renderPage(page) {
            currentPage = page;
            const start = (page - 1) * rowsPerPage;
            const end = start + rowsPerPage;

            rows.hide();
            rows.slice(start, end).show();

            renderPagination();
        }

        function renderPagination() {
            const currentGroup = Math.floor((currentPage - 1) / groupSize);
            const startPage = currentGroup * groupSize + 1;
            let endPage = startPage + groupSize - 1;
            if (endPage > totalPages) endPage = totalPages;

            let paginationHtml = '<div>';

            if (startPage > 1) {
                paginationHtml += `<button class="page-btn" data-page="${startPage - 1}">&lt;이전</button>`;
            }

            for (let i = startPage; i <= endPage; i++) {
                paginationHtml += '<button class="page-btn ' + (i == currentPage ? 'active' : '') + '" data-page="' + i + '">' + i + '</button>';
            }

            if (endPage < totalPages) {
                paginationHtml += `<button class="page-btn" data-page="${endPage + 1}">다음&gt;</button>`;
            }

            paginationHtml += '</div>';

            $('#pagination').html(paginationHtml);

            $('.page-btn').off('click').on('click', function () {
                const selectedPage = parseInt($(this).data('page'));
                renderPage(selectedPage);
            });
        }

        renderPage(1); // 처음 실행 시 첫 페이지 표시
    }
</script>
</body>
</html>