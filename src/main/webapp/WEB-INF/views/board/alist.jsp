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
	<div class="list-header">
		<a class="btn-point write-btn" href="/board/writerFrm">공지사항쓰기(매니저만 보이게)</a>
	</div>
	<div class="list-content">
	<table class="tbl tbl-hover">
	<tr>
		<th style="width:40%">번호</th>
		<th style="width:40%">제목</th>
		<th style="width:40%">작성자</th>
		<th style="width:40%">작성일자</th>
		<th style="width:40%">조회수</th>
		<th style="width:40%">추천수</th>
	</tr>
	<c:forEach var="board" items="${boardList }">
		<tr>
			<td class="boardTitle"><a href="/board/view?boardno=${board.boardId}&updChk=true">${board.boardTitle}</a></td>
			<td>${board.memberId}</td>
			<td>${board.createdAt}</td>
			<td>${board.viewCount}</td>
			<td>${board.boardLikeCount}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<div class="page-Navi" id="pageNavi">${pageNavi}</div>
	</section>
	</main>
		
	</div>
</body>
</html>