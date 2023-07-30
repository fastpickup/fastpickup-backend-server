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
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FastPickup</title>
</head>

<body>
<%@ include file="../include/header.jsp" %>
<form action="/admin/member/create" method="post">
	<h3>회원 생성</h3>
	<div class="form_content">
		<div class="bg-light rounded h-100 p-4">
			<label for="memberName" class="form-label">이름</label>
			<input type="text" id="memberName" name="memberName" class="form-control" required placeholder="이름을 입력 해주세요">
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="email" class="form-label">Email</label>
			<input type="email" id="email" name="email" class="form-control" required placeholder="Email을 입력 해주세요">
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="memberPw" class="form-label">비밀번호</label>
			<input type="password" id="memberPw" name="memberPw" class="form-control" required
			       placeholder="비밀번호를 입력 해주세요">
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="memberPhone" class="form-label">전화번호</label>
			<input type="text" id="memberPhone" name="memberPhone" class="form-control" required placeholder="010-xxxx-xxxx">
		</div>
	</div>
	<div class="button_wrap p-4">
		<a href="/admin/index" class="btn btn-outline-dark btnSignin">목록으로</a>
		<button type="submit" class="btn btn-dark">회원 생성</button>
	</div>
</form>
<%@ include file="../include/footer.jsp" %>
</body>

</html>