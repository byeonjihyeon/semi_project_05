<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정하기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
		<main class='content'>
		 <section class='section notice-write-wrap'>
		 						<%-- 여기가 수정하기 누르면 나오는곳 --%>
								<form action="/board/update2" method="post"  enctype="multipart/form-data" >
 								 <input type="hidden" name="boardId" id="boardId"  value="${boardInfo.boardId}">	
 								 
				<div class='page-title'>게시글 조회</div>
						<%-- tbl_notice의 notice_writer(작성자) 컬럼의 값은 회원 번호! --%>					
  								<input type="hidden" name="memberId" id="loginMemberId" value="${loginMember.memberId}">
						<table class='tbl'>
							<tr>
								<th class="name-title">제목</th>
								<td colspan='3'>
									<div class='input-item'>
										<input type='text' name='boardTitle' id="boardTitle" value="${boardInfo.boardTitle}">
									</div>
								</td>
							</tr>
							
							<tr>
								<th style='width:10%'>작성자</th>
								<td style='width:40%; text-align: left;'>
									<input type="text" style="border: none;" name="memberId" id="memberId" value="${boardInfo.memberId}" disabled>
								</td>
							</tr>
							
							<tr>
								<td colspan='4'>
									<div class='input-item'>
									
										<textarea name='boardContent' id="boardContent">${boardInfo.boardContent}</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<th style='width:10%'>이미지 첨부</th>
								<td style='width:40%'>
									<input type='file' name='uploadImg'>
								
								</td>
								<td>이미지는 gif, jpg, png 파일 형식만 가능합니다.(5MB 이하)</td>
							</tr>
							
							<tr>
								<th style='width:10%'>첨부파일</th>
								<td style='width:40%'>
									<input type='file' name='uploadFile'>
								</td>
								<td>pdf, doc, docx, ppt, pptx, xls, xlsx, mp3 파일형식만 가능합니다.(5MB 이하)</td>
							</tr>
							<c:if test="${loginMember.memberId eq boardInfo.memberId}">
							<tr>
								<td>
  									<button type="submit" id="updateNotice">수정하기</button>
								</td>
								
						<%-- <button type="submit" id="updateNotice">수정하기</button> --%>
							</tr>
							</c:if>
						</table>
						</form>
</body>
</html>