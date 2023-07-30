<!--
/*
* Date : 2023.07.27
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="utf-8">
	<title>FastPickup</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<meta content="" name="keywords">
	<meta content="" name="description">

	<!-- Favicon -->
	<link href="/img/favicon.ico" rel="icon">

	<!-- Google Web Fonts -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

	<!-- Icon Font Stylesheet -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">


	<!-- Customized Bootstrap Stylesheet -->
	<link href="/css/bootstrap.min.css" rel="stylesheet">

	<!-- Template Stylesheet -->
	<link href="/css/style.css" rel="stylesheet">
</head>

<body style="background: #fff;">
<%--<%@ include file="include/header.jsp" %>--%>
<div class="sigin_wrap">
	<div class="col-sm-5 col-xl-5 sigin_content">
		<h1><img src="/imgs/login_logo.png" alt="logo"/></h1>
		<div class="bg-body rounded h-100 p-4">
			<h3>로그인</h3>
			<form action="/admin/index" method="post">
				<div class="mb-3">
					<label for="username" class="form-label">Email address</label>
					<input type="email" class="form-control" id="username" name="username"
					       aria-describedby="username">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password</label>
					<input type="password" class="form-control" id="password" name="password">
				</div>
				<!-- Remember Me checkbox -->
				<div>
					<input type="checkbox" name="remember-me">
					이 컴퓨터에 쿠키를 저장하실겁니까 ?
				</div>
				<div class="button_wrap">
					<button type="submit" class="btn btn-dark">로그인</button>
					<a href="http://localhost:8080/oauth2/authorization/kakao" class="btn btn-kakao">
						<img src="/imgs/kakao.png">
						Kakao 로그인
					</a>
				</div>
			</form>
			<div class="button_wrap button_wrap2">
				<a href="/admin/member/create" class="btn btn-outline-dark">회원 가입</a>
				<a href="/admin/member/createstore" class="btn btn-outline-dark">가맹점 가입</a>
			</div>
		</div>
	</div>
</div>
<%--<%@ include file="include/footer.jsp" %>--%>
<!-- Update Complete Message Start -->
<div class="modal alertModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">${message}</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
</div>
<!-- Update Complete Message End -->

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

<!-- Template Javascript -->
<script src="/js/main.js"></script>

<script>
  const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
  let message = "${message}";
  if (message !== "") {
    alertModal.show();
  }
  setTimeout(function () {
    alertModal.hide();
  }, 1500);
</script>
</body>

</html>