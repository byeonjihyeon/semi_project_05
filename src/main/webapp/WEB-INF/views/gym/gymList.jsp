<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>헬스장 목록</title>
    <link rel="stylesheet" href="/resources/css/gym/gym-2.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	

    <main>
        <h1>헬스장 목록</h1>
        <input type="text" id="searchBox" placeholder="지역 검색">
        <button id="searchBtn">검색</button>

        <div id="gymContainer">
            <c:forEach var="gym" items="${gymList}">
                <div class="gym-card" onclick="location.href='/gym/detail?gymId=${gym.gymId}'">
                    <img src="" alt="헬스장 사진">
                    <div class="gym-info">
                        <h3>${gym.gymName}</h3>
                        <p>${gym.gymAddr}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
    <script>
        $('#searchBtn').click(function () {
            let keyword = $('#searchBox').val().toLowerCase();
            $('.gym-card').each(function () {
                let location = $(this).find('p').text().toLowerCase();
                $(this).toggle(location.includes(keyword));
            });
        });
    </script>
</body>
</html>
