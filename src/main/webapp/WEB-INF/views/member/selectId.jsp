<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/default.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 조회 결과</title>
<style>
	.section {
	width : 50%;
	height : 50%;
	margin : 0 auto;
	}
</style>
</head>
<body>
	<div class="login-container">
	<div class="wrap">
		<main class="content srch-info-container">
			<section class="section">
			<form action="/">
				<div class="srch-info-wrap">
					<div class="input-title">
					<h1>아이디 찾기 조회 결과</h1>
					</div>
				</div>				
				<div class="srch-info-wrap">
					<div class="input-item">
					<h3>회원님의 아이디는 ${selectId}</h3>
					</div>
				</div>
				<div style="text-align: center;">
					<button type="submit" style="height:30px;"> 확인 </button>
				</div>
				</form>
			</section>
		</main>
	</div>
	</div>
</body>
</html>