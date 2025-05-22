<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL 라이브러리 선언 -->     <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/css/gym/gymTicket.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
    <main>
	  <div class="wrap">
	    <div class="content">
	      <div class="page-title">회원권 구매</div>
	      <section class="section bg-section">
	        <div class="membership-container">
	
	          <form action="/membership/paymentBefore" method="post">
	            <div class="membership-option">
	            	<input type = "hidden" name= "gymId" value = "${ticket.gymId }">
	              <label class="membership-radio">
	                <input type="radio" name="membership" value="oneMonth" checked> &nbsp;1개월
	              </label>
	              <div class="membership-price">
	                <del>700,000원</del>
	                <span class="discount">14%</span>
	                <div class="final-price">${ticket.oneMonth}원</div>
	              </div>
	            </div>
	
	            <div class="membership-option">
	              <label class="membership-radio">
	                <input type="radio" name="membership" value="threeMonth"> &nbsp;3개월
	              </label>
	              <div class="membership-price">
	                <del>700,000원</del>
	                <span class="discount">14%</span>
	                <div class="final-price">${ticket.threeMonth}원</div>
	              </div>
	            </div>
	
	            <div class="membership-option">
	              <label class="membership-radio">
	                <input type="radio" name="membership" value="sixMonths"> &nbsp;6개월
	              </label>
	              <div class="membership-price">
	                <del>700,000원</del>
	                <span class="discount">14%</span>
	                <div class="final-price">${ticket.sixMonth}원</div>
	              </div>
	            </div>
	
	            <div class="membership-option">
	              <label class="membership-radio">
	                <input type="radio" name="membership" value="oneYear"> &nbsp;12개월
	              </label>
	              <div class="membership-price">
	                <del>700,000원</del>
	                <span class="discount">14%</span>
	                <div class="final-price">${ticket.oneYear}원</div>
	              </div>
	            </div>
	
	            <div class="membership-option">
	              <label class="membership-radio">
	                <input type="radio" name="membership" value="oneDay"> &nbsp;일일권
	              </label>
	              <div class="membership-price">
	                <del>700,000원</del>
	                <span class="discount">14%</span>
	                <div class="final-price">${ticket.oneDay}원</div>
	              </div>
	            </div>
	
	            <button type="submit" class="purchase-button">회원권 구매하기</button>
	          </form>
	
	        </div>
	      </section>
	    </div>
	  </div>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
</body>
</html>