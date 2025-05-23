<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     
<srcipt src=https://code.jquery.com/jquery-3.7.1.min.js></srcipt>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
.list-content {
	height: 800px;
}
button {
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  color: #333;
  padding: 6px 12px;
  font-size: 14px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

/* 검색창 (input type=text) */
input[type="text"] {
  padding: 6px 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
  transition: border-color 0.2s ease;
  height: 34px;
  vertical-align: middle;
}

input[type="text"]:focus {
  border-color: #888;
}

/* 조회 버튼 (input type=button) */
input[type="button"] {
  padding: 6px 12px;
  font-size: 14px;
  background-color: #f0f0f0;
  color: #333;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
  height: 36px;
  vertical-align: middle;
  margin-left: 6px;
  transition: background-color 0.2s ease;
}

select {
  -webkit-appearance: none;
  -moz-appearance: none;

  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 6px 10px;
  font-size: 14px;
  color: #333;
  width: 80px;
  height: 36px;
  vertical-align: middle;
  outline: none;
  cursor: pointer;
 
  /* 오른쪽 화살표 아이콘 (SVG) */
  background-image: url("data:image/svg+xml;utf8,<svg fill='gray' height='18' viewBox='0 0 24 24' width='18' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
}

select:focus {
  border-color: #888;
}

[name='search']{
width: 150px;
}
.member-info-btn-small {
  padding: 5px 10px;
  font-size: 0.9rem;
  background-color: #CE2029;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left : 15px;
}
</style>
</head>
<body>
 <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
  	<div class="container">
		<jsp:include page="/WEB-INF/views/common/sidemenuAdmin.jsp"></jsp:include>
			<main class="content">
	        	<section class='section notice-list-wrap'>
				<div class='page-title'>회원 관리</div>
					<div class='list-content'>
						<%-- 회원 아이디 또는 이름으로 조회할 폼태그 --%>
						<div>
							<form action='javascript:void(0)' method="get">
								<select id='searchField' name='searchField'>
									<option value='member_id'>아이디</option>
									<option value='member_name'>이름</option>
								</select>
								<input type='text' name='search' id='searchValue'>
								<input type='button' id='search' value='조회'>
							</form>
						</div>
						<table class='adminTbl tbl-hover'>
							<tr>
								<th style='width:10%;'>NO</th>
								<th style='width:15%;'>회원 아이디</th>
								<th style='width:15%;'>이름</th>
								<th style='width:15%;'>등급</th>
								<th style='width:15%;'>가입일</th>
								<th style='width:15%'>관리</th>
							</tr>
							<c:forEach var='member' items="${memberList}" varStatus='status'>	
								<tr class='memberlist'>
									<td>${(status.index + 1) +(currentPage -1) * pageSize}</td>
									<td id='memberId'>${member.memberId}</td>
									<td>${member.memberName}</td>
									<td>${member.memberGrade}</td>
									<td>${member.memberDate}</td>
									<td>
										<a href="/admin/member/details?id=${member.memberId}"><button>조회</button></a>
										<a href="#"><button onclick="deleteMember('${member.memberId}')">삭제</button></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				<div id='pageNavi'>${pageNavi}</div>
			</section>	
	   		</main>
	</div>  
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>   
</body>
<script>
//'조회'클릭 이벤트 발생시 이름 또는 아이디로 조회
$('#search').on('click', function(){
	
	
	let field = $('#searchField').val();
	let search = $('#searchValue').val();
	
	
	$.ajax({
		url : "/admin/member/searchInfo",
		data : {"field": field, "search" : search},
		type : "get",
		success : function(res){
			if(res.length === 0){
				alert('검색한 회원이 존재하지 않습니다');
			}else{
				$('.memberlist').remove();
				
				$.each(res, function(idx,item){
					let html = '';
					html += "<tr class='memberlist'>";
					    html += "<td>" + (idx + 1) + "</td>";
						html += "<td>" + item.memberId +"</td>";
						html += "<td>" + item.memberName +"</td>";
						html += "<td>" + item.memberGrade +"</td>";
						html += "<td>" + item.memberDate +"</td>";
					
						html += "<td>";
						html += "<a href='/admin/member/details?id="+item.memberId+"'><button>조회</button></a>";
						html += "<button onclick='deleteMember("+ item.memberId +")'>삭제</button>";
						html += "</td>";
						
					html += "</tr>";
						
					$('.adminTbl').append(html);
					$('#pageNavi').remove();
				});
				
			}
		},
		error : function(){
			console.log('ajax 통신 오류');
		}
	});
	

	
});

function deleteMember(memberId) {
	
	  swal({
		 title : "삭제",
		 text : "삭제 하시겠습니까?",
		 icon : "warning",
		 buttons : {
			 cancel : {
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
	  }).then(function(val){
		if(val){ //삭제 버튼 클릭시 동작
			location.href="/admin/member/delete?id="+ memberId;
		}  
	  });
}
</script>
</html>