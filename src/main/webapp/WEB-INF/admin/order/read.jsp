<!--
/*
* Date : 2023.07.28
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FastPickup</title>
</head>

<body>
<%--
	가맹점 전화번호,
	주문 날짜,
	주문자 전화번호 필요!!!!
--%>
<%@ include file="../include/header.jsp" %>
<div class="order_wrap">
	<div class="order_title">
		<h3>주문 상세</h3>
		<div class="order_state">
			<p class="p-2 px-4 bg-warning text-dark rounded-3">${listOrder.orderStatus}</p>
			<div class="order_state_button">
				<form id="order-form" action="/admin/order/updatehistory" method="post">
					<input type="hidden" name="ono" class="form-control" id="ono" value="${listOrder.ono}">
					<input type="hidden" name="orderHistory" class="form-control" id="orderHistory" value="${listOrder.orderHistory}">
					<input type="hidden" id="status" name="orderStatus">
					<div class="button_wrap">
						<button type="button" class="btn btn-outline-dark" onclick="submitForm('주문접수')">상품 접수 확인</button>
						<button type="button" class="btn btn-dark" onclick="submitForm('주문준비완료')">상품 준비 완료</button>
						<button type="button" class="btn btn-primary" onclick="submitForm('반려')">상품 반려</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="order_store">
		<h5>${listOrder.storeName} <span style="font-size:1rem;font-weight:400;">가맹점 번호: [${listOrder.sno}]</span></h5>
		<ul>
			<li><span>Email</span> ${listOrder.email}</li>
			<li><span>전화번호</span> <%--${listOrder.phone}--%></li>
			<li><span>주소</span> ${listOrder.storeAddress}</li>
		</ul>
	</div>
	<div class="order_store order_store2">
		<h5>주문 정보 (No: ${listOrder.ono})</h5>
		<dl>
			<dt><img src="http://192.168.0.64/${listOrder.fileName[0]}" width="250"/></dt>
			<dd>
				<ul>
					<li><span>주문자 전화번호</span> <%--${listOrder.phone}--%></li>
					<li><span>상품 번호</span> ${listOrder.pno}</li>
					<li><span>상품명</span> <strong>${listOrder.productName}</strong></li>
					<li><span>주문 수량</span> ${listOrder.orderCount}</li>
					<li><span>상품 가격</span> <fmt:formatNumber type="currency" value="${listOrder.productPrice}" pattern="###,### 원" /></li>
					<li><span>총 금액</span> <strong><fmt:formatNumber type="currency" value="${listOrder.productPrice * listOrder.orderCount}" pattern="###,### 원" /></strong></li>
				</ul>
			</dd>
		</dl>
	</div>
</div>
<div class="button_wrap">
	<a href="/admin/order/list" class="btn btn-outline-dark">목록으로</a>
</div>
<!-- Update Complete Message Start -->
<div class="modal alertModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">${message}</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
				        data-dismiss="modal">닫기
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Update Complete Message End -->
<%@ include file="../include/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
  const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
  let message = "${message}";
  if (message !== "") {
    alertModal.show();
  }

  function submitForm(value) {
    document.getElementById('status').value = value;
    document.getElementById('order-form').submit();
  }

  setTimeout(function () {
    alertModal.hide();
  }, 1500);
</script>
</body>

</html>