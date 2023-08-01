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
            <h3>문의 상세</h3>
            <div class="card">
                <div class="bg-light rounded h-100 p-4">
                    <form onsubmit="onSubmitForm(event)" method="get">
                        <dl class="detail_content">
                            <dt>문의 번호</dt>
                            <dd>${listQna.qno}</dd>
                            <dt>Email</dt>
                            <dd class="email">${listQna.email}</dd>
                            <dt>문의 날짜</dt>
                            <dd>${listQna.registDate}</dd>
                            <dt>문의 제목</dt>
                            <dd>${listQna.qnaTitle}</dd>
                            <dt>문의 내용</dt>
                            <dd>${listQna.qnaContent}</dd>
                        </dl>
                    </form>
                </div>
            </div>

            <form action="/admin/qna/delete/${listQna.qno}" method="post">
                <div class="button_wrap mt-4">
<%--                    <a href="/admin/qna/update/${listQna.qno}" class="btn btn-dark">문의 수정</a>--%>
                    <a href="/admin/qna/list" class="btn btn-outline-dark">목록으로</a>
                    <button type="submit" class="btn btn-primary btn-delete">문의 삭제</button>
                </div>
            </form>
            <c:choose>
                <c:when test="${count == 0}">
                    <div id="reply-form-wrapper" class="mt-4">
                        <form action="/admin/qna/replies/${qno}/create" method="post">
                            <input type="hidden" name="qno" class="form-control" readonly value="${listQna.qno}">
                            <input type="hidden" name="email" class="form-control" readonly value="${pageContext.request.userPrincipal.name}">
                            <textarea class="form-control p-3" rows="4" placeholder="답글을 입력해주세요." name="reply"></textarea>
                            <div class="button_wrap mt-4">
                                <button type="submit" class="btn btn-dark">답글 달기</button>
                            </div>
                        </form>
                    </div>

                </c:when>
                <c:otherwise>
                    <div style="background-color: #f0f0f0; border-radius: 8px; padding: 16px; margin-top: 40px">
                        <form action="/admin/qna/replies/delete/${replyRead.rno}" method="post">
                            <dl class="detail_content">
                                <dt>Email</dt>
                                <dd class="email">${replyRead.email}</dd>
                                <dt>답변 날짜</dt>
                                <dd>${replyRead.replyDate}</dd>
                                <dt>답변 내용</dt>
                                <dd>${replyRead.reply}</dd>
                            </dl>
                            <a href="/admin/qna/replies/update/${replyRead.rno}">수정</a>
                            <button class="border-0" type="submit">삭제</button>
                        </form>
                    </div>

                </c:otherwise>
            </c:choose>





        </div>
    </div>
</div>


<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>


</script>
</body>

</html>