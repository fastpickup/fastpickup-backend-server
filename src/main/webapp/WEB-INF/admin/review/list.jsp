<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <h3>리뷰</h3>
  <!-- Search Start -->
  <div class="my-4 search_wrap bg-body">
    <form action="/admin/review/list" method="get" class="actionForm">
      <div class="search_box">
        <input type="hidden" name="page" value="${pageRequestDTO.page}">
        <input type="hidden" name="size" value="${pageRequestDTO.size}">
        <div class="input-group mb-3">
          <select name="type" class="form-select search-condition">
            <option value="">선택해주세요</option>
            <option value="e" ${pageRequestDTO.type=='e' ? 'selected="selected"' : '' }>Email</option>
            <option value="s" ${pageRequestDTO.type=='n' ? 'selected="selected"' : '' }>가맹점</option>
            <option value="t" ${pageRequestDTO.type=='p' ? 'selected="selected"' : '' }>리뷰제목</option>
          </select>
          <input type="text" name="keyword" class="form-control search-input" placeholder="검색어를 입력 해주세요."
            value="${pageRequestDTO.keyword}">
          <button type="submit" class="btn btn-primary btnSearch">검색</button>
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
            <th scope="col">리뷰 번호</th>
            <th scope="col">주문 번호</th>
            <!-- <th scope="col">가맹점 번호</th> -->
            <th scope="col">가맹점</th>
            <th scope="col">주문음식</th>
            <th scope="col">작성자</th>
            <th scope="col">리뷰 제목</th>
            <th scope="col">작성일</th>
            <!-- <th scope="col">회원 권한</th> -->
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${listReview.list}" var="review" varStatus="status">
            <tr>
              <td>${review.rno}</td>
              <td>${review.ono}</td>
              <!-- <td>${review.sno}</td> -->
              <td><a href="/admin/store/read/${review.sno}">${review.storeName}</a></td>
              <td>${review.productName}</td>
              <td>${review.email}</td>
              <td>${review.reviewTitle}</td>
              <td>${review.reviewDate}</td>
              <!-- <td>${member.store}</td> -->
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <!-- <div class="button_wrap">
      <a href="/admin/review/create" class="btn btn-dark">리뷰작성</a>
    </div> -->
  </div>

  <!-- Paging Start -->
  <div class="btn-toolbar" role="toolbar" style="justify-content: center;">
    <ul class="btn-group me-2 paging" role="group" aria-label="First group">
      <c:if test="${listReview.prevBtn}">
        <li><a href="${listReview.startNum - 1}" class="btn btn-group btn-prev">이전</a></li>
      </c:if>

      <c:forEach var="i" begin="${listReview.startNum}" end="${listReview.endNum}">
        <li class="${listReview.page == i ? 'active' : ''}">
          <a href="${i}" class="btn btn-group">${i}</a>
        </li>
      </c:forEach>

      <c:if test="${listReview.nextBtn}">
        <li><a href="${listReview.endNum + 1}" class="btn btn-group btn-next">다음</a></li>
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
    actionForm.setAttribute("action", "/admin/review/list")
    //submit
    actionForm.submit()
  })

  //검색 버튼
  btnSearch.addEventListener("click", e => {
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