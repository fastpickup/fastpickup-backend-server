<!--
/*
* Date : 2023.07.31
* Author : 조상희
* E-mail : jo_sh_1028@naver.com
*/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link rel="shortcut icon" href="/imgs/favicon.ico" type="image/x-icon">
	<title>FastPickup</title>
</head>

<body>
<%@ include file="../include/header.jsp" %>
<div class="order_wrap product_read_wrap">
	<div class="order_title">
		<h3>상품 상세</h3>
	</div>
	<%--{productStore}
	${productRead}--%>
	<div class="order_store">
		<h5>${productStore.storeName} <span style="font-size:1rem;font-weight:400;">가맹점 번호: [${productStore.sno}]</span></h5>
		<ul>
			<li><span>가맹점 주소</span> ${productStore.storeAddress}</li>
			<li><span>가맹점 전화번호</span> ${productStore.storePhone}</li>
		</ul>
	</div>
	<div class="order_store order_store2">
		<h5>상품 정보 (No: ${productRead.pno})</h5>
		<dl>
			<dt>
				<div class="view_image">
					<img src="http://192.168.0.64/${productRead.fileNames[0]}" width="500"/>
				</div>
				<ul class="image_list">
					<c:forEach items="${productRead.fileNames}" var="productImage" varStatus="status">
						<li><img src="http://192.168.0.64/s_${productImage}" width="80"/></li>
					</c:forEach>
				</ul>
			</dt>
			<dd>
				<ul>
					<li><span>카테고리명</span> ${productRead.categoryName}</li>
					<li><span>상품명</span> <h4>${productRead.productName}</h4></li>
					<li><span>상품 상세</span> ${productRead.productContent}</li>
					<li><span>상품 가격</span> <fmt:formatNumber type="currency" value="${productRead.productPrice}" pattern="###,### 원" /></li>
					<li>
						<span>등록일</span>
						<fmt:parseDate value="${productRead.registDate}" pattern="yyyy-MM-dd'T'HH:mm" var="productRegistDate" type="both" />
						<fmt:formatDate value="${productRegistDate}" pattern="yyyy-MM-dd HH:mm" />
					</li>
					<li>
						<span>수정일</span>
						<fmt:parseDate value="${productRead.registDate}" pattern="yyyy-MM-dd'T'HH:mm" var="productUpdateDate" type="both" />
						<fmt:formatDate value="${productUpdateDate}" pattern="yyyy-MM-dd HH:mm" />
					</li>
					<li><span>좋아요</span> ${productRead.likeCount}</li>
					<li><span>추천상품</span> ${productRead.isRecommend}</li>
				</ul>
			</dd>
		</dl>
	</div>
</div>
<div class="button_wrap">
	<a href="/admin/store/read/${productRead.sno}" class="btn btn-outline-dark">목록으로</a>
</div>
<!-- Modal Start -->
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
<!-- Modal End -->
<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>
  const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
  const viewImage = document.querySelector(".view_image img")
  const imageList = document.querySelector(".image_list")
  let message = "${message}";
  if (message !== "") {
    alertModal.show();
  }
  setTimeout(function () {
    alertModal.hide();
  }, 1500);

  imageList.addEventListener("mouseover", (e) => {
    //target
	  const target = e.target;
    //console.log(target.tagName)

	  //IMG가 아니면 return
	  if(target.tagName !== "IMG"){
      return
	  }

    //이미지명 추출
    const src = e.target.getAttribute("src")
	  const imageName = src.split("/s_")
	  //console.log(imageName[1])

	  //이미지 경로 수정
	  viewImage.setAttribute("src", "http://192.168.0.64/" + imageName[1])
  })

</script>
</body>

</html>