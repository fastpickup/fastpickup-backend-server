<!--
/*
 * Date   : 2023.07.27
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
			<h1>Member Update Page</h1>
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-body">
								<form action="/admin/store/update" method="post">
									<div class="bg-light rounded h-100 p-4">
										<label for="sno" class="form-label">가맹점 번호</label>
										<input type="text" name="sno" class="form-control" id="sno"
											value="${listStore.sno}">
									</div>
									<div class="bg-light rounded h-100 p-4">
										<label for="storeName" class="form-label">가맹점 이름</label>
										<input type="text" name="storeName" class="form-control" id="storeName"
											value="${listStore.storeName}">
									</div>
									<div class="bg-light rounded h-100 p-4">
										<label for="email" class="form-label">가맹점 이메일</label>
										<input type="email" name="email" class="form-control" id="email"
											value="${listStore.email}">
									</div>
									<div class="bg-light rounded h-100 p-4">
										<label for="storeNumber" class="form-label">가맹점 사업자 번호</label>
										<input type="text" name="storeNumber" class="form-control" id="storeNumber"
											value="${listStore.storeNumber}">
									</div>
									<div class="bg-light rounded h-100 p-4">
										<label for="storeAddress" class="form-label">가맹점 주소</label>
										<input type="text" name="storeAddress" class="form-control" id="storeAddress"
											value="${listStore.storeAddress}">
									</div>
									<div class="bg-light rounded h-100 p-4">
										<div class="col-sm-9 offset-sm-3">
											<button type="submit" class="btn btn-primary">가맹점 정보 업데이트</button>
											<a href="/admin/store/read/${listStore.sno}"
												class="btn btn-primary text-white">가맹점 상세 돌아가기</a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="../include/footer.jsp" %>

	</body>

	</html>