<!--
/*
* Date : 2023.07.27
* Author : 권성준
* E-mail : thistrik@naver.com
*/
-->

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<div class="col-12">
	<h3>가맹점 목록</h3>
	<!-- Search Start -->
	<div class="my-4 search_wrap bg-body">
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
			<form action="/admin/store/list" method="get" class="actionForm">
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_STORE')">
			<form action="/admin/store/list/${sno}" method="get" class="actionForm">
		</sec:authorize>
			<div class="search_box">
				<input type="hidden" name="page" value="${pageRequestDTO.page}">
				<input type="hidden" name="size" value="${pageRequestDTO.size}">
				<div class="input-group mb-3">
					<select name="type" class="form-select search-condition">
						<option value="">선택해주세요</option>
						<option value="e" ${pageRequestDTO.type=='e' ? 'selected="selected"' : '' }>Email</option>
						<option value="s" ${pageRequestDTO.type=='s' ? 'selected="selected"' : '' }>가맹점 이름</option>
						<option value="es" ${pageRequestDTO.type=='es' ? 'selected="selected"' : '' }>통합 검색</option>
					</select>
					<input type="text" name="keyword" class="form-control search-input" placeholder="검색어를 입력 해주세요." value="${pageRequestDTO.keyword}">
					<button type="button" class="btn btn-primary btnSearch">검색</button>
				</div>
			</div>
		</form>
	</div>
	<!-- Search End -->
	<div class="bg-light rounded h-100 p-4">
		<div class="table-responsive">
			<table class="table">
				<thead>
				<tr>
					<th scope="col">가맹점 번호</th>
					<th scope="col">가맹점 이름</th>
					<th scope="col">가맹점 사업자 번호</th>
					<th scope="col">가맹점 주소</th>
					<th scope="col">가맹점 이메일</th>
					<th scope="col">가맹점 전화번호</th>
					<th scope="col">가맹점 대표이미지</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${listStore.list}" var="store" varStatus="status">
					<tr>
						<td>${store.sno}</td>
						<td><a href="/admin/store/read/${store.sno}">${store.storeName}</a></td>
						<td>${store.storeNumber}</td>
						<td>${store.storeAddress}</td>
						<td>${store.email}</td>
						<td>${store.storePhone}</td>
						<td>
							<c:if test="${store.fileName}">
							</c:if>
							<img src="http://192.168.0.64/s_${store.fileName}" />
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
		<div class="button_wrap">
			<a href="/admin/store/create" class="btn btn-dark">가맹점 추가</a>
		</div>
		</sec:authorize>
	</div>

	<!-- Paging Start -->
	<div class="btn-toolbar" role="toolbar" style="justify-content: center;">
		<ul class="btn-group me-2 paging" role="group" aria-label="First group">
			<c:if test="${listStore.prevBtn}">
				<li><a href="${listStore.startNum - 1}" class="btn btn-group btn-prev">이전</a></li>
			</c:if>

			<c:forEach var="i" begin="${listStore.startNum}" end="${listStore.endNum}">
				<li class="${listStore.page == i ? 'active' : ''}">
					<a href="${i}" class="btn btn-group">${i}</a>
				</li>
			</c:forEach>

			<c:if test="${listStore.nextBtn}">
				<li><a href="${listStore.endNum + 1}" class="btn btn-group btn-next">다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- Paging End -->
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
</div>
<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>
  const paging = document.querySelector(".paging")
  const actionForm = document.querySelector(".actionForm")
  const pageInput = actionForm.querySelector("input[name=page]")
  const typeObj = actionForm.querySelector("select[name=type]")
  const keywordObj = actionForm.querySelector("input[name=keyword]")
  const btnSearch = document.querySelector(".btnSearch")

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
    actionForm.setAttribute("action", "/admin/store/list")
    //submit
    actionForm.submit()
  })

  //검색 버튼
  btnSearch.addEventListener("click", (e) => {
    //이벤트 막기
    e.preventDefault()
    e.stopPropagation()

    //검색타입, 키워드 입력 안되었을 시 return
    if (typeObj.options[typeObj.selectedIndex].value === "") {
      alert("검색 조건을 선택해주세요")
	    typeObj.focus()
      return
    }
    if (keywordObj.value === "") {
      alert("검색어를 입력해주세요")
	    keywordObj.focus()
      return
    }

    //검색 하고나면 page는 무조건 1페이지
    pageInput.value = 1

    actionForm.submit()
  }, false)

  const alertModal = new bootstrap.Modal(document.querySelector(".alertModal"))
  let message = "${message}";
  if (message !== "") {
    alertModal.show();
  }
  setTimeout(function () {
    alertModal.hide();
  }, 1500);

</script>
</body>

</html>