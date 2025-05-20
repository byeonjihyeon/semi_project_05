<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->
    <link rel="stylesheet" href="/resources/css/login.css"> <!-- CSS 파일 연결 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
		<main class='content'>
		 <section class='section notice-write-wrap'>
				<div class='page-title'>게시글 조회</div>
				<form action='/board/delete'
						method='post' <%--첨부파일 업로드시, method는 모조건 post로 지정하고, enctype은 multipart로 지정해야함. --%>
						enctype='multipart/form-data'>
						<%-- tbl_notice의 notice_writer(작성자) 컬럼의 값은 회원 번호! --%>
						<input type='hidden' name='boardWriter' value='${loginMember.memberNo}'>
						<table class='tbl'>
							<tr>
								<th class="name-title">제목</th>
								<td colspan='3'>
									<div class='input-item'>
										<input type='text' name='boardTitle' value="${boardInfo.boardTitle }">
									</div>
								</td>
							</tr>
							
							<tr>
								<th style='width:10%'>작성자</th>
								<td style='width:40%'>
									<input type="text" name="memberId" id="memberId" value="${boardInfo.memberId}" disabled>
								</td>
							</tr>
							
							<tr>
								<td colspan='4'>
									<div class='input-item'>
									
										<textarea name='boardContent'>${boardInfo.boardContent}</textarea>
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
							
							<tr>
								
								<td>
									<button type="submit" onclick="validateForm()" id="deleteNotice">삭제하기</button>
								</td>
								<td>
									<button type="button" id="updateNotice">변경하기</button>
								</td>
							</tr>
						</table>
				</form>
			</section>
	         </main>
		</div>     
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<script>
	
	function validateForm(){
		
		if(${'#memberId'}.val() == ${loginMember.memberId}){
			swal({
				title : "알림",
				text : "이 게시물을 삭제하시겠습니까?",
				icon : "warning",
				buttons : {
					cancle : {
						text : "취소",
						value : false,
						visible : true,
						closeModal : true
					},
					confirm : {
						text : "삭제",
						value : true,
						visible : true,
						closeModal : true
					}
				}
			}).then(function(val)){
				if(val){
					$('#deleteNotice').onsubmit();
				}
			}
		}
		}
		
	

</script>
</body>
</html>