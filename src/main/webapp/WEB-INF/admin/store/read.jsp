<!--
/*
* Date : 2023.07.27
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link rel="shortcut icon" href="/imgs/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<title>FastPickup</title>
</head>

<body>
<%@ include file="../include/header.jsp" %>
<div class="row">
	<div class="col-md-12">
		<h3>가맹점 상세</h3>
		<div class="card">
			<div class="bg-light rounded h-100 p-4">
				<form onsubmit="onSubmitForm(event)" method="get">
					<dl class="detail_content">
						<dt>가맹점 번호</dt>
						<dd class="sno">${listStore.sno}</dd>
						<dt>가맹점명</dt>
						<dd class="sno">${listStore.storeName}</dd>
						<dt>가맹점 이메일</dt>
						<dd class="email">${listStore.email}</dd>
						<dt>사업자 번호</dt>
						<dd>${listStore.storeNumber}</dd>
						<dt>가맹점 주소</dt>
						<dd>${listStore.storeAddress}</dd>
						<dt>가맹점 전화번호</dt>
						<dd>${listStore.storePhone}</dd>
					</dl>
				</form>
			</div>
		</div>
		<!-- Member Delete & Member Signout & Member Update & Board List Page -->
		<form onsubmit="return false;" action="/admin/store/delete" method="post">
			<div class="button_wrap mt-4">
				<a href="/admin/store/list" class="btn btn-outline-dark">목록으로</a>
				<button type="submit" class="btn btn-primary btn-delete" onclick="confirmDelete(event)">가맹점 퇴출</button>
				<a href="/admin/store/update/${listStore.sno}" class="btn btn-dark">가맹점 수정</a>
			</div>
		</form>
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
		<!-- Update Complete Message End -->
		<!-- Delete Confrim Message Modal -->
		<div class="modal deleteModal" tabindex="-1" role="dialog">
			<form method="post" class="actionForm">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-body">정말 삭제 하시겠습니까?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary btnDeleteModal" data-bs-dismiss="modal">Confirm</button>
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- Delete Confrim Message Modal -->
	</div>
</div>
<%--<div class="table-responsive">--%>
<%--	<table class="table">--%>
<%--		<thead>--%>
<%--		<tr>--%>
<%--			<th scope="col">일별 매출 통계</th>--%>
<%--			<th scope="col">상품 이름</th>--%>
<%--			<th scope="col">총 판매액</th>--%>
<%--		</tr>--%>
<%--		</thead>--%>
<%--		<tbody>--%>
<%--		<c:forEach var="sales" items="${salesDay}">--%>
<%--			<tr>--%>
<%--				<td>${sales.registDate}</td>--%>
<%--				<td>${sales.productName}</td>--%>
<%--				<td>${sales.totalSales}</td>--%>
<%--			</tr>--%>
<%--		</c:forEach>--%>
<%--		</tbody>--%>
<%--	</table>--%>
<%--</div>--%>
<%--<div class="table-responsive">--%>
<%--	<table class="table">--%>
<%--		<thead>--%>
<%--		<tr>--%>
<%--			<th scope="col">월별 매출 통계</th>--%>
<%--			<th scope="col">상품 이름</th>--%>
<%--			<th scope="col">총 판매액</th>--%>
<%--		</tr>--%>
<%--		</thead>--%>
<%--		<tbody>--%>
<%--		<c:forEach var="sales" items="${salesMonth}">--%>
<%--			<tr>--%>
<%--				<td>${sales.registMonth}</td>--%>
<%--				<td>${sales.productName}</td>--%>
<%--				<td>${sales.totalSales}</td>--%>
<%--			</tr>--%>
<%--		</c:forEach>--%>
<%--		</tbody>--%>
<%--	</table>--%>
<%--</div>--%>
<div style="display: flex;">
	<div id="curve_chart" style="width: 50%; height: 500px"></div>
	<div id="column_chart_month" style="width: 50%; height: 500px;"></div>
</div>
<div class="col-12">
<%--	${listProduct}--%>
<%--	${pageRequestDTO}--%>
	<h4 class="my-3">상품 목록</h4>
	<div class="bg-light rounded h-100">
		<div class="table-responsive">
			<table class="table">
				<thead>
				<tr>
					<th scope="col">상품 번호</th>
					<th scope="col">상품 이름</th>
					<th scope="col">상품 가격</th>
					<%--<th scope="col">카테고리명</th>--%>
					<th scope="col">상품 등록일</th>
					<th scope="col">조회수</th>
					<th scope="col">좋아요</th>
					<th scope="col">추천상품</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${listProduct.list}" var="product" varStatus="status">
					<tr<c:if test="${product.recStatus == 1}"> class="product_list_active"</c:if>>
						<td><a href="/admin/product/read/${product.pno}">${product.pno}</a></td>
						<td>
							<a href="/admin/product/read/${product.pno}">
								<img src="http://192.168.0.64/s_${product.fileName}">
									${product.productName}
							</a>
						</td>
						<td><fmt:formatNumber type="currency" value="${product.productPrice}" pattern="###,### 원" /></td>
						<%--<td>${product.categoryName}</td>--%>
						<td>
							<fmt:parseDate value="${product.registDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
							<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd" />
						</td>
						<td>${product.viewCount}</td>
						<td>${product.likeCount}</td>
						<td>
							<c:if test="${product.recStatus == 1}">추천</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="button_wrap">
			<a href="/admin/product/create/${listStore.sno}" class="btn btn-dark">상품 등록</a>
		</div>
	</div>
	<!-- Paging Start -->
	<div class="btn-toolbar" role="toolbar" style="justify-content: center;">
		<ul class="btn-group me-2 paging" role="group" aria-label="First group">
			<c:if test="${listProduct.prevBtn}">
				<li><a href="${listProduct.startNum - 1}" class="btn btn-group btn-prev">이전</a></li>
			</c:if>

			<c:forEach var="i" begin="${listProduct.startNum}" end="${listProduct.endNum}">
				<li class="${listProduct.page == i ? 'active' : ''}">
					<a href="${i}" class="btn btn-group">${i}</a>
				</li>
			</c:forEach>

			<c:if test="${listProduct.nextBtn}">
				<li><a href="${listProduct.endNum + 1}" class="btn btn-group btn-next">다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- Paging End -->
	<form action="/admin/store/read/${listStore.sno}" method="get" class="pagingForm">
		<input type="hidden" name="page" value="${pageRequestDTO.page}">
		<input type="hidden" name="size" value="${pageRequestDTO.size}">
	</form>

	<h4 class="my-3">상품 리뷰</h4>
	<div class="bg-light rounded h-100">
		<div class="table-responsive">
			<table class="reviewTable">
				<thead>
				<tr>
					<th scope="col">리뷰 번호</th>
					<th scope="col">작성자</th>
					<th scope="col">리뷰 제목</th>
					<th scope="col">리뷰 일자</th>
				</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
		<div class="button_wrap">
			<a href="/admin/review/create/${listStore.sno}" class="btn btn-dark">리뷰 등록</a>
		</div>
	</div>

</div>
<%@ include file="../include/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
  const paging = document.querySelector(".paging")
  const pagingForm = document.querySelector(".pagingForm")
  const pageInput = pagingForm.querySelector("input[name=page]")

  paging.addEventListener("click", (e) => {
    //이벤트 막기
    e.preventDefault()
    e.stopPropagation()

    //target 찾기
    const target = e.target
    //console.log(target.tagName)

    //A태그가 아니면 return
    if (target.tagName !== "A") {
      return
    }

    //page번호 설정
    const pageNum = target.getAttribute("href")
    //console.log(pageNum)

    //input에 page변경 넘겨주기
    pageInput.value = pageNum
    //actionForm action 변경
    pagingForm.setAttribute("action", `/admin/store/read/${listStore.sno}`)
    //submit
    pagingForm.submit()
  })
  // '삭제' 버튼 클릭 시 모달 띄우기
  document.querySelector('.btn-delete').addEventListener('click', function (event) {
    event.preventDefault();
    // 모달 보이기
    $('.deleteModal').modal('show');
  });

  // '확인' 버튼 클릭 시 폼 제출하기
  document.querySelector('.btnDeleteModal').addEventListener('click', function () {
    var sno = document.querySelector('.sno').textContent;
    var form = document.querySelector('form[action="/admin/store/delete"]');
    form.action = '/admin/store/delete/' + sno;
    form.submit();
  });
  const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
  let message = "${message}";
  if (message !== "") {
    alertModal.show();
  }
  setTimeout(function () {
    alertModal.hide();
  }, 1500);

  // Goggle Chart Start
  google.charts.load('current', { 'packages': ['corechart'] });
  google.charts.setOnLoadCallback(drawChartDay);

  function drawChartDay() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '날짜');
    data.addColumn('number', '매출');
    <c:forEach var="sales" items="${salesDay}">
    data.addRow(['${sales.registDate}', ${sales.totalSales}]);
    </c:forEach>

    var options = {
      title: '일별 매출 통계',
      curveType: 'function',
      legend: { position: 'bottom' }
    };

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    chart.draw(data, options);
  }
  // Goggle Chart Start
  google.charts.load('current', { 'packages': ['corechart'] });
  google.charts.setOnLoadCallback(drawChartMonth);



  function drawChartMonth() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '월');
    data.addColumn('number', '매출');
    <c:forEach var="sales" items="${salesMonth}">
    data.addRow(['${sales.registMonth}', ${sales.totalSales}]);
    </c:forEach>

    var options = {
      title: '월별 매출 통계',
      bars: 'vertical', // 막대 방향 (가로: 'horizontal', 세로: 'vertical')
      legend: { position: 'bottom' }
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_month'));
    chart.draw(data, options);
  }
</script>

</body>

</html>