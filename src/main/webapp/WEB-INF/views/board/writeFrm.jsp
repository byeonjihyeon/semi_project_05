<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
		<div class="container">
		 <jsp:include page="/WEB-INF/views/common/sidemenuBoard.jsp"></jsp:include>
			 <main class="content">
	         	<section class='section notice-write-wrap'>
				<div class='page-title'>게시판 작성</div>
				<form action='/board/write'
						method='post' <%--첨부파일 업로드시, method는 모조건 post로 지정하고, enctype은 multipart로 지정해야함. --%>
						enctype='multipart/form-data'
						onsubmit='return validateForm()'>
						<input type='hidden' name='memberId' value='${loginMember.memberId}'>
						<input type='hidden' name='boardType' value='B'> <%-- 자유게시판 : B --%>

						<table class='tbl'>
							<tr>
								<th class="name-title">제목</th>
								<td colspan='3'>
									<div class='input-item'>
										<input type='text' name='boardTitle'>
									</div>
								</td>
							</tr>
							
							<tr>
								<th style='width:10%'>작성자</th>
								<td style='width:40%; text-align: left;' >
									<span>${loginMember.memberId}</span>
								</td>
							</tr>
							
							<tr>
								<td colspan='4'>
									<div class='input-item'>
									
										<textarea name='boardContent' placeholder="내용을 적어주세요"></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<th style='width:10%'>이미지 첨부</th>
								<td style='width:40%'>
									<input type='file' name='uploadFile1'>
								
								</td>
								<td>이미지는 gif, jpg, png 파일 형식만 가능합니다.(5MB 이하)</td>
							</tr>
							
							<tr>
								<th style='width:10%'>첨부파일</th>
								<td style='width:40%'>
									<input type='file' name='uploadFile2'>
								</td>
								<td>pdf, doc, docx, ppt, pptx, xls, xlsx, mp3 파일형식만 가능합니다.(5MB 이하)</td>
							</tr>
							<tr>
							
								<td colspan='4'>
									<button class='btn-primary lg' id="check" name="check">작성하기</button>
								</td>
							</tr>
						</table>
				</form>
			</section>
	         </main>
		</div>     
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
	<script>
	
	</script>
	</body>
</html>