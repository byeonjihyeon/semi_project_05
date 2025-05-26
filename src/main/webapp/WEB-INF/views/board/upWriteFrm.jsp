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
 								 <input type="hidden" name="boardId" id="boardId"  value="${oneBoard.boardId}">	
 								 <input type="hidden" name="boardType" id="boardType"  value="B">	
 								 
				<div class='page-title'>게시글 수정</div>
						<%-- tbl_notice의 notice_writer(작성자) 컬럼의 값은 회원 번호! --%>					
  								<input type="hidden" name="memberId" id="loginMemberId" value="${loginMember.memberId}">
						<table class='tbl'>
							<tr>
								<th class="name-title">제목</th>
								<td colspan='3'>
									<div class='input-item'>
										<input type='text' name='boardTitle' id="boardTitle" value="${oneBoard.boardTitle}">
									</div>
								</td>
							</tr>
							
							<tr>
								<th style='width:10%'>작성자</th>
								<td style='width:40%; text-align: left;'>
									<input type="text" style="border: none;" name="memberId" id="memberId" value="${oneBoard.memberId}" disabled>
								</td>
							</tr>
							
							<tr>
								<td colspan='4'>
									<div class='input-item'>
									
										<textarea name='boardContent' id="boardContent">${oneBoard.boardContent}</textarea>
									</div>
								</td>
							</tr>
								
							<tr>
							
								<th style='width:10%'>첨부파일</th>
								<td style='width:40%'> 
								<div>
								<c:forEach var="file" items="${oneBoard.fileList}" >
									<div class="files">
										<span class='delFileName'>${file.fileName}</span>			
										<span class="material-icons delBtn" onclick="delFile(this, ${file.fileNo})">remove_circle</span>						
									<%-- 1) 작성한 게시물 이미지 꺼내오기 --%>
									<%-- 2) 로그인한 아이디랑 작성자 아이디가 같으면 파일선택 버튼과, 문구 보여주기  --%>
								
									</div>										
								</c:forEach>
								</div>
									<input type='file' name='uploadImg' >
								<td>gif, jpg, png, pdf, doc, docx, ppt, pptx, xls, xlsx, mp3 파일 형식만 가능합니다.(5MB 이하)</td>
							</tr>
								
							
							<c:if test="${loginMember.memberId eq oneBoard.memberId}">	
								<tr>
									<td>
	  									<button type="submit" id="updateNotice">수정하기</button>
									</td>
	
								</tr>
							</c:if>
							
							
						</table>
						</form>
			</section>
				
	         </main>
		</div>     
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script>
	function delFile(obj, fileNo){
		//파일 삭제 아이콘 클릭시, form태그 내부에 hidden타입으로 input 태그 추가
		let input = $('<input>');
		input.attr('type','hidden');
		input.attr('name','delFileNo');
		input.attr('value', fileNo);
		$(obj).parent().remove();
		$('form').prepend(input);
	}
	
	
	/*
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
    
	*/	

		
	

</script>
</body>
</html>