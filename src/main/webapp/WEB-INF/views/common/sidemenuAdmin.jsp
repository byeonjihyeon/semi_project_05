<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
 <style>

  </style>
</head>
<body>
	<div class="container">
        <aside class="sidebar">
          <nav>
            <ul>
              <li><a>관리자 페이지</a></li>
              
              <li><a href="/admin/super/insertAdminFrm">관리자 등록</a></li>
              <li><a href='/admin/super/admins'>관리자 조회</a>
              <li><a href="/admin/member/list?page=1">회원 관리</a></li>
              <li><a href="/admin/gym/list?page=1">헬스장 관리</a></li>
              <li><a href="/admin/gym/applications?page=1">헬스장 신청내역</a></li>
              <li><a href="/admin/board/inquiries?page=1">일대일 문의내역</a></li>
              <li><a>내정보 변경</a></li>
              <li><a>비밀번호 변경</a>
            </ul>
          </nav>
        </aside>
        <%-- 
        <main class="content">
          <h1>메인 콘텐츠</h1>
          <p>여기에 메인 콘텐츠가 들어갑니다.</p>
        </main>
        --%>
      </div>
</body>
</html>