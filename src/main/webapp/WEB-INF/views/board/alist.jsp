<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->            
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<style>
	<%-- 자유게시판 글씨 --%>
	div.page-title{
	color : black;
	
	}

.list-content{

	color : black;

	}
	
.box-container2{
	display : flex;
	gap : 30px;
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
</style>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
	 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
	<main class="content">
	<section class='section notice-list-wrap'>
	<div class="page-title">공지사항</div>
		<br><br>
		
	<div class="list-header">
		<a class="btn-point write-btn" href="/board/writeAFrm">공지사항쓰기(매니저만 보이게)</a>
		
		
	<div class="box-container2">
		<c:if test="${sortGubun eq 'asc' }">
			<div class="box4"><a href="/board/alist?reqPage=1&sortGubun=desc">최신순/오래된순</a></div>
		</c:if>
		<c:if test="${sortGubun eq 'desc' }">
			<div class="box4"><a href="/board/alist?reqPage=1&sortGubun=asc">최신순/오래된순</a></div>
		</c:if>
		<c:if test="${sortGubun eq 'asc' }">
			<div class="box5"><a href="/board/alist?reqPage=1&sortGubun=desc">조회수</a></div>
		</c:if>
		<c:if test="${sortGubun eq 'desc' }">
			<div class="box5"><a href="/board/alist?reqPage=1&sortGubun=asc">조회수</a></div>
		</c:if>
	</div>	
</div>
	<br>
	<div class="list-content">
	<table class="tbl tbl-hover">
	<tr>
		<th style="width:15%">번호</th>
		<th style="width:25%">제목</th>
		<th style="width:15%">작성자</th>
		<th style="width:15%">작성일자</th>
		<th style="width:15%">조회수</th>
		<th style="width:15%">추천수</th>
	</tr>
	<c:forEach var="board" items="${boardList }">
		<tr>
			<td><a href="/board/view?boardno=${board.boardId}&updChk=true">${board.boardId}</a></td>
			<td class="boardTitle">${board.boardTitle}</td>
			<td>${board.memberId}</td>
			<td>${board.createdAt}</td>
			<td>${board.viewCount}</td>
			<td>${board.boardLikeCount}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<div class="page-Navi" id="pageNavi">${pageNavi}</div>
	<form id="chat">
	<input type="text" id="chat-input" placeholder="메세지를 입력하세요"	autocomplete="off">
	<button type="submit">입력</button>
	</form>
	</section>
	</main>
		
	</div>
</body>
</html>