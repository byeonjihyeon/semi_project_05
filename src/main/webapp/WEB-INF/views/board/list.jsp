<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->            

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
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
	.box7{
	border:2px solid black;
	width : 180px;
	height : 100px;
	}
	.box8{
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
</head>
<body>
	  <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  	<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
			 <main class="content">
	         	<section class='section notice-list-wrap'>
				<div class='page-title'>자유 게시판</div>
				<div class='page-title2'>최근 많이 본 TOP 5</div>
			 	
			 	
			 		
			 		
			 	<div class='box-container'>
			 		
			 		<div class="box3">박스3</div>
			 		<br><br>
			 		<div class="box7">박스4</div>
			 		<div class="box8">박스5</div>
			 	</div>
			 	
			 	<br><br>
				<br><br>
					<div class='list-header'>
					
					<%-- 로그인 안했을때 보여줌과 로그인 했을때 보여줌 --%>
					<c:choose>
						<c:when test="${not empty sessionScope.boardId }">
						</c:when>
						</c:choose>
						<a class='btn-point write-btn' href='/board/writeFrm'>글쓰기(일반)</a> <%-- 나중에 로그인시 보이게 추가해줄것 --%>
						<div class='box-container2'>
							<c:if test="${sortGubun eq 'asc'}">
					 			<div class="box4"><a href='/board/list?reqPage=1&sortGubun=desc'>최신순/오래된순</a></div>
							</c:if>
							<c:if test="${sortGubun eq 'desc'}">
								<div class="box4"><a href='/board/list?reqPage=1&sortGubun=asc'>최신순/오래된순</a></div>
							</c:if>
				 			
				 			<c:if test="${sortGubun eq 'asc'}">
				 				<div class="box5"><a href='/board/list?reqPage=1&sortGubun=desc'>조회수</a></div>
				 			</c:if>
				 			<c:if test="${sortGubun eq 'desc' }">
				 				<div class="box5"><a href='/board/list?reqPage=1&sortGubun=asc'>조회수</a></div>
				 			</c:if>
				 			<c:if test="${sortGubun eq 'asc'}">
				 				<div class="box6"><a href='/board/list?reqPage=1&sortGubun=desc'>추천수</a></div>
				 			</c:if>
				 			<c:if test="${sortGubun eq 'desc' }">
				 				<div class="box6"><a href='/board/list?reqPage=1&sortGubun=asc'>추천수</a></div>
				 			</c:if>
			 			</div>
					</div>
				<br>
				<div class='list-content'>
					<table class='tbl tbl-hover'>
						<tr>
							<th style="width:15%">번호</th>
							<th style="width:25%">제목</th>
							<th style="width:15%">작성자</th>
							<th style="width:15%">작성일자</th>
							<th style="width:15%">조회수</th>
							<th style="width:15%">추천수</th>
						</tr>
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td>
								<a href="/board/view?boardNo=${board.boardId}&?title=${board.boardTitle}&?writer=${board.memberId}&?updChk=true">${board.boardId}</a>
								</td>
								<td class='boardTitle'>${board.boardTitle}
								
								</td>
								<td>${board.memberId}</td>
								<td>${board.createdAt}</td>
								<td>${board.viewCount}</td>
								<td>${board.boardLikeCount}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="page-Navi" id='pageNavi'>${pageNavi}</div>
				<div class="input-group">
				<input type="text" id="chat-input" placeholder="메세지를 입력하세요 " autocomplete="off">
				<button type="button">입력</button>
				</div>
				
				
			</section>
	         </main>
		</div>     
	

</body>
</html>