<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
</head>
<style>
	<%-- 자유게시판 글씨 --%>
	div.page-title{
	color : black;
	
	}


	
	<%-- 제목,작성자~ 글씨 --%>
	.list-content{

	color : black;

	}
	
	<%-- 글쓰기 일반 --%>
	.btn-point.write-btn{
	float: right;
	padding : 10px;
	margin : 5px;
	color : black;
	
	}
	
	.active-page{
	color : black;
	}
	<%-- 박스 컨테이너--%>
	.box-container{
	display : flex;
	justify-content:center;
	gap : 30px;
	}
	.box1{
	border:2px solid black;
	width : 180px;
	height : 100px;
	}
	.box2{
	border:2px solid black;
	width : 180px;
	height : 100px;
	}
	.box3{
	border:2px solid black;
	width : 180px;
	height : 100px;
	}
	
	.box-container2{
	display : flex;
	
	gap:30px;
	}
	.box4{
	border : 1px solid black;
	width : 80px;
	padding : 5px;
	text-align : center;
	color :  pink;
	background color : pink;
	}
	.box5{
	border : 1px solid black;
	width : 70px;
	text-align : right;
	padding : 10px;
	}
	.box6{
	border : 1px solid black;
	width : 70px;
	text-align : right;
	padding : 10px;
	}
	
	.box7 {
	border : 1px solid black;
	
	width : 70px;
	height : 30px;
	
	}
	.box-container3{
	color : blue;
	width: 100%;
	
	}
	
</style>
<body>
	  <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  	<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
			 <main class="content">
	         	<section class='section notice-list-wrap'>
				<div class='page-title'>자유 게시판</div>
				<div class='page-title2'>최근 많이 본 TOP 10</div>
			 	<div class='box-container'>
			 		<div class="box1">
			 			<a href="https:/www.naver.com">네이버</a> <%-- 자료 한번 넣어봤음 a href--%>
			 		</div> 
			 		<div  class="box2">박스</div>
			 		<div class="box3">박스</div>
			 	</div>
			 	
			 	<br><br>
				<br><br>
					<div class='list-header'>
						<a class='btn-point write-btn' href='/board/writeFrm'>글쓰기(일반)</a> <%-- 나중에 로그인시 보이게 추가해줄것 --%>
						
						<div class='box-container2'>
			 			<div class="box4">최신순/오래된순</div>  <%-- onclick --%>
			 			<div class="box5">조회수</div>
			 			<div class="box6">추천수</div>
			 	</div>
					</div>
				<br>
				<div class='list-content'>
					<table class='tbl tbl-hover'>
						<tr>
							<th style='width:10%;'>번호</th>
							<th style='width:30%;'>제목</th>
							<th style='width:15%;'>작성자</th>
							<th style='width:20%;'>작성일</th>
							<th style='width:10%;'>조회수</th>
							<th style='width:10%;'>추천수</th>
						</tr>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td class='boardTitle'><a href='/board/view?boardNo=${board.boardId}&updChk=true'>${board.boardTitle}</a></td>
								<td>${board.memberId}</td>
								<td>${board.createdAt}</td>
								<td>${board.viewCount}</td>
								<td>${board.boardLikeCount}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<table class="tbl tbl-hover">
				<tr>
					<th style='width:10%;'>번호</th>
					<th style='width:30%;'>제목을 써야합니다</th>
					<th style='width:15%;'>작성자</th>
					<th style='width:20%;'>작성일</th>
					<th style='width:10%;'>조회수</th>
					<th style='width:10%;'>추천수</th>
				</tr>
				</table>
				
				<div class="page-Navi" id='pageNavi'>${pageNavi}</div>
				<div class="box-container3">
		
			
				</div>
				<div class="box7"></div>
				<button class="btn-primary lg" >입력</button>
				
			</section>
	         </main>
		</div>     
		
</body>
</html>