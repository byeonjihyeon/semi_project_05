<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 조회</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
		<div class="container">
			<jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
			<main class='content'>
				<section class="section notice-write-wrap">
			<form action="/aboard/update" method="post"	enctype="multipart/form-data">
				<input type="hidden" name="boardId" id="boardId" value="${boardInfo.boardId }">
				
				<div class="page-title">게시글 조회</div>
					<input type="hidden" name="memberId" id="loginMemberId" value="${loginMember.memberId }">
					<table class="tbl">
					<tr>
						<th class="name-title">제목</th>
						<td colspan="3">
							<div class="input-item">
								${boardInfo.boardTitle }
							</div>
						</td>
					</tr>
					<tr>
						<th style="width:10%">작성자</th>
						<td colspan="3" style="width:40%; text-align: left;">
						<div class="input-item">
							${boardInfo.memberId }
						</div>
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
								<c:forEach var='file'  items='${boardInfo.fileList}'>
								<a href="javascript:fileDown('${file.fileName}', '${file.filePath}')">${file.fileName}</a>
							</c:forEach>
					</tr>
					</table>
					</form>
						<c:if test="${loginMember.memberId } eq boardInfo.memberId}">
							<form action="/board/delete" method="get">
							<input type="hidden" name="deleteBoardId" value="${boardInfo.boardId }">
								<button type="submit" id="deleteNotice">삭제</button>
							</form>
							<a href="/board/updateFrm?boardNo=${boardInfo.boardId }">수정</a>
						</c:if>
		
		</section>
		</main>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script>
		function fileDown(fileName, filePath) {
			location.href="/board/fileDown?fileName=" +  encodeURIComponent(fileName) + "&filePath=" + filePath;
		}
	</script>
</body>
</html>