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
<h3>가맹정 생성</h3>
<form action="/admin/store/create" method="post">
	<div class="form_content">
		<div class="bg-light rounded h-100 p-4">
			<label for="storeName" class="form-label">가맹점 이름</label>
			<input type="text" id="storeName" name="storeName" class="form-control" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="storeNumber" class="form-label">사업자 번호</label>
			<input type="text" id="storeNumber" name="storeNumber" class="form-control" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="storeAddress" class="form-label">주소</label>
			<input type="text" id="storeAddress" name="storeAddress" class="form-control" required>
		</div>
	</div>
	<div class="button_wrap p-4">
		<a href="/admin/store/list" class="btn btn-outline-dark btnSignin">목록으로</a>
		<button type="submit" class="btn btn-dark">가맹점 생성</button>
	</div>
</form>
<%@ include file="../include/footer.jsp" %>
</body>

</html>