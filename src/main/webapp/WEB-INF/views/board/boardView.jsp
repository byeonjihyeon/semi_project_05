<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
		<main class='content'>
		 <section class='section notice-write-wrap'>
								<form action="/board/update" method="post"  enctype="multipart/form-data" >
 								 <input type="hidden" name="boardId" id="boardId"  value="${boardInfo.boardId}">	
 								 
				<div class='page-title'>게시글 조회</div>
 						<input type="hidden" name="memberId" id="loginMemberId" value="${loginMember.memberId}">
						<table class='tbl'>
							<tr>
								<th class="name-title">제목</th>
								<td colspan='3'>
									<div class='input-item'>
										${boardInfo.boardTitle}
									</div>
								</td>
							</tr>
							<tr>
								<th style='width:10%'>작성자</th>
								<td colspan='3' style='width:40%; text-align: left;'>
									<div class='input-item'>
									${boardInfo.memberId}
									</div>
								</td>
							</tr>
							<tr>
								<td colspan='3'>
									<div class='input-item'>
										${boardInfo.boardContent}
									</div>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan='3'>
									<c:forEach var='file' items='${boardInfo.fileList}'>
										<a href="javascript:fileDown('${file.fileName}', '${file.filePath}')">${file.fileName}</a>
									</c:forEach>
							</tr>
						</table>
						</form>
						
								<c:if test="${loginMember.memberId eq boardInfo.memberId}">				
									<form action="/board/delete" method="get" >
									<input type="hidden" name="deleteBoardId" value="${boardInfo.boardId}">
									<input type="hidden" name="boardType" value="free">
												<button type="submit" id="deleteNotice">삭제</button>
									</form>
									<a href='/board/updateFrm?boardNo=${boardInfo.boardId}'>수정</a>
								</c:if>
							<%--게시글에 대한 댓글 출력 --%>
				<div class='commentBox'>
					<c:forEach var='comment' items="${notice.commentList}">
						<ul class='posting-comment'>
							<li>
								<p class='comment-info'>
									<span class='material-icons'>account_box</span>
									<span>${comment.commentWriter}</span>
									<span>${comment.commentDate}</span>
									<%--로그인 회원의 아이디와, 댓글작성자 아이디가 같은 경우 수정과 삭제 버튼 화면에 표출 --%>
									<c:if test="${not empty loginMember and loginMember.memberId eq comment.commentWriter}">
										<a href="javascript:void(0)" onclick="mdfComment(this, ${comment.commentNo})">수정</a>
										<a href="javascript:void(0)" onclick='delComment(${comment.commentNo})'>삭제</a>
									</c:if>
								</p>
								<p class='comment-content'>
									${comment.commentContent}
								</p>
								<%--화면을 처음 랜더링할 때, 숨겼다가 수정버튼 클릭시 화면에 표기 --%>
								<div class='input-item' style='display: none;'>
									<textarea name='commentContent'>${comment.commentContent }</textarea>
								</div>
							</li>
						</ul>
					</c:forEach>
					</div>
					</section>
			 </main>
		</div>     
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script>
function fileDown(fileName, filePath) {
	location.href='/board/fileDown?fileName=' + encodeURIComponent(fileName) + '&filePath=' + filePath+"&type=free";
}
</script>

</body>

</html>