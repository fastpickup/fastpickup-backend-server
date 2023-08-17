<!--
/*
* Date : 2023.07.31
* Author : 송수정
* Author : 이범수
* E-mail : sujung033131@gmail.com
* E-mail : beomsu_1221@naver.com
*/
-->

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
<%@ include file="../include/header.jsp" %>
<h3>문의글 수정</h3>
<form action="/admin/qna/update" method="post">
    <div class="form_content">
        <div class="bg-light rounded h-100 px-4 py-2">
            <label for="qno" class="form-label">문의 번호</label>
            <input type="text" name="qno" class="form-control" id="qno" value="${listQna.qno}" readonly>
        </div>
        <div class="bg-light rounded h-100 px-4 py-2">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" class="form-control" id="email" value="${listQna.email}" readonly>
        </div>
        <div class="bg-light rounded h-100 px-4 py-2">
            <label for="modifyDate" class="form-label">문의 날짜</label>
            <input type="text" name="modifyDate" class="form-control" id="modifyDate" value="${listQna.registDate}" readonly>
        </div>
        <div class="bg-light rounded h-100 px-4 py-2">
            <label for="qnaTitle" class="form-label">문의 제목</label>
            <input type="text" name="qnaTitle" class="form-control" id="qnaTitle" value="${listQna.qnaTitle}">
        </div>
        <div class="bg-light rounded h-100 px-4 py-2">
            <label for="qnaContent" class="form-label">문의 내용</label>
            <input type="text" name="qnaContent" class="form-control" id="qnaContent" value="${listQna.qnaContent}">
        </div>
    </div>
    <div class="button_wrap p-4">
        <a href="/admin/qna/read/${listQna.qno}" class="btn btn-outline-dark">이전으로</a>
        <button type="submit" class="btn btn-dark">문의글 수정</button>
    </div>
</form>
<%@ include file="../include/footer.jsp" %>

</body>

</html>