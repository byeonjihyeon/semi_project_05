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
								<%-- 댓글 등록 --%>
				<c:if test="${not empty loginMember}">
					<div class="inputCommentBox">
									<%-- URL --%>
						<form action="/board/insertComment" method="post">
						<input type="hidden" name="boardNo" value="${boardInfo.memberId}"> <%-- 게시글 번호 --%>
						<input type="hidden" name="commentWriter" value="${loginMember.memberId}"> <%-- 댓글 작성자 --%>
							<ul class="comment-write">
								<li>
									<div class="input-item">
										<textarea name="commentContent"></textarea>
									</div>
								</li>
								<li>
									<button type="submit" class="btn-primary">등록</button>
								</li>
							</ul>
						</form>
					</div>
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
	console.log(fileName);
	console.log(filePath);
}

function deleteNotice(memberId) {
	swal({
		title : "식제",
		text : "게시글을 삭제하시겠습니까?",
		icon : "warning",
		buttons : {
			cancel : {
				text : "취소",
				value : false,
				visible : true,
				closeModal : true,
			},
			confirm : {
				text : "삭제",
				value :true,
				visible : true,
				closeModal : true <%-- closeModa 일수도?--%>
			}
		}
	}).then(function(val){
		if(val){			<%-- url --%>
			location.href="/board/deleteComment?commentNo="+commentNo + "&memberId=${boardInfo.memberId}";
		}
	});
}
<%-- 댓글 수정 버튼 클릭--%>
function mdfComment(obj, commentNo){
	//obj == 수정 a 태그
	//(1) 기존 댓글 보여주고 있는 p 태그 숨기기
	$(obj).parents("li").find("p.comment-content").hide();
	
	//(2) 수정할 수 있는 textarea를 감싸고 있는 div 화면에 보여주기
	$(obj).parents("li").find("div.input-item").show();
	
	//(3) '수정' => '수정완료'
	$(obj).text('수정완료');
	$(obj).attr('onclick','mdfCommentComplete(this, ' +commentNo+')');
	
	//(4) '삭제 ' => '수정취소'
	//텍스트만 변경시, 여전히 delComment라는 삭제 이벤트가 설정되어있으므로 이벤트 핸들러 함수 변경
	$(obj).next().text('수정취소');
	$(obj).next().attr('onclick', 'mdfCommentCancel(this, '+commentNo+')');
}
<%-- 수정 완료 클릭--%>
function mdfCommentComplete(obj,commentNo) {
	//JS에서 POST 방식으로 데이터 전송시, form 태그로 
	
	//동적생성
	//form 태그
	let form = $('<form>');
	form.attr('action', '/notice/updateComment');
	form.attr('method', 'post');
	
	//게시글 번호(댓글 수정후, 다시 상세보기로 이동할 때 필요)
	let noticeNo = $('<input>');
	noticeNo.attr('type','text');
	noticeNo.attr('name', 'noticeNo');
	noticeNo.attr('value','${notice.noticeNo}');
	
	//댓글 번호(SQL 조건식 작성할 때 필요)
	let commentNoEl = $('<input>');
	commentNoEl.attr('type', 'text');
	commentNoEl.attr('name', 'commentNo');
	commentNoEl.attr('value', commentNo);
	
	//댓글 내용
	let commentContent = $(obj).parents("li").find("div.input-item");
	
	//form 태그 하위에, 동적 생성 요소 자식으로 추가
	form.append(noticeNo);
	form.append(commentNoEl);
	form.append(commentContent);
	
	//body 태그 하위에, 동적 생성 form 태그 추가한후, submit 처리
	$('body').append(form);
	form.submit();
}
<%-- 수정 취소 클릭--%>
function mdfCommentCancel(obj, commentNo) {
	//obj == 수정취소 a 태그
	//(1) 기존 댓글을 보여주고 있는 p 태그 보여주기
	$(obj).parents("li").find("p.comment-content").show();
	
	//(2) 수정할 수 있는 textarea를 감싸고 있는 div 화면에서 숨기기
	$(obj).parents("li").find("div.input-item").hide();
	
	//(3) '수정완료' -> '수정'
	$(obj).prev().text('수정');
	$(obj).prev().attr('onclick', 'mdfComment(this, '+ commentNo +')');
	
	//(4) '수정취소' -> '삭제'
	$(obj).text('삭제');
	$(obj).attr('onclick', 'delComment('+commentNo+')');
}
</script>

</body>

</html>