
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FastPickup</title>
</head>

<body>
<%@ include file="../../include/header.jsp" %>
<h3>답글 수정</h3>
<form action="/admin/qna/replies/update" method="post">
    <div class="form_content">
        <%--  문의 번호  --%>
        <input type="hidden" name="qno" class="form-control-plaintext" id="qno" value="${readReply.qno}" readonly>
        <div class="bg-light rounded h-100 p-4">
            <label for="rno" class="form-label">답글 번호</label>
            <input type="text" name="rno" class="form-control-plaintext" id="rno" value="${readReply.rno}" readonly>
        </div>
        <div class="bg-light rounded h-100 p-4">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" class="form-control-plaintext" id="email" value="${readReply.email}" readonly>
        </div>
        <div class="bg-light rounded h-100 p-4">
            <label for="replyDate" class="form-label">문의 날짜</label>
            <input type="text" name="replyDate" class="form-control-plaintext" id="replyDate" value="${readReply.replyDate}" readonly>
        </div>
        <div class="bg-light rounded h-100 p-4">
            <label for="reply" class="form-label">문의 내용</label>
            <input type="text" name="reply" class="form-control" id="reply" value="${readReply.reply}">
        </div>
    </div>
    <div class="button_wrap p-4">
        <a href="/admin/qna/read/${readReply.qno}" class="btn btn-outline-dark">이전으로</a>
        <button type="submit" class="btn btn-dark">문의글 수정</button>
    </div>
</form>
<%@ include file="../../include/footer.jsp" %>

</body>

</html>