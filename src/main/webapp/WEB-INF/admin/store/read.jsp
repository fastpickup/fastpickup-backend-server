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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <dt>가맹점 이미지</dt>
            <dt><img src="http://192.168.0.64/${listStore.fileName[0]}" width="250"/></dt>
					</dl>
				</form>
			</div>
		</div>
		<!-- Member Delete & Member Signout & Member Update & Board List Page -->
		<form onsubmit="return false;" action="/admin/store/delete" method="post">
			<div class="button_wrap mt-4">
        <sec:authorize access="hasAnyRole('ROLE_STORE')">
        <a href="/admin/store/list/${sno}" class="btn btn-outline-dark">목록으로</a>
        </sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
        <a href="/admin/store/list" class="btn btn-outline-dark">목록으로</a>
				<button type="submit" class="btn btn-primary btn-delete" onclick="confirmDelete(event)">가맹점 퇴출</button>
				</sec:authorize>
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
				<tbody class="storeProductList">
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div class="button_wrap">
			<a href="/admin/product/create/${listStore.sno}" class="btn btn-dark">상품 등록</a>
		</div>
		</sec:authorize>
	</div>
	<!-- Paging Start -->
	<div class="btn-toolbar" role="toolbar" style="justify-content: center;">
		<ul class="btn-group me-2 paging storeProductPaging" role="group" aria-label="First group">
		</ul>
	</div>
	<!-- Paging End -->
<%--	<form action="/admin/store/read/${listStore.sno}" method="get" class="pagingForm">--%>
<%--		<input type="hidden" name="page" value="${pageRequestDTO.page}">--%>
<%--		<input type="hidden" name="size" value="${pageRequestDTO.size}">--%>
<%--	</form>--%>


<h4 class="my-3">리뷰 목록</h4>
<div class="bg-light rounded h-100">
	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">리뷰 번호</th>
				<th scope="col">작성자</th>
				<th scope="col">리뷰 제목</th>
				<th scope="col">리뷰 일자</th>
			</tr>
			</thead>
			<tbody class="storeReviewList">
			</tbody>
		</table>
	</div>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<div class="button_wrap">
		<a href="/admin/review/create/${listStore.sno}" class="btn btn-dark">리뷰 등록</a>
	</div>
	</sec:authorize>
</div>
<!-- Paging Start -->
<div class="btn-toolbar" role="toolbar" style="justify-content: center;">
	<ul class="btn-group me-2 paging storeReviewPaging" role="group" aria-label="First group">
	</ul>
