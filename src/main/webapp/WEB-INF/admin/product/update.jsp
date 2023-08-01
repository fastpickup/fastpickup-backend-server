<!--
/*
* Date : 2023.08.01
* Author : 조상희
* E-mail : jo_sh_1028@naver.com
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
<h3>상품 수정</h3>
<form name="frm" method="post">
	<div class="form_content">
		<div class="bg-light rounded h-100 p-4">
			<label for="sno" class="form-label">가맹점 번호</label>
			<input type="text" id="sno" name="sno" class="form-control" value="${productStore.sno}" readonly required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="categoryName" class="form-label">카테고리 명</label>
			<input type="text" id="categoryName" name="categoryName" class="form-control" value="${productRead.categoryName}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="productName" class="form-label">상품 이름</label>
			<input type="text" id="productName" name="productName" class="form-control" value="${productRead.productName}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="productContent" class="form-label">상품 상세 정보</label>
			<input type="text" id="productContent" name="productContent" class="form-control" value="${productRead.productContent}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="productPrice" class="form-label">상품 가격</label>
			<input type="number" id="productPrice" name="productPrice" class="form-control" value="${productRead.productPrice}" required>
		</div>
		<div class="bg-light rounded h-100 p-4">
			<label for="productFile" class="form-label">상품 이미지</label>
			<input type="file" name="upload" multiple class="form-control uploadInput" id="productFile">
		</div>
		<div class="bg-light rounded h-100 p-4">
			<input type="checkbox" name="isRecommend" id="isRecommend" value="${productRead.isRecommend}" <c:if test="${productRead.isRecommend == 999999999}">checked</c:if>/>
			<label for="isRecommend" class="form-label">추천상품 여부</label>
		</div>
		<div class="uploadHidden"></div>
	</div>
</form>
<div class="uploadWrap p-4">
	<ul class="uploadUL">
		<c:forEach items="${productRead.fileNames}" var="product" varStatus="status">
			<li data-originName="${product}">
				<a href="http://localhost/${product}" target="_blank">
					<img src="http://localhost/s_${product}"/>
				</a>
				<p>${fn:substring(product,37,fn:length(product))}</p>
				<button class="btn btn-danger" onclick="javascript:removeFile(event, '${product}')">X</button>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="button_wrap p-4">
	<a href="/admin/product/read/${productRead.pno}" class="btn btn-outline-dark">상품 상세로</a>
	<button type="button" class="btn btn-dark btnAdd">상품 수정</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/js/file.js"></script>
<%@ include file="../include/footer.jsp" %>
<%-- Start Javascript --%>
<script>
  const uploadInput = document.querySelector(".uploadInput")
  const uploadUL = document.querySelector(".uploadUL")
  const uploadHidden = document.querySelector(".uploadHidden")
  const btnAdd = document.querySelector(".btnAdd")

  //파일 업로드
  uploadInput.addEventListener("change", e => {

    //file 없으면 리턴
    if(!uploadInput.files || uploadInput.files.length === 0) {
      return
    }

    //form data
    const formData = new FormData()

    //formData에 파일 넣어주기
    for(let i = 0; i < uploadInput.files.length; i++) {
      formData.append("files", uploadInput.files[i])
    }
    console.dir(formData)

    //http header 타입 지정
    const header = {headers: {"Content-Type": "multipart/form-data"}}

    //파일 업로드 axios 호출
    axios.post("http://192.168.0.64:8080/api/files/upload", formData, header).then(res => {
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
    for(let liObj of liArr) {
      //console.log(liObj)
      const originName = liObj.getAttribute("data-originname")
      console.log(originName)
      //console.log("---------------------------------")
      str += '<input type="hidden" name="fileNames" value="'+originName+'">'
    }
    uploadHidden.innerHTML += str

	  //console.log(isRecommend.checked)
	  if(isRecommend.checked === false){
      uploadHidden.innerHTML += '<input type="hidden" name="isRecommend" value="0">'
	  }

    frm.submit()
  }, false)
</script>
</body>

</html>