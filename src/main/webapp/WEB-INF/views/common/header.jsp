<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->    
<link rel="stylesheet" href="/resources/css/default.css"> <!-- 선생님이 이미 만든 파일 연결 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> <!-- 구글에서 제공해주는 아이콘 링크태그 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- jQuery 연결 -->
<script src="/resources/js/sweetalert.min.js"></script> <!-- 로그인 결과 알림창 용도 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/resources/css/default.css"> <!-- CSS 파일 연결 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- jQuery 연결 -->
	<style>
		header a, footer a {
		 text-decoration: none;
		 color: #555;
		}
	</style>
</head>
<body>
 <header class="header">
       <div class="header-container">
         <div class="header-left"> 
           <nav class="main-menu">
             <a id='logo' href="/">MUTGYM LOGO</a>
             <div class="menu-item">헬스
               <div class="submenu">
                 <a href="#">헬스장 찾기</a>
                 <a href="/gym/registerFrm">헬스장 등록</a>
               </div>
             </div>
             <div class="menu-item">게시판
               <div class="submenu">
                 <a href="/board/alist?reqPage=1">공지사항</a>
                 <a href="/board/list?reqPage=1">자유게시판</a>
               </div>
             </div>
             	<%--관리자인 경우에만 보이는 관리자페이지--%>
             	 <c:if test="${not empty sessionScope.loginAdmin}">
             	 	<div class="menu-item">관리자
              		 <div class="submenu">
		                 <a href="/admin/member/list?page=1">회원 관리</a>
		                 <a href="/admin/gym/list?page=1">헬스장 관리</a>
		                 <a href="/admin/gym/applications?page=1">헬스장 신청내역</a>
		                 <a href="/admin/board/inquiries?page=1"">일대일 문의내역</a>
              		 </div>
            	 	</div>
             	 </c:if>
           </nav>
         </div>
         <div class="header-right">
           <nav class="main-menu">
           <c:choose>
           		<%--회원(헬스장포함) , 관리자 session 둘다 null 인 경우 --%>
				<c:when test='${empty sessionScope.loginMember and empty sessionScope.loginAdmin}'>
             <div class="menu-item"><a href='/member/loginFrm'>로그인</a>
              <%-- 서브 메뉴 만들시 사용할
               <div class="submenu">
                 <a href="#">회원가입</a>
                 <a href="#">내 정보</a>
               </div>
               --%>
             </div>
             <div class="menu-item"><a href='#'>회원가입</a>
             	<%-- 서브 메뉴 만들시 사용할 
               <div class="submenu">
                 <a href="#">문의하기</a>
                 <a href="#">FAQ</a>
               </div>
               --%>
             </div>
             </c:when>
				<c:otherwise>
             <div class="menu-item"><a href='#'>마이페이지</a>
              <%-- 서브 메뉴 만들시 사용할
               <div class="submenu">
                 <a href="#">회원가입</a>
                 <a href="#">내 정보</a>
               </div>
               --%>
             </div>
             <div class="menu-item"><a href='/member/logOut'>로그아웃</a>

             	<%-- 서브 메뉴 만들시 사용할 
               <div class="submenu">
                 <a href="#">문의하기</a>
                 <a href="#">FAQ</a>
               </div>
               --%>
             </div>
            	 </c:otherwise>
			</c:choose>
           </nav>
         </div>
       </div>
     </header>
</body>
</html>