</div>
<!-- Paging End -->
</div>
<%@ include file="../include/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  const sno = ${listStore.sno}

  // 상품 리스트
  const storeProductPaging = document.querySelector(".storeProductPaging")
  const storeProductList = document.querySelector(".storeProductList")

  // 리뷰 리스트
  const storeReviewList = document.querySelector(".storeReviewList")
  const storeReviewPaging = document.querySelector(".storeReviewPaging")

  //상품 리스트 가져오기
  const getList = async(page = 1) => {
    const res = await axios.get("http://localhost:8080/admin/product/"+sno+"/list?page="+page)
    return res.data
  }

  //상품 리스트 출력
  const productListDefault = (page) => {
    getList(page).then(arr => {
      let productListStr = ""
      let productPagingStr = ""
      //Product List 출력
      for (let i = 0; i < arr.list.length; i++) {
        const {pno, productName, productPrice, registDate, viewCount, likeCount, fileName, recStatus} = arr.list[i]
        // console.log(pno)
        productListStr += '<tr';
        if (recStatus == 1) {
          productListStr += ' class="product_list_active"';
        }
        productListStr += '>';
        productListStr += '<td><a href="/admin/product/read/' + pno + '">' + pno + '</a></td>';
        productListStr += '<td><a href="/admin/product/read/' + pno + '"><img src="http://192.168.0.64/s_' + fileName + '">' + productName + '</a></td>';
        productListStr += '<td>' + new Intl.NumberFormat('ko-KR').format(productPrice) + ' 원</td>';
        productListStr += '<td>' + new Date(registDate).toLocaleDateString('ko-KR') + '</td>';
        productListStr += '<td>' + viewCount + '</td>';
        productListStr += '<td>' + likeCount + '</td>';
        productListStr += '<td>';
        if (recStatus == 1) {
          productListStr += '추천';
        }
        productListStr += '</td>';
        productListStr += '</tr>';
        //console.log(productListStr)
      }//end for
      // /Product List 출력

      //Product List Paging 출력
      const {page, size, startNum, endNum, prevBtn, nextBtn, total} = arr
      //console.log(arr)

      if (prevBtn) {
        productPagingStr += '<li><a href="' + (startNum - 1) + '" class="btn btn-group btn-prev">이전</a></li>';
      }

      for (let i = startNum; i <= endNum; i++) {
        productPagingStr += '<li class="' + (page === i ? 'active' : '') + '">';
        productPagingStr += '<a href="' + i + '" class="btn btn-group">' + i + '</a>';
        productPagingStr += '</li>';
      }

      if (nextBtn) {
        productPagingStr += '<li><a href="' + (endNum + 1) + '" class="btn btn-group btn-next">다음</a></li>';
      }
      //console.log(productPagingStr)
      // /Product List Paging 출력
      storeProductList.innerHTML = productListStr
      storeProductPaging.innerHTML = productPagingStr
    })
  }

  productListDefault()

  storeProductPaging.addEventListener("click", (e) => {
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

    //paging 변경
    productListDefault(pageNum)
  })



  // 리뷰 리스트 가져오기
  const getReviewList = async(page = 1) => {
    const res = await axios.get("http://localhost:8080/admin/review/"+sno+"/list?page="+page)
    return res.data
  }

  //리뷰 리스트 출력
  const reviewListDefault = (page) => {
    getReviewList(page).then(arr => {
      let reviewListStr = ""
      let reviewPagingStr = ""
      //Product List 출력
      for (let i = 0; i < arr.list.length; i++) {
        const { rno, email, reviewTitle, reviewContent, reviewDate, fileName } = arr.list[i];
    reviewListStr += '<tr';

    // Add a class 'hidden' to the row if fileName is empty
    if (!fileName) {
        reviewListStr += ' class="hidden"';
    }

    reviewListStr += '>';
    reviewListStr += '<td>' + rno + '</td>';
    reviewListStr += '<td>' + email + '</td>';

    // Add image column if fileName is available
    if (fileName) {
        const imagePath = 'http://192.168.0.64/s_' + fileName;
        reviewListStr += '<td><a href="/admin/review/read/' + rno + '"><img src="' + imagePath + '">' + reviewTitle + '</a></td>';
    } else {
      reviewListStr += '<td><a href="/admin/review/read/' + rno + '">' + reviewTitle + '</a></td>'; 
    }

    reviewListStr += '<td>' + new Date(reviewDate).toLocaleDateString('ko-KR') + '</td>';
    reviewListStr += '</tr>';
      }//end for
      // /review List 출력

      //review List Paging 출력
      const {page, size, startNum, endNum, prevBtn, nextBtn, total} = arr
      //console.log(arr)

      if (prevBtn) {
        reviewPagingStr += '<li><a href="' + (startNum - 1) + '" class="btn btn-group btn-prev">이전</a></li>';
      }

      for (let i = startNum; i <= endNum; i++) {
        reviewPagingStr += '<li class="' + (page === i ? 'active' : '') + '">';
        reviewPagingStr += '<a href="' + i + '" class="btn btn-group">' + i + '</a>';
        reviewPagingStr += '</li>';
      }

      if (nextBtn) {
        reviewPagingStr += '<li><a href="' + (endNum + 1) + '" class="btn btn-group btn-next">다음</a></li>';
      }
      //console.log(productPagingStr)
      // /Product List Paging 출력
      storeReviewList.innerHTML = reviewListStr
      storeReviewPaging.innerHTML = reviewPagingStr
    })
  }

  reviewListDefault()

  storeReviewPaging.addEventListener("click", (e) => {
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

    //paging 변경
    reviewListDefault(pageNum)
  })
	/* 리뷰 리스트 END */


  // '삭제' 버튼 클릭 시 모달 띄우기
  <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
  document.querySelector('.btn-delete').addEventListener('click', function (event) {
    event.preventDefault();
    // 모달 보이기
    $('.deleteModal').modal('show');
  });
  </sec:authorize>

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
      legend: { position: 'bottom' },
      colors: ["#ae2d33"]
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
      legend: { position: 'bottom' },
      colors: ["#ae2d33"]
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_month'));
    chart.draw(data, options);
  }
</script>

</body>

</html>