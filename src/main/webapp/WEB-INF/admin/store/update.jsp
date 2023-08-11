<!--
/*
* Date : 2023.07.27
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<h3>가맹점 정보 수정</h3>
			<form action="/admin/store/update" method="post">
				<div class="form_content">
					<div class="bg-light rounded h-100 p-4">
						<label for="sno" class="form-label">가맹점 번호</label>
						<input type="text" name="sno" class="form-control" id="sno" value="${listStore.sno}">
					</div>
					<div class="bg-light rounded h-100 p-4">
						<label for="storeName" class="form-label">가맹점 이름</label>
						<input type="text" name="storeName" class="form-control" id="storeName"
							value="${listStore.storeName}">
					</div>
					<div class="bg-light rounded h-100 p-4">
						<label for="email" class="form-label">가맹점 이메일</label>
						<input type="email" name="email" class="form-control" id="email" value="${listStore.email}">
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
						<label for="storePhone" class="form-label">가맹점 전화번호</label>
						<input type="text" name="storePhone" class="form-control" id="storePhone"
							value="${listStore.storePhone}">
					</div>
					<div class="bg-light rounded h-100 p-4">
						<label for="storeFile" class="form-label">상품 이미지</label>
						<input type="file" name="upload" multiple class="form-control uploadInput" id="productFile">
					</div>
					<div class="uploadHidden"></div>
				</div>
				<div class="uploadWrap p-4">
					<ul class="uploadUL">
						<c:forEach items="${listStore.fileName}" var="product" varStatus="status">
							<li data-originName="${product}">
								<a href="http://192.168.0.64/${product}" target="_blank">
									<img src="http://192.168.0.64/s_${product}" />
								</a>
								<p>${fn:substring(product,37,fn:length(product))}</p>
								<button class="btn btn-danger"
									onclick="javascript:removeFile(event, '${product}')">X</button>
							</li>
						</c:forEach>
					</ul>
				</div>
				<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
				<script src="/js/file.js"></script>
				<div class="button_wrap p-4">
					<a href="/admin/store/read/${listStore.sno}" class="btn btn-outline-dark">조회 페이지</a>
					<button type="submit" class="btn btn-dark">정보 수정</button>
				</div>
			</form>
			<%@ include file="../include/footer.jsp" %>
				<script>
					const sno = ${ listStore.sno }
					const uploadInput = document.querySelector(".uploadInput")
					const uploadUL = document.querySelector(".uploadUL")
					const uploadHidden = document.querySelector(".uploadHidden")
					const btnAdd = document.querySelector(".btnAdd")

					//파일 업로드
					uploadInput.addEventListener("change", e => {

						//file 없으면 리턴
						if (!uploadInput.files || uploadInput.files.length === 0) {
							return
						}

						//form data
						const formData = new FormData()

						//formData에 파일 넣어주기
						for (let i = 0; i < uploadInput.files.length; i++) {
							formData.append("files", uploadInput.files[i])
						}
						console.dir(formData)

						//http header 타입 지정
						const header = { headers: { "Content-Type": "multipart/form-data" } }

						//파일 업로드 axios 호출
						axios.post("http://localhost:8080/api/files/upload", formData, header).then(res => {
							const result = res.data
							console.log(result)
							showProducts(result)
						})
					})

					//수정버튼 클릭
					btnAdd.addEventListener("click", e => {
						//console.log(frm.title.value);
						const isRecommend = document.querySelector("input[name=isRecommend]")
						//li 전부 셀렉트
						const liArr = uploadUL.querySelectorAll("li")

						//li개수만큼 for문 돌려서 input hidden 추가
						let str = ""
						for (let liObj of liArr) {
							//console.log(liObj)
							const originName = liObj.getAttribute("data-originname")
							console.log(originName)
							//console.log("---------------------------------")
							str += '<input type="hidden" name="fileName" value="' + originName + '">'
						}
						uploadHidden.innerHTML += str

						//console.log(isRecommend.checked)
						if (isRecommend.checked === false) {
							uploadHidden.innerHTML += '<input type="hidden" name="isRecommend" value="0">'
						}

						frm.submit()
					}, false)

					//삭제 modal show
					btnDelete.addEventListener("click", e => {
						deleteModal.show()
					}, false)

				</script>
	</body>

	</html>