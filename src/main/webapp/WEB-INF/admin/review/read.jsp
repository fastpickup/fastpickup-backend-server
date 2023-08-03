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
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <h3>리뷰 상세</h3>
            <div class="card">
                <div class="bg-light rounded h-100 p-4">
                    <form onsubmit="onSubmitForm(event)" method="get">
                        <dl class="detail_content">
                            <dt>리뷰 번호</dt>
                            <dd>${reviewRead.rno}</dd>
                            <dt>가맹점 번호</dt>
                            <dd>${reviewRead.sno}</dd>
                            <dt>가맹점</dt>
                            <dd>${reviewRead.storeName}</dd>
                            <dt>작성일</dt>
                            <dd>${reviewRead.reviewDate}</dd>
                            <dt>작성자</dt>
                            <dd class="email">${reviewRead.email}</dd>
                            <dt>리뷰 제목</dt>
                            <dd>${reviewRead.reviewTitle}</dd>

                            <dt>사진</dt>
                            <c:if test="${not empty reviewRead.fileNames and not empty reviewRead.fileNames[0]}">
                                <dd class="view_image">
                                    <c:forEach items="${reviewRead.fileNames}" var="fileName">
                                        <img src="http://localhost/${fileName}" />
                                    </c:forEach>
                                </dd>
                            </c:if>
                            <c:if test="${empty reviewRead.fileNames or empty reviewRead.fileNames[0]}">
                                <dd  style="color: #888;" class="no_image">이미지 없음</dd>
                            </c:if>
                           

                            <dt>리뷰 내용</dt>
                            <dd>${reviewRead.reviewContent}</dd>
                        </dl>
                    </form>
                </div>
            </div>

            <form action="/admin/review/delete/${reviewRead.rno}" method="post">
                <div class="button_wrap mt-4">
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <a href="/admin/review/update/${reviewRead.rno}" class="btn btn-dark">리뷰 수정</a>
                    </sec:authorize>
                    
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <a href="/admin/review/list" class="btn btn-outline-dark">목록으로</a>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_STORE')">
                        <a href="/admin/review/list/${reviewRead.sno}" class="btn btn-outline-dark">목록으로</a>
                    </sec:authorize>
                </div>
            </form>

            <!-- <form action="/admin/qna/replies/${qno}/create" method="post">
                <input type="hidden" id="rno" name="qno" class="form-control" readonly value="${listQna.qno}">
                <input type="hidden" id="email" name="email" class="form-control" readonly value="${pageContext.request.userPrincipal.name}">
                <input type="text" name="reviewContent">
                <button type="submit" class="btn btn-dark">답글 달기</button>
            </form> -->
            <c:choose>
                <c:when test="${count == 1}">
                    <div id="reply-form-wrapper" class="mt-4">
                        <form action="/admin/review/create/${reviewRead.rno}" method="post">
                            <input type="hidden" name="sno" class="form-control" readonly value="${reviewRead.sno}">
                    <input type="hidden" name="gno" class="form-control" readonly value="${reviewRead.gno}">
                    <input type="hidden" name="ono" class="form-control" readonly value="${reviewRead.ono}">
                    <input type="hidden" name="email" class="form-control" readonly value="${pageContext.request.userPrincipal.name}">
                    <input type="text" name="reviewTitle" class="form-control" placeholder="제목을 입력해주세요.">
                    <textarea class="form-control p-3" rows="4" placeholder="답글을 입력해주세요." name="reviewContent"></textarea>
                    <div class="button_wrap mt-4">
                        <button type="submit" class="btn btn-dark">답글 달기</button>
                    </div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div id="reply-form-wrapper" style="background-color: #f0f0f0; border-radius: 8px; padding: 16px; margin-top: 40px">
                <form action="/admin/qna/replies/delete/${replyRead.rno}" method="post">
                    <dl class="detail_content">
                        <dt>사장님</dt>
                        <dd class="email">${storeReview.email}</dd>
                        <dt>답변 날짜</dt>
                        <dd>${storeReview.reviewDate}</dd>
                        <dt>답변 내용</dt>
                        <dd>${storeReview.reviewContent}</dd>
                    </dl>
                    <!-- <a href="/admin/qna/replies/update/${replyRead.rno}">수정</a> -->
                    <!-- <button class="border-0" type="submit">삭제</button> -->
                    <div class="button_wrap mt-4">
                        <a href="/admin/review/store/update/${storeReview.rno}" class="btn btn-dark">답글 수정</a>
                    </div>
                </form>
            </div>
        </c:otherwise>
    </c:choose>

        </div>
    </div>

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

</div>


<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>


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