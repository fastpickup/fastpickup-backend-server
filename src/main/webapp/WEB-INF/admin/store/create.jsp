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
			<h3>가맹점 생성</h3>
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
					<div class="bg-light rounded h-100 p-4">
						<label for="storePhone" class="form-label">가맹점 전화번호</label>
						<input type="text" id="storePhone" name="storePhone" class="form-control" required
							placeholder="EX : 010-xxxx-xxxx">
					</div>
					<div class="bg-light rounded h-100 p-4">
						<label for="storeFile" class="form-label">상품 이미지</label>
						<input type="file" name="upload" multiple class="form-control uploadInput" id="productFile">
					</div>
					<div class="uploadHidden"></div>
				</div>
				<div class="uploadWrap p-4">
					<ul class="uploadUL"></ul>
				</div>
				<div class="button_wrap p-4">
					<a href="/admin/store/list" class="btn btn-outline-dark btnSignin">목록으로</a>
					<button type="submit" class="btn btn-dark btnAdd">가맹점 생성</button>
				</div>
			</form>
			<%@ include file="../include/footer.jsp" %>
				<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
				<script src="/js/file.js"></script>
				<!-- Script Start -->
				<script>
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

					//등록버튼 클릭
					btnAdd.addEventListener("click", e => {
						//console.log(frm.title.value);
						//li 전부 셀렉트
						const liArr = uploadUL.querySelectorAll("li")

						//li개수만큼 for문 돌려서 input hidden 추가
						let str = ""
						for (let liObj of liArr) {
							//console.log(liObj)
							const originName = liObj.getAttribute("data-originname")
							console.log(originName)
							//console.log("---------------------------------")
							str += '<input type="hidden" name="fileNames" value="' + originName + '">'
						}

						uploadHidden.innerHTML += str
						frm.submit()
					}, false)

				</script>
	</body>

	</html>