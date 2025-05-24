<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MUTGYM 메인페이지</title>
    <link rel="stylesheet" href="/resources/css/default.css">
    <link rel="stylesheet" href="/resources/css/index.css"> <!-- 기존 CSS 유지 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        .material-icons { /* Basic styling for Material Icons */
            font-family: 'Material Icons'; font-weight: normal; font-style: normal;
            font-size: 24px; display: inline-block; line-height: 1;
            text-transform: none; letter-spacing: normal; word-wrap: normal;
            white-space: nowrap; direction: ltr; -webkit-font-smoothing: antialiased;
            text-rendering: optimizeLegibility; -moz-osx-font-smoothing: grayscale;
            font-feature-settings: 'liga';
        }
    </style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="main-page-container">
        <div class="main-content-area">
            <%-- 검색 섹션 --%>
            <section class="search-section">
                <div class="search-intro-text">
                    헬스장 검색은 역시! 지금 바로 검색해보세요!
                </div>
                <form action="${pageContext.request.contextPath}/searchGym" method="get" class="search-form">
                    <div class="search-input-group">
                        <input type="text" name="location" placeholder="위치 (예: 강남구, 역삼동)" class="location-input">
                        <button type="submit" class="search-button">검색하기</button>
                    </div>
                    <div class="search-filters">
                        <select name="sortOrder">
                            <option value="latest">등록일순</option>
                            <option value="price_asc">가격 낮은순</option>
                            <option value="price_desc">가격 높은순</option>
                            <option value="rating_desc">만족도순</option>
                            <option value="views_desc">조회순</option>
                        </select>
                    </div>
                </form>
            </section>

            <%-- HOT 게시글 및 신규 헬스장 (Now horizontal and scrollable if needed) --%>
            <section class="gym-listings-overview">
                <div class="hot-posts-section">
                    <h3>Hot 게시글 🔥</h3>
                    <ul>
                        <c:choose>
                            <c:when test="${not empty hotPosts}">
                                <c:forEach var="post" items="${hotPosts}" varStatus="status" begin="0" end="4">
                                    <li><a href="${pageContext.request.contextPath}/post/detail?id=${post.id}">${status.count}. ${post.title}</a></li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" begin="1" end="5">
                                    <li>${i}. 등록된 Hot 게시글이 없습니다.</li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <div class="new-gyms-section">
                    <h3>이달/금년 등록 헬스장 ✨</h3>
                    <ul>
                        <c:choose>
                            <c:when test="${not empty newGyms}">
                                <c:forEach var="gym" items="${newGyms}" varStatus="status" begin="0" end="4">
                                    <li><a href="${pageContext.request.contextPath}/gym/detail?id=${gym.id}">${status.count}. ${gym.name} (${gym.locationShort})</a></li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="i" begin="1" end="5">
                                    <li>${i}. 신규 등록된 헬스장이 없습니다.</li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </section>

            <%-- 내 주변 헬스장 (Carousel - no changes to this section's functionality) --%>
            <section class="nearby-gyms-section">
                <h2>내 주변 헬스장</h2>
                <div class="gym-carousel-wrapper">
                    <button class="carousel-arrow prev-arrow" onclick="scrollNearbyGyms(-1)">
                        <span class="material-icons">chevron_left</span>
                    </button>
                    <div class="gym-cards-outer-container">
                        <div class="gym-cards-inner-container">
                            <c:choose>
                                <c:when test="${not empty nearbyGyms}">
                                    <c:forEach var="gym" items="${nearbyGyms}">
                                        <article class="gym-card">
                                            <div class="gym-card-image">
                                                <img src="${not empty gym.fileList ? gym.fileList[0].getfileUrl() : ''}"alt="${gym.gymName} 사진">
                                            </div>
                                            <div class="gym-card-info">
                                                <h4>${gym.gymName}</h4>
                                                <p>${gym.gymAddr}</p>
                                            </div>
                                        </article>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="i" begin="1" end="6">
                                        <article class="gym-card">
                                            <div class="gym-card-image">
                                                <img src="${pageContext.request.contextPath}/images/placeholder_gym.png" alt="시설 사진">
                                            </div>
                                            <div class="gym-card-info">
                                                <h4>헬스장명 ${i}</h4>
                                                <p>위치 정보</p>
                                            </div>
                                        </article>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <button class="carousel-arrow next-arrow" onclick="scrollNearbyGyms(1)">
                        <span class="material-icons">chevron_right</span>
                    </button>
                </div>
            </section>
        </div>
    </div>

    <%-- 오른쪽 하단 고정 비만도 계산기 --%>
    <aside class="obesity-calculator-aside">
        <h4>나의 비만도는? 🤔</h4>
        <form id="obesityForm">
            <div class="form-group">
                <label for="height">키(cm):</label>
                <input type="number" id="height" name="height" required>
            </div>
            <div class="form-group">
                <label for="weight">체중(kg):</label>
                <input type="number" id="weight" name="weight" required>
            </div>
            <div class="form-group">
                <label>성별:</label>
                <input type="radio" id="male" name="gender" value="male" checked> <label for="male" class="radio-label">남</label>
                <input type="radio" id="female" name="gender" value="female"> <label for="female" class="radio-label">여</label>
            </div>
            <button type="button" id="diagnoseButton" onclick="diagnoseObesity()" class="aside-button">진단하기</button>
            <button type="button" id="viewResultButton" onclick="viewObesityResult()" class="aside-button" style="display:none;">결과보기</button>
            <div id="obesityResult" style="margin-top:10px;">
                <%-- 결과가 여기에 표시될 수 있습니다. --%>
            </div>
        </form>
    </aside>


    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script>
    function diagnoseObesity() {
        const height = parseFloat(document.getElementById("height").value);
        console.log(height);
        const weight = parseFloat(document.getElementById("weight").value);
        const resultDiv = document.getElementById("obesityResult");

        if (isNaN(height) || isNaN(weight) || height <= 0 || weight <= 0) {
            resultDiv.innerHTML = `<p style="color:red;">정확한 키와 체중을 입력해주세요.</p>`;
            return;
        }

        // BMI 계산: 체중(kg) / (키(m)²)
        const heightMeter = height / 100;
        console.log(heightMeter);
        const bmi = weight / (heightMeter * heightMeter);
        console.log(bmi);
        let diagnosis = "";

        if (bmi <= 18.5) {
            diagnosis = "저체중";
        } else if (bmi <= 22.9) {
            diagnosis = "정상 체중";
        } else if (bmi <= 24.9) {
            diagnosis = "과체중";
        } else {
            diagnosis = "비만";
        }
        console.log(diagnosis);
        resultDiv.innerHTML = `
            <p><strong>BMI 지수:</strong> ${bmi.toFixed(1)}</p>
            <p><strong>진단 결과:</strong> ${diagnosis}</p>
        `;

        // 결과 보기 버튼 활성화
        //document.getElementById("viewResultButton").style.display = "inline-block";
    }

/*     function viewObesityResult() {
        const resultDiv = document.getElementById("obesityResult");
        if (resultDiv.innerHTML.trim() === "") {
            alert("먼저 진단을 진행해주세요.");
        } else {
            resultDiv.scrollIntoView({ behavior: 'smooth' });
        }
    } */

        const innerContainer = document.querySelector('.gym-cards-inner-container');
        const outerContainer = document.querySelector('.gym-cards-outer-container');
        const prevArrow = document.querySelector('.prev-arrow');
        const nextArrow = document.querySelector('.next-arrow');
        let cardWidth = 0;
        let visibleCards = 0;
        
        function updateCarouselDimensions() {
            const firstCard = innerContainer ? innerContainer.querySelector('.gym-card') : null;
            if (firstCard && outerContainer) {
                const cardStyle = window.getComputedStyle(firstCard);
                cardWidth = firstCard.offsetWidth + parseFloat(cardStyle.marginLeft) + parseFloat(cardStyle.marginRight);
                if (outerContainer.offsetWidth > 0 && cardWidth > 0) {
                   visibleCards = Math.floor(outerContainer.offsetWidth / cardWidth);
                } else {
                    visibleCards = 1;
                }
            }
            toggleArrows();
        }

        function scrollNearbyGyms(direction) {
            if (!outerContainer || cardWidth === 0) return;
            const scrollAmount = (visibleCards > 1 ? visibleCards -1 : 1) * cardWidth * direction;
            outerContainer.scrollBy({ left: scrollAmount, behavior: 'smooth' });
            setTimeout(toggleArrows, 300);
        }
        
        function toggleArrows() {
            if (!outerContainer || !innerContainer || !prevArrow || !nextArrow) return;
            const scrollLeft = outerContainer.scrollLeft;
            const maxScrollLeft = innerContainer.scrollWidth - outerContainer.clientWidth;

            prevArrow.disabled = scrollLeft <= 0;
            prevArrow.style.opacity = scrollLeft <= 0 ? "0.5" : "1";
            
            nextArrow.disabled = scrollLeft >= maxScrollLeft - 5; // Tolerance for floating point
            nextArrow.style.opacity = scrollLeft >= maxScrollLeft - 5 ? "0.5" : "1";
        }

        window.addEventListener('load', () => {
            updateCarouselDimensions();
            if(outerContainer) {
                outerContainer.addEventListener('scroll', toggleArrows);
            }
        });
        window.addEventListener('resize', updateCarouselDimensions);
    </script>
</body>
</html>
