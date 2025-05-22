<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/resources/js/sweetalert.min.js"></script>
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
						
								<c:if test="${loginMember.memberId eq boardInfo.memberId}">				
						<form action="/board/delete" method="get" >
						<input type="hidden" name="deleteBoardId" value="${boardInfo.boardId}">
									<button type="submit" id="deleteNotice">삭제하기</button>
						</form>
								</c:if>
			</section>
				
	         </main>
		</div>     
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script>
	
	$('#deleteNotice').on('click', function(e) {
		
	    e.preventDefault();
	    swal({
	        title: "알림",
	        text: "게시물을 삭제하시겠습니까 ?",
	        icon: "warning",
	        buttons: {
	            cancel: {
	                text: "취소",
	                value: false,
	                visible: true,
	                closeModal: true
	            },
	            confirm: {
	                text: "삭제",
	                value: true,
	                visible: true,
	                closeModal: true
	            }
	        }
	    }).then(function(val) {
	        if (val) {
	            // 버튼이 들어 있는 form을 찾아서 submit
	        	$(e.target).closest('form').submit();
	        }
	    });
	});
		
		
    //form 태그의 action 속성 변경   /board/update
    //수정하기 요청을 처리할 UpdateServlet 생성하여, 값 추출
    //변경하기 클릭 시 동작할 함수 지정.
    //SWAL로 수정 여부 확인 받고, 수정하기 클릭 시
    
    
	$('#updateNotice').on('click', function(e){
		
		e.preventDefault(); // 강제로 submit 막아주는거야
		swal({
			title : "알림",
			text : "게시물을 수정하시겠습니까?",
			icon : "warning",
			buttons : {
				cancel : {
					text :"취소",
					value : false,
					visible : true,
					closeModal : true
				},
				confirm : {
					text : "수정",
					value : true,
					visibla : true,
					closeModal : true
				}
			}
		}).then(function(val){
			if(val) {
				$(e.target).closest('form').submit();
		            
			}
		});
		
	});
    
		

		
	

</script>
</body>
</html>