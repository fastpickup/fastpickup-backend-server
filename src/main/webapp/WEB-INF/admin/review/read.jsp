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
                            <dt>가맹점</dt>
                            <dd>${reviewRead.storeName}</dd>
                            <dt>작성일</dt>
                            <dd>${reviewRead.reviewDate}</dd>
                            <dt>작성자</dt>
                            <dd class="email">${reviewRead.email}</dd>
                            <dt>리뷰 제목</dt>
                            <dd>${reviewRead.reviewTitle}</dd>
                            <dt>리뷰 내용</dt>
                            <dd>${reviewRead.reviewContent}</dd>

                        </dl>
                    </form>
                </div>
            </div>

            <form action="/admin/qna/delete/${listQna.qno}" method="post">
                <div class="button_wrap mt-4">
                    <a href="/admin/review/update/${listQna.qno}" class="btn btn-dark">리뷰 수정</a>
                    <a href="/admin/review/list" class="btn btn-outline-dark">목록으로</a>
                    <button type="submit" class="btn btn-primary btn-delete">리뷰 삭제</button>
                </div>
            </form>

            <form action="/admin/qna/replies/${qno}/create" method="post">
                <input type="hidden" id="rno" name="qno" class="form-control" readonly value="${listQna.qno}">
                <input type="hidden" id="email" name="email" class="form-control" readonly value="${pageContext.request.userPrincipal.name}">
                <input type="text" name="reviewContent">
                <button type="submit" class="btn btn-dark">답글 달기</button>
            </form>

            <div style="background-color: #f0f0f0; border-radius: 8px; padding: 16px; margin-top: 10px">
            <form action="/admin/qna/replies/delete/${replyRead.rno}" method="post">
                    <dl class="detail_content">
                        <dt style="font-size: 16px;">Email</dt>
                        <dd  style="font-size: 14px;" class="email">${replyRead.email}</dd>
                        <dt style="font-size: 16px;">답글 날짜</dt>
                        <dd style="font-size: 14px;">${replyRead.replyDate}</dd>
                        <dt style="font-size: 16px;">답글 내용</dt>
                        <dd style="font-size: 14px;">${replyRead.reply}</dd>
                    </dl>
                <a href="/admin/qna/replies/update/${replyRead.rno}">수정</a>
                <button type="submit">삭제</button>
            </form>
            </div>


        </div>
    </div>
</div>


<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>


</script>
</body>

</html>