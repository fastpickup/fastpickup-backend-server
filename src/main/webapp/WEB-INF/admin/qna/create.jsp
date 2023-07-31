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
<form action="/admin/qna/create" method="post">
    <h3>문의 등록</h3>
    <div class="form_content">
        <div class="bg-light rounded h-100 p-4">
            <label for="email" class="form-label">이메일</label>
            <input type="email" id="email" name="email" class="form-control" readonly value="${pageContext.request.userPrincipal.name}">
        </div>
        <div class="bg-light rounded h-100 p-4">
            <label for="qnaTitle" class="form-label">문의 제목</label>
            <input type="text" id="qnaTitle" name="qnaTitle" class="form-control" required placeholder="문의 제목을 입력 해주세요">
        </div>
        <div class="bg-light rounded h-100 p-4">
            <label for="qnaContent" class="form-label">문의 내용</label>
            <input type="text" id="qnaContent" name="qnaContent" class="form-control" required placeholder="문의 내용을 입력 해주세요">
        </div>
    </div>
    <div class="button_wrap p-4">
        <a href="/admin/qna/list" class="btn btn-outline-dark btnSignin">목록으로</a>
        <button type="submit" class="btn btn-dark">등록</button>
    </div>
</form>

<%@ include file="../include/footer.jsp" %>

<!-- JavaScript Start -->
<script>


</script>
</body>

</html>