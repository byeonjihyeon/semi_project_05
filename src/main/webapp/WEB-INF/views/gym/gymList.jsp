<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     <html>
<head>
    <title>헬스장 목록</title>
    <link rel="stylesheet" href="/resources/css/gym/gymList.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
		    .wrap {
		  max-width: 1000px;
		  margin: 0 auto;
		  padding: 20px;
		  font-family: ns-r;
		}
		
		.search-container {
		  text-align: center;
		  margin-bottom: 20px;
		}
		
		.search-container input {
		  width: 300px;
		  padding: 10px;
		  border-radius: 6px;
		  border: 1px solid var(--line2);
		}
		
		.search-container button {
		  padding: 10px 16px;
		  margin-left: 8px;
		  background-color: var(--main2);
		  color: white;
		  border: none;
		  border-radius: 6px;
		  font-family: ns-b;
		  cursor: pointer;
		}
		
		.filter-buttons {
		  text-align: center;
		  margin-bottom: 20px;
		}
		
		.filter-buttons button {
		  margin: 0 6px;
		  padding: 8px 12px;
		  border: 1px solid var(--line3);
		  border-radius: 6px;
		  background-color: white;
		  font-family: ns-r;
		  cursor: pointer;
		}
		
		#gymContainer {
		  display: flex;
		  flex-direction: column;
		  gap: 16px;
		}
		
		.gym-card {
		  display: flex;
		  align-items: center;
		  background-color: white;
		  border: 1px solid var(--line3);
		  border-radius: 12px;
		  padding: 16px;
		  cursor: pointer;
		  transition: box-shadow 0.2s ease;
		}
		
		.gym-card:hover {
		  box-shadow: 0 2px 10px rgba(0,0,0,0.08);
		}
		
		.gym-img {
		  flex-shrink: 0;
		  width: 120px;
		  height: 90px;
		  background-color: var(--gray7);
		  display: flex;
		  justify-content: center;
		  align-items: center;
		  margin-right: 16px;
		  overflow: hidden;
		  border-radius: 8px;
		}
		
		.gym-img img {
		  width: 100%;
		  height: 100%;
		  object-fit: cover;
		}
		
		.gym-info h3 {
		  margin: 0 0 6px 0;
		  font-family: ns-b;
		  font-size: 18px;
		  color: var(--gray1);
		}
		
		.gym-info p {
		  margin: 2px 0;
		  font-size: 14px;
		  color: var(--gray2);
		}
		
		
		    
    </style>
</head>


<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<main>
    <div class="wrap">
        <div class="search-container">
            <input type="text" id="searchBox" placeholder="주소를 입력하세요.">
            <button id="searchBtn">검색</button>
        </div>

        <div class="filter-buttons">
            <button>가격이 낮은순</button>
            <button>평점 높은순</button>
            <button>가격 높은순</button>
        </div>

        <div id="gymContainer">
            <c:forEach var="gym" items="${gymList}">
                <div class="gym-card" onclick="location.href='/gym/detail?gymId=${gym.gymId}'">
                    <div class="gym-img">
                        <img src="${gym.fileList[0].fileUrl}" alt="헬스장 이미지">
                        
                    </div>
                    <div class="gym-info">
                        <h3>${gym.gymName}</h3>
                        <p>가격: ${gym.ticket.oneMonth}원 ~</p>
                        <%--<p>평점: ${gym.rating}⭐</p> --%>
                        <p>${gym.gymAddr}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

<script>
    $('#searchBtn').click(function () {
        let keyword = $('#searchBox').val().toLowerCase();
        $('.gym-card').each(function () {
            let location = $(this).find('.gym-info p:last').text().toLowerCase();
            $(this).toggle(location.includes(keyword));
        });
    });
</script>
</body>
</html>